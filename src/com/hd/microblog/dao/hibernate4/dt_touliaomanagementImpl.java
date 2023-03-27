package com.hd.microblog.dao.hibernate4;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import com.hd.common.dao.hibernate4.BaseHibernateDao;
import com.hd.microblog.dao.dt_touliaomanagementDao;
import com.hd.microblog.model.dt_touliaomanagement;

@Repository("dt_touliaomanagementDao")
public class dt_touliaomanagementImpl extends BaseHibernateDao<dt_touliaomanagement, Integer>  implements  dt_touliaomanagementDao{
	

}