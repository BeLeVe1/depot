package com.hd.microblog.service;

import java.util.List;
import java.util.Map;

import com.hd.common.service.IBaseService;
import com.hd.microblog.model.fd_client;


public interface fd_clientService  extends IBaseService<fd_client, Integer> {

	List adminfindfd_clientlist1(String jqcode, String bdcode, Integer start, int number);

	List adminfindfd_clientlistcount1(String jqcode, String bdcode);

	List adminfindfd_clientlistcount(String clientName);

	List adminfindfd_clientlist(Integer start, int number);

	List adminfindfd_clientlist();

	List adminfindfd_clientlistall();

	List adminfindfd_clientfordidandtype(Integer dataprocess_id, int flag);

	List adminfindfd_clientlistfordpid(int dataprocess_id);

	List adminfindaccount(int clientNum, String clientName);

	

	

	

	
}

