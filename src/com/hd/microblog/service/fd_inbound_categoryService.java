package com.hd.microblog.service;

import java.util.List;
import java.util.Map;

import com.hd.common.service.IBaseService;
import com.hd.microblog.model.fd_inbound_category;


public interface fd_inbound_categoryService  extends IBaseService<fd_inbound_category, Integer> {

	List adminfindfd_inbound_categorylist1(String jqcode, String bdcode, Integer start, int number);

	List adminfindfd_inbound_categorylistcount1(String jqcode, String bdcode);

	List adminfindfd_inbound_categorylistcount(String inbound_categoryName);

	List adminfindfd_inbound_categorylist(Integer start, int number);

	List adminfindfd_inbound_categorylist();

	List adminfindfd_inbound_categorylistall();

	List adminfindfd_inbound_categoryfordidandtype(Integer dataprocess_id, int flag);

	List adminfindfd_inbound_categorylistfordpid(int dataprocess_id);

	List adminfindaccount(int inbound_categoryNum, String inbound_categoryName);

	

	

	

	
}

