package com.hd.microblog.dao.hibernate4;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import com.hd.common.dao.hibernate4.BaseHibernateDao;
import com.hd.microblog.dao.dt_orderlistDao;
import com.hd.microblog.model.dt_orderlist;

@Repository("dt_orderlistDao")
public class dt_orderlistImpl extends BaseHibernateDao<dt_orderlist, Integer>  implements  dt_orderlistDao{
	

}