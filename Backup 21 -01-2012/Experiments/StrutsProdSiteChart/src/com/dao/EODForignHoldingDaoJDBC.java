package com.dao;


import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.ResultSetWrappingSqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 9/26/11
 * Time: 5:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class EODForignHoldingDaoJDBC extends JdbcTemplate implements EODForignHoldingDao{


        private static Logger log = Logger.getLogger(EODForignHoldingDaoJDBC.class);



    public ResultSet getEODForignHoldingValuesByLastTradedDate(String from, String to) throws SQLException, ParseException {


        String query = "select L.Company_name ,  L.FH - E.FH as Diff\n" +
                "from (select company_name , round(CONVERT(REPLACE(T.forign_holding,',',''),SIGNED INTEGER)/CONVERT(REPLACE(T.issued_qty,',',''),SIGNED INTEGER) * 100,2) FH  \n" +
                "from upuldi_CSEDAILYDATA.DAILY_STOCK_STATISTICS T where rtrim(ltrim(last_traded_date)) = (select  rtrim(ltrim(z.last_traded_date)) from (select @rownumC:=@rownumC+1 rownumC, d.last_traded_date from upuldi_CSEDAILYDATA.DAILY_STOCK_STATISTICS d, (SELECT @rownumC:=0) r where last_traded_date is not null and last_traded_date <> ' null ' and DATE_FORMAT(d.last_traded_date,'%d/%m/%Y') >= DATE_FORMAT(?,'%d/%m/%Y')   group by last_traded_date order by  DATE_FORMAT(d.last_traded_date,'%d/%m/%Y') asc  limit 1)z )\n" +
                ") E\n" +
                "inner join (\n" +
                "select company_name , round(CONVERT(REPLACE(T.forign_holding,',',''),SIGNED INTEGER)/CONVERT(REPLACE(T.issued_qty,',',''),SIGNED INTEGER) * 100,2) FH  \n" +
                "from upuldi_CSEDAILYDATA.DAILY_STOCK_STATISTICS T where rtrim(ltrim(last_traded_date)) = (select rtrim(ltrim(z.last_traded_date)) from (select @rownumC:=@rownumC+1 rownumC, d.last_traded_date from upuldi_CSEDAILYDATA.DAILY_STOCK_STATISTICS d, (SELECT @rownumC:=0) r where last_traded_date is not null and last_traded_date <> ' null ' and DATE_FORMAT(d.last_traded_date,'%d/%m/%Y') <= DATE_FORMAT(?,'%d/%m/%Y') group by last_traded_date order by  DATE_FORMAT(d.last_traded_date,'%d/%m/%Y') desc  limit 1)z)\n" +
                ") L on E.company_name = L.company_name\n" +
                "where L.FH - E.FH != 0\n" +
                "order by L.FH - E.FH asc;";


        SimpleDateFormat simpleDateFormatFullDate = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat simpleDateFormatYearShort = new SimpleDateFormat("dd/MM/yy");

        Date fromDate = simpleDateFormatFullDate.parse(from);
        String fromShort = simpleDateFormatYearShort.format(fromDate);

        Date toDate = simpleDateFormatFullDate.parse(to);
        String toShort = simpleDateFormatYearShort.format(toDate);




        Object[] parameters = new Object[2];
        parameters[0] = fromShort;
        parameters[1] = toShort;


        int[] types = { java.sql.Types.VARCHAR , java.sql.Types.VARCHAR  } ;
        SqlRowSet rowSet = this.queryForRowSet(query,parameters,types);
        ResultSetWrappingSqlRowSet resultSetWrappingSqlRowSet = (ResultSetWrappingSqlRowSet) rowSet;
        ResultSet rs = resultSetWrappingSqlRowSet.getResultSet();

      //  log.debug("RESULT SET HAS NEXT " + rs.next());

        return rs;

    }
}
