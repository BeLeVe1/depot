package com.hd.microblog.dao.hibernate4;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import com.hd.common.dao.hibernate4.BaseHibernateDao;
import com.hd.microblog.dao.dt_realscheduleDao;
import com.hd.microblog.model.dt_realschedule;

@Repository("dt_realscheduleDao")
public class dt_realscheduleImpl extends BaseHibernateDao<dt_realschedule, Integer>  implements  dt_realscheduleDao{


}