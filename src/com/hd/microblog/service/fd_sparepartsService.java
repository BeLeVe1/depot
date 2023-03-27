package com.hd.microblog.service;

import java.util.List;
import java.util.Map;

import com.hd.common.service.IBaseService;
import com.hd.microblog.model.fd_spareparts;


public interface fd_sparepartsService  extends IBaseService<fd_spareparts, Integer> {

	

	

	

	List adminfindfd_sparepartslistall();

	

	List adminfindaccount(int sparepartsNum, String sparepartsType,String sparepartsName, String sparepartsSpecification,int sparepartsStocks,String sparepartsUnit,double sparepartsPrice);



	List adminfindfd_sparepartslist();



//	List adminfindfd_sparepartslistcount(String sparepartsName, String sparepartsType);
//
//
////
//	List adminfindfd_sparepartslist(String sparepartsName, String sparepartsWork, Integer start, int number);
//
//
//
	List adminfindfd_sparepartslistcount(String sparepartsName);



	List adminfindfd_sparepartslist(Integer start, int number);



	List adminfindfd_sparepartsfordidandtype(Integer dataprocess_id, int flag);



	List adminfindfd_sparepartslistfordpid(int dataprocess_id);



	List adminfindfd_sparepartslist1(String jqcode, String bdcode, Integer start, int number);



	List adminfindfd_sparepartslistcount1(String jqcode, String bdcode);




	

	

	
	
}

