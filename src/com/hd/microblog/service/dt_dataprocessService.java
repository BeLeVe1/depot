package com.hd.microblog.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;

import com.hd.common.service.IBaseService;
import com.hd.microblog.model.dt_dataprocess;


public interface dt_dataprocessService  extends IBaseService<dt_dataprocess, Integer> {

	List adminfinddataprocessslist();

	List adminfinddataprocesslist(String jqcode,String bdcode,String zbcode, String qccode, String qcname, String fg, String sort,
			Integer start, int number);

	List adminfinddataprocesslistcount(String jqcode,String bdcode,String zbcode, String qccode, String qcname, String fg);

	List adminfinddataprocessgroup(String fg);

	List adminfinddataprocesslistforqccode(String qccode);

}

