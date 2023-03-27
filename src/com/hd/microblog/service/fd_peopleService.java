package com.hd.microblog.service;

import java.util.List;
import java.util.Map;

import com.hd.common.service.IBaseService;
import com.hd.microblog.model.fd_people;


public interface fd_peopleService  extends IBaseService<fd_people, Integer> {

	

	

	

	List adminfindfd_peoplelistall();

	

	List adminfindaccount(int perNum, String perName, String phoneNum, String perWork);



	List adminfindfd_peoplelist();



	List adminfindfd_peoplelistcount(String perName, String perWork);



	List adminfindfd_peoplelist(String perName, String perWork, Integer start, int number);



	List adminfindfd_peoplelistcount(String perName);



	List adminfindfd_peoplelist(Integer start, int number);



	List adminfindfd_peoplefordidandtype(Integer dataprocess_id, int flag);



	List adminfindfd_peoplelistfordpid(int dataprocess_id);



	List adminfindfd_peoplelist1(String jqcode, String bdcode, Integer start, int number);



	List adminfindfd_peoplelistcount1(String jqcode, String bdcode);




	

	

	
	
}

