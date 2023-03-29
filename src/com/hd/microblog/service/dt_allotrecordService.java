package com.hd.microblog.service;

import java.util.List;
import java.util.Map;

import com.hd.common.service.IBaseService;
import com.hd.microblog.model.dt_allotrecord;

public interface dt_allotrecordService extends IBaseService<dt_allotrecord, Integer> {

	List adminfindsupplyplanlist(String sparepartsNum,
								 String sparepartsName,
								 String sparepartsSpecification,
								 String sparepartsType,
								 String warehouseNum,
								 String warehouseName,
								 String warehouseFreight,
								 String customerName,
								 String ContractNum,
								 String ContractName,
								 String outdate,
								 String auditor,
								 String auditTime,
								 String modifiedName,
								 String modifiedDate,
								 String modifiedTime,
								 String preparationTime,
			String sort,Integer start, int number) ;
	
	List adminfindsupplyplanlist() ;
	
	List adminfindsupplyplanlistcount(String sparepartsNum,
									  String sparepartsName,
									  String sparepartsSpecification,
									  String sparepartsType,
									  String warehouseNum,
									  String warehouseName,
									  String warehouseFreight,
									  String customerName,
									  String ContractNum,
									  String ContractName,
									  String outdate,
									  String auditor,
									  String auditTime,
									  String modifiedName,
									  String modifiedDate,
									  String modifiedTime,
									  String preparationTime) ;
	
	List sumallotrecord(String bdcode,String qccode,String qcname);
	
	void refreshallotrecord(Integer allot_id,String jqcode,String bdcode,String from_store,String qccode,String qcname,Integer this_allot_number,Integer old_allot_number,Integer sum_allot_number);

	void refreshstorehouse(String jqcode,String from_store,String qccode,String qcname,Integer this_allot_number,Integer old_allot_number);
}
