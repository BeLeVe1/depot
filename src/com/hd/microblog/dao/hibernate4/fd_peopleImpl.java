package com.hd.microblog.dao.hibernate4;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import com.hd.common.dao.hibernate4.BaseHibernateDao;
import com.hd.microblog.dao.fd_peopleDao;
import com.hd.microblog.model.fd_people;

@Repository("fd_peopleDao")
public class fd_peopleImpl extends BaseHibernateDao<fd_people, Integer>  implements  fd_peopleDao{


	

}
