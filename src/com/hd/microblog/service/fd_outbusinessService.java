package com.hd.microblog.service;

import java.util.List;
import java.util.Map;

import com.hd.common.service.IBaseService;
import com.hd.microblog.model.fd_outbusiness;


public interface fd_outbusinessService  extends IBaseService<fd_outbusiness, Integer> {

	List adminfindfd_outbusinesslist1(String jqcode, String bdcode, Integer start, int number);

	List adminfindfd_outbusinesslistcount1(String jqcode, String bdcode);

	List adminfindfd_outbusinesslistcount(String outbusinessName);

	List adminfindfd_outbusinesslist(Integer start, int number);

	List adminfindfd_outbusinesslist();

	List adminfindfd_outbusinesslistall();

	List adminfindfd_outbusinessfordidandtype(Integer dataprocess_id, int flag);

	List adminfindfd_outbusinesslistfordpid(int dataprocess_id);

	List adminfindaccount(int outbusinessNum, String outbusinessName);

	

	

	

	
}

