package com.hd.microblog.service;

import java.util.List;
import java.util.Map;

import com.hd.common.service.IBaseService;
import com.hd.microblog.model.fd_windfarm;


public interface fd_windfarmService  extends IBaseService<fd_windfarm, Integer> {

	

	

	

	List adminfindfd_windfarmlistall();

	

	List adminfindaccount(int windfarmNum, String windfarmName,  String windfarmRegion);



	List adminfindfd_windfarmlist();



	List adminfindfd_windfarmlistcount(String windfarmName, String windfarmRegion);



	List adminfindfd_windfarmlist(String windfarmName, String windfarmRegion, Integer start, int number);



	List adminfindfd_windfarmlistcount(String windfarmName);



	List adminfindfd_windfarmlist(Integer start, int number);



	List adminfindfd_windfarmfordidandtype(Integer dataprocess_id, int flag);



	List adminfindfd_windfarmlistfordpid(int dataprocess_id);



	List adminfindfd_windfarmlist1(String jqcode, String bdcode, Integer start, int number);



	List adminfindfd_windfarmlistcount1(String jqcode, String bdcode);




	

	

	
	
}

