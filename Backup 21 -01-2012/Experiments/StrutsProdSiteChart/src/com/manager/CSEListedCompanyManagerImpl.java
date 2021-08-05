package com.manager;

import com.dao.CSEListedCompanyDao;
import com.domain.CSEListedCompany;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 11/2/11
 * Time: 11:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class CSEListedCompanyManagerImpl implements CSEListedCompanyManager {

    private CSEListedCompanyDao cseListedCompanyDao;

    public void setCseListedCompanyDao(CSEListedCompanyDao cseListedCompanyDao) {
        this.cseListedCompanyDao = cseListedCompanyDao;
    }

    public List<String> getCSEStockCodes() {

        List<CSEListedCompany> cseListedCompanies =  cseListedCompanyDao.getCSEListedCompanies();
        List<String> cseListedCompanyStockCodes = new ArrayList<String>();

        for (CSEListedCompany cseListedCompany : cseListedCompanies) {
            cseListedCompanyStockCodes.add(cseListedCompany.getStockCode());
        }
        return cseListedCompanyStockCodes;
    }
}
