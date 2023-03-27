package com.hd.microblog.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;

import com.hd.common.service.IBaseService;
import com.hd.microblog.model.dt_realschedule;


public interface dt_realscheduleService  extends IBaseService<dt_realschedule, Integer> {
	List adminfindrealschedulelist(String orcode,String gjcode,String opcode,Integer start, int number);

	List adminfindrealschedulelistcount(String orcode,String gjcode,String opcode);

	List adminfindcpname(String cpcode);

	List adminfindgjname(String gjcode);

	List adminfindopname(String opcode);

	List adminfindjqname(String jqcode);

}