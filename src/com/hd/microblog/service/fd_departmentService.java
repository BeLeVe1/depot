package com.hd.microblog.service;

import java.util.List;
import java.util.Map;

import com.hd.common.service.IBaseService;
import com.hd.microblog.model.fd_department;


public interface fd_departmentService  extends IBaseService<fd_department, Integer> {

	List adminfindfd_departmentlist1(String jqcode, String bdcode, Integer start, int number);

	List adminfindfd_departmentlistcount1(String jqcode, String bdcode);

	List adminfindfd_departmentlistcount(String departmentName);

	List adminfindfd_departmentlist(Integer start, int number);

	List adminfindfd_departmentlist();

	List adminfindfd_departmentlistall();

	List adminfindfd_departmentfordidandtype(Integer dataprocess_id, int flag);

	List adminfindfd_departmentlistfordpid(int dataprocess_id);

	List adminfindaccount(int departmentNum, String departmentName);

	

	

	

	
}

