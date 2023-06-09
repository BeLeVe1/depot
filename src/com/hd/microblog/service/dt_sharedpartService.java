package com.hd.microblog.service;

import java.util.List;
import java.util.Map;

import com.hd.common.service.IBaseService;
import com.hd.microblog.model.dt_sharedpart;


public interface dt_sharedpartService  extends IBaseService<dt_sharedpart, Integer> {

	List adminfindsharedpartlist(String jqcode,String bdcode,String zbcode,String qccode,String qcname,String fg,String sort,Integer start, int number);

	List adminfindsharedpartlistcount(String jqcode,String bdcode,String zbcode,String qccode,String qcname,String fg);

	List adminfindsharedpartlist(String fg);

	List adminfindsharedpartfordataprocess_id(int dataprocess_id);

	List adminfindsharedpartlistall();

	List adminfindsharedpartlistforfg(String fg);

	List adminfindsharedpartlistsum(Integer jqcode, Integer qccode);

	List adminfindsharedpartlistsum(Integer qccode);

	List adminfindsharedpartlistsum2(Integer bdcode, String qcname);
	
	List adminfindsharedpartlistsum3(Integer jqcode,String bdcode,String qccode, String qcname);
	
	List adminfindsharedpartlistsum4(Integer jqcode,String bdcode,String qccode, String qcname);

	List adminfindsharedpartlistsum2(String qcname);
	
}

