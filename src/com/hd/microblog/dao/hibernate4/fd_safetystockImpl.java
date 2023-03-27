package com.hd.microblog.dao.hibernate4;


import org.springframework.stereotype.Repository;

import com.hd.common.dao.hibernate4.BaseHibernateDao;
import com.hd.microblog.dao.fd_safetystockDao;
import com.hd.microblog.model.fd_safetystock;

@Repository("fd_safetystockDao")
public class fd_safetystockImpl extends BaseHibernateDao<fd_safetystock, Integer>  implements fd_safetystockDao {

}
