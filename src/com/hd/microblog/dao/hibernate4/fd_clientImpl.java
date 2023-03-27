package com.hd.microblog.dao.hibernate4;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import com.hd.common.dao.hibernate4.BaseHibernateDao;
import com.hd.microblog.dao.fd_clientDao;
import com.hd.microblog.model.fd_client;

@Repository("fd_clientDao")
public class fd_clientImpl extends BaseHibernateDao<fd_client, Integer>  implements  fd_clientDao{


	

}
