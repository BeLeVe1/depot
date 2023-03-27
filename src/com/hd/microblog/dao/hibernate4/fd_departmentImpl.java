package com.hd.microblog.dao.hibernate4;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import com.hd.common.dao.hibernate4.BaseHibernateDao;
import com.hd.microblog.dao.fd_departmentDao;
import com.hd.microblog.model.fd_department;

@Repository("fd_departmentDao")
public class fd_departmentImpl extends BaseHibernateDao<fd_department, Integer>  implements  fd_departmentDao{


	

}
