package com.hd.microblog.service;

import java.util.List;
import java.util.Map;

import com.hd.common.service.IBaseService;
import com.hd.microblog.model.fd_inbound_purchase;


public interface fd_inbound_purchaseService  extends IBaseService<fd_inbound_purchase, Integer> {

	List adminfindfd_inbound_purchaselist1(String jqcode, String bdcode, Integer start, int number);

	List adminfindfd_inbound_purchaselistcount1(String jqcode, String bdcode);

	List adminfindfd_inbound_purchaselistcount(String inbound_purchaseName);

	List adminfindfd_inbound_purchaselist(Integer start, int number);

	List adminfindfd_inbound_purchaselist();

	List adminfindfd_inbound_purchaselistall();

	List adminfindfd_inbound_purchasefordidandtype(Integer dataprocess_id, int flag);

	List adminfindfd_inbound_purchaselistfordpid(int dataprocess_id);

	List adminfindaccount(int inbound_purchaseNum, String inbound_purchaseName);

	

	

	

	
}

