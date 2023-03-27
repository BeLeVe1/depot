package com.hd.microblog.service;

import java.util.List;
import java.util.Map;

import com.hd.common.service.IBaseService;
import com.hd.microblog.model.fd_outbound_category;


public interface fd_outbound_categoryService  extends IBaseService<fd_outbound_category, Integer> {

	List adminfindfd_outbound_categorylist1(String jqcode, String bdcode, Integer start, int number);

	List adminfindfd_outbound_categorylistcount1(String jqcode, String bdcode);

	List adminfindfd_outbound_categorylistcount(String outbound_categoryName);

	List adminfindfd_outbound_categorylist(Integer start, int number);

	List adminfindfd_outbound_categorylist();

	List adminfindfd_outbound_categorylistall();

	List adminfindfd_outbound_categoryfordidandtype(Integer dataprocess_id, int flag);

	List adminfindfd_outbound_categorylistfordpid(int dataprocess_id);

	List adminfindaccount(int outbound_categoryNum, String outbound_categoryName);

	

	

	

	
}

