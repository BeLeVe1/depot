package com.hd.microblog.service;

import java.util.List;
import java.util.Map;

import com.hd.common.service.IBaseService;
import com.hd.microblog.model.fd_warehouse;


public interface fd_warehouseService  extends IBaseService<fd_warehouse, Integer> {

	

	

	

	List adminfindfd_warehouselistall();

	

	List adminfindaccount(int warehouseNum, String warehouseName, String warehouseFreight);



	List adminfindfd_warehouselist();



	List adminfindfd_warehouselistcount(String warehouseName, String warehouseFreight);



	List adminfindfd_warehouselist(String warehouseName, String warehouseFreight, Integer start, int number);



	List adminfindfd_warehouselistcount(String warehouseName);



	List adminfindfd_warehouselist(Integer start, int number);



	List adminfindfd_warehousefordidandtype(Integer dataprocess_id, int flag);



	List adminfindfd_warehouselistfordpid(int dataprocess_id);



	List adminfindfd_warehouselist1(String jqcode, String bdcode, Integer start, int number);



	List adminfindfd_warehouselistcount1(String jqcode, String bdcode);




	

	

	
	
}

