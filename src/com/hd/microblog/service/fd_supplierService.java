package com.hd.microblog.service;

import java.util.List;
import java.util.Map;

import com.hd.common.service.IBaseService;
import com.hd.microblog.model.fd_supplier;


public interface fd_supplierService  extends IBaseService<fd_supplier, Integer> {

	List adminfindfd_supplierlist1(String jqcode, String bdcode, Integer start, int number);

	List adminfindfd_supplierlistcount1(String jqcode, String bdcode);

	List adminfindfd_supplierlistcount(String supName);

	List adminfindfd_supplierlist(Integer start, int number);

	List adminfindfd_supplierlist();

	List adminfindfd_supplierlistall();

	List adminfindfd_supplierfordidandtype(Integer dataprocess_id, int flag);

	List adminfindfd_supplierlistfordpid(int dataprocess_id);

	List adminfindaccount(int supNum, String supName);

	

	

	

	
}

