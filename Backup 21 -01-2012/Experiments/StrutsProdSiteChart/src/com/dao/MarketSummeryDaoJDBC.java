package com.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.ResultSetWrappingSqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.ResultSet;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/1/11
 * Time: 1:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class MarketSummeryDaoJDBC extends JdbcTemplate implements MarketSummeryDao{


    public ResultSet getMarketSummeryValues(String type) {

        String query = null;

        if ("ALL".equalsIgnoreCase(type)) {
            query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(cse_all_share_index, \",\", \"\")  as value " +
                    "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y') desc;";
        }else if ("MIL".equalsIgnoreCase(type)) {
            query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(milanka_price_index, \",\", \"\") as value " +
                    "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y') desc;";
        }else if ("MTURNOVER".equalsIgnoreCase(type)) {
            query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(MARKET_VALUE_OF_TURNOVER_RS, \",\", \"\") as value " +
                    "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y') desc;";
        }else if ("MTRADE".equalsIgnoreCase(type)) {
            query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(TRADES_NO, \",\", \"\") as value " +
                    "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y') desc;";
        }else if ("DOMTRADE".equalsIgnoreCase(type)) {
            query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(DOMESTIC_NO, \",\", \"\") as value " +
                    "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y') desc;";
        }else if ("FORTRADE".equalsIgnoreCase(type)) {
            query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(FOREIGN_NO, \",\", \"\") as value " +
                    "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y') desc;";
        }else if ("DOMPER".equalsIgnoreCase(type)) {
            query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(DOMESTIC_PURCHASE_RS, \",\", \"\") as value " +
                    "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y')  desc;";
        }else if ("DOMSALE".equalsIgnoreCase(type)) {
            query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(DOMESTIC_SALES_RS, \",\", \"\") as value " +
                    "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y') desc;";
        }else if ("FORPER".equalsIgnoreCase(type)) {
            query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(FOREIGN_PURCHASE_RS, \",\", \"\") as value " +
                    "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y') desc;";
        }else if ("FORSALE".equalsIgnoreCase(type)) {
            query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(FOREIGN_SALES_RS, \",\", \"\") as value " +
                    "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y') desc;";
        }else if ("MVOL".equalsIgnoreCase(type)) {
            query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(VOLUME_OF_TURNOVER_NO, \",\", \"\") as value " +
                    "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y') desc;";
        }else if ("VOLDOM".equalsIgnoreCase(type)) {
            query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(VOLUME_OF_DOMESTIC_NO, \",\", \"\") as value " +
                    "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y') desc;";
        }else if ("VFOR".equalsIgnoreCase(type)) {
            query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(VOLUME_OF_FOREIGN_NO, \",\", \"\") as value " +
                    "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y') desc;";
        }
        else if ("TOTVOL".equalsIgnoreCase(type)) {
                    query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(VOLUME_OF_TURNOVER_NO, \",\", \"\") as value " +
                            "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y') desc;";
        }
        else if ("DOMVOL".equalsIgnoreCase(type)) {
                    query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(DOMESTIC_NO, \",\", \"\") as value " +
                            "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y') desc;";
        }
        else if ("FORVOL".equalsIgnoreCase(type)) {
                    query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(VOLUME_OF_FOREIGN_NO, \",\", \"\") as value " +
                            "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y') desc;";
        }
        else if ("ALLTRADE".equalsIgnoreCase(type)) {
                    query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(TRADES_NO, \",\", \"\") as value " +
                            "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y') desc;";
        }

        SqlRowSet rowSet = this.queryForRowSet(query);

        ResultSetWrappingSqlRowSet resultSetWrappingSqlRowSet = (ResultSetWrappingSqlRowSet) rowSet;
        return resultSetWrappingSqlRowSet.getResultSet();

    }

    public ResultSet getMarketSummerySectionValues(String type) {

        String query = null;

        if ("VOL".equalsIgnoreCase(type)) {
            query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d') as date, \n" +
                    "REPLACE(volume_of_turnover_no, \",\", \"\")  as allturn, \n" +
                    "REPLACE(volume_of_domestic_no, \",\", \"\")  as domestic,\n" +
                    "REPLACE(volume_of_foreign_no, \",\", \"\")  as forturn \n" +
                    "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y') desc;";
        }else if ("MIL".equalsIgnoreCase(type)) {
            query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(milanka_price_index, \",\", \"\") as value " +
                    "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y') desc;";
        }else if ("MTURNOVER".equalsIgnoreCase(type)) {
            query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(MARKET_VALUE_OF_TURNOVER_RS, \",\", \"\") as value " +
                    "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y') desc;";
        }else if ("MTRADE".equalsIgnoreCase(type)) {
            query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(TRADES_NO, \",\", \"\") as value " +
                    "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y') desc;";
        }else if ("DOMTRADE".equalsIgnoreCase(type)) {
            query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(DOMESTIC_NO, \",\", \"\") as value " +
                    "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y') desc;";
        }else if ("FORTRADE".equalsIgnoreCase(type)) {
            query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(FOREIGN_NO, \",\", \"\") as value " +
                    "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y') desc;";
        }else if ("DOMPER".equalsIgnoreCase(type)) {
            query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(DOMESTIC_PURCHASE_RS, \",\", \"\") as value " +
                    "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y')  desc;";
        }else if ("DOMSALE".equalsIgnoreCase(type)) {
            query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(DOMESTIC_SALES_RS, \",\", \"\") as value " +
                    "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y') desc;";
        }else if ("FORPER".equalsIgnoreCase(type)) {
            query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(FOREIGN_PURCHASE_RS, \",\", \"\") as value " +
                    "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y') desc;";
        }else if ("FORSALE".equalsIgnoreCase(type)) {
            query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(FOREIGN_SALES_RS, \",\", \"\") as value " +
                    "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y') desc;";
        }else if ("MVOL".equalsIgnoreCase(type)) {
            query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(VOLUME_OF_TURNOVER_NO, \",\", \"\") as value " +
                    "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y') desc;";
        }else if ("VOLDOM".equalsIgnoreCase(type)) {
            query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(VOLUME_OF_DOMESTIC_NO, \",\", \"\") as value " +
                    "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y') desc;";
        }else if ("VFOR".equalsIgnoreCase(type)) {
            query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(VOLUME_OF_FOREIGN_NO, \",\", \"\") as value " +
                    "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y') desc;";
        }
        else if ("TOTVOL".equalsIgnoreCase(type)) {
                    query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(VOLUME_OF_TURNOVER_NO, \",\", \"\") as value " +
                            "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y') desc;";
        }
        else if ("DOMVOL".equalsIgnoreCase(type)) {
                    query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(DOMESTIC_NO, \",\", \"\") as value " +
                            "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y') desc;";
        }
        else if ("FORVOL".equalsIgnoreCase(type)) {
                    query = "select DATE_FORMAT(str_to_date(day,'%d-%m-%Y'),'%Y-%m-%d'), REPLACE(VOLUME_OF_FOREIGN_NO, \",\", \"\") as value " +
                            "from upuldi_CSEDAILYDATA.DAILY_MARKET_SUMMERY order by str_to_date(day,'%d-%m-%Y') desc;";
        }

        SqlRowSet rowSet = this.queryForRowSet(query);

        ResultSetWrappingSqlRowSet resultSetWrappingSqlRowSet = (ResultSetWrappingSqlRowSet) rowSet;
        return resultSetWrappingSqlRowSet.getResultSet();
    }

    public ResultSet getContributorSummeryValues(String type) {

        String query = null;

        if ("VOLUME".equalsIgnoreCase(type)) {

            query = "select y.ticker,y.vol from (select @rownum:=@rownum+1 rownum, d.ticker,d.vol  \n" +
                    "from upuldi_CSEDAILYDATA.EOD_DATA d, (SELECT @rownum:=0) r where date  = \n" +
                    "(select z.date from (select @rownumC:=@rownumC+1 rownumC, d.date from upuldi_CSEDAILYDATA.EOD_DATA d, (SELECT @rownumC:=0) r group by date order by  DATE_FORMAT(d.date,'%Y-%m-%d') desc  limit 1)z) order by d.vol desc limit 10)y;";

        }else if("POSITIVE-PRE".equalsIgnoreCase(type)) {

            query = "select x.* from (select d.ticker , ROUND((((REPLACE(close,',','')-REPLACE(open,',',''))/REPLACE(open,',',''))*100),2) gap from upuldi_CSEDAILYDATA.EOD_DATA d where d.date = (select z.date from (select @rownumC:=@rownumC+1 rownumC, d.date \n" +
                    "from upuldi_CSEDAILYDATA.EOD_DATA d, (SELECT @rownumC:=0) r group by date \n" +
                    "order by  DATE_FORMAT(d.date,'%Y-%m-%d') desc  limit 1)z)) x order by x.gap desc limit 10;";

        } else if("NEGATIVE-PRE".equalsIgnoreCase(type)) {

            query = "select x.* from (select d.ticker , ROUND((((REPLACE(close,',','')-REPLACE(open,',',''))/REPLACE(open,',',''))*100),2) gap from upuldi_CSEDAILYDATA.EOD_DATA d where d.date = (select z.date from (select @rownumC:=@rownumC+1 rownumC, d.date \n" +
                    "from upuldi_CSEDAILYDATA.EOD_DATA d, (SELECT @rownumC:=0) r group by date \n" +
                    "order by  DATE_FORMAT(d.date,'%Y-%m-%d') desc  limit 1)z)) x order by x.gap asc limit 10;";

        } else if("FORIGN-HOL-INDEX".equalsIgnoreCase(type)) {

            query = "select RTRIM(LTRIM(S.company_name)), round((CONVERT(REPLACE(S.forign_holding,',',''),UNSIGNED INTEGER)/CONVERT(REPLACE(S.issued_qty,',',''),UNSIGNED INTEGER))*100,2) PRESENT\n" +
                    "from upuldi_CSEDAILYDATA.DAILY_STOCK_STATISTICS S\n" +
                    "where S.last_traded_date = (select z.last_traded_date from (select @rownumC:=@rownumC+1 rownumC, d.last_traded_date from upuldi_CSEDAILYDATA.DAILY_STOCK_STATISTICS d, (SELECT @rownumC:=0) r group by last_traded_date order by  DATE_FORMAT(d.last_traded_date,'%d/%m/%Y') desc  limit 1)z\n" +
                    ") and ( round((CONVERT(REPLACE(S.forign_holding,',',''),UNSIGNED INTEGER)/CONVERT(REPLACE(S.issued_qty,',',''),UNSIGNED INTEGER))*100,2) < 100) \n" +
                    "and ( round((CONVERT(REPLACE(S.forign_holding,',',''),UNSIGNED INTEGER)/CONVERT(REPLACE(S.issued_qty,',',''),UNSIGNED INTEGER))*100,2) > 0 ) \n" +
                    "order by PRESENT;";
        }

        SqlRowSet rowSet = this.queryForRowSet(query);

        ResultSetWrappingSqlRowSet resultSetWrappingSqlRowSet = (ResultSetWrappingSqlRowSet) rowSet;
        return resultSetWrappingSqlRowSet.getResultSet();
    }


}
