package com.hd.microblog.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;

import com.hd.common.service.IBaseService;
import com.hd.microblog.model.dt_touliaomanagement;

public interface dt_touliaomanagementService  extends IBaseService<dt_touliaomanagement, Integer> {
	List adminfindtouliaomanagement(String orcode,String cpname,String cpcode,String gjname,String gjcode, String touliaostate,Integer start, int number);

	List adminfindtouliaomanagementcount(String orcode,String cpname,String cpcode,String gjname,String gjcode, String touliaostate);
	
	List adminfindalltouliaomanagement();
	
}