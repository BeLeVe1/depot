package com.hd.microblog.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;

import com.hd.common.service.IBaseService;
import com.hd.microblog.model.dt_orderlist;

public interface dt_orderlistService  extends IBaseService<dt_orderlist, Integer> {
	
	List adminfindorderlist(String orcode,String cpname,String cpcode,String gjname,String gjcode,String orstat,String gjstat,Integer start, int number);

	List adminfindorderlistcount(String orcode,String cpname,String cpcode,String gjname,String gjcode,String orstat,String gjstat);

	List adminfindorderlist(String select_order_list);
	
	List adminfindallorderlist();
	
	List adminselectorderlistcount(String select_order_list);
}
