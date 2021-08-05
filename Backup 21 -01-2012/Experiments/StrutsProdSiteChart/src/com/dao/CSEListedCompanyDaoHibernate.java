package com.dao;

import com.domain.CSEListedCompany;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 11/2/11
 * Time: 11:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class CSEListedCompanyDaoHibernate extends HibernateDaoSupport implements CSEListedCompanyDao {

    public List<CSEListedCompany> getCSEListedCompanies() {

        Query query = getSession().getNamedQuery("getCSEListedCompanyCodes");
        return query.list();
    }
}
