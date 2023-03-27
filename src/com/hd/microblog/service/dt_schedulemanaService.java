package com.hd.microblog.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;

import com.hd.common.service.IBaseService;
import com.hd.microblog.model.dt_schedulemana;


public interface dt_schedulemanaService  extends IBaseService<dt_schedulemana, Integer> {
	List adminfindschedulemanalist(String schedule_number,String orcode,String gjcode,String opcode,Integer start, int number);

	List adminfindschedulemanalistcount(String schedule_number,String orcode,String gjcode,String opcode);
	
	List adminfindschedulemanadetaillist(String schedule_number,String orcode,String gjcode,String opcode,Integer start, int number);

	List adminfindschedulemanadetaillistcount(String schedule_number,String orcode,String gjcode,String opcode);

}