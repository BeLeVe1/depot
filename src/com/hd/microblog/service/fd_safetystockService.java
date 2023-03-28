package com.hd.microblog.service;

import java.util.List;

import com.hd.common.service.IBaseService;
import com.hd.microblog.model.fd_safetystock;

public interface fd_safetystockService extends IBaseService<fd_safetystock, Integer> {

	List adminfindfd_safetystocklist(String sparepartNum, String sparepartName, String sparepartSpecification,
									 String ss, String R, String maxInventory,String sstime,
									 String sort,
									 Integer start, int number);
	
	List adminfindfd_safetystocklist();
	
	List adminfindstorerecordlist();
	
	List adminfindfd_safetystocklistcount(String sparepartNum, String sparepartName, String sparepartSpecification,
										  String ss, String R, String maxInventory,String sstime);
	
	List adminfindstorerecordlist(String jqcode, String from_store,String to_store,
			String qccode, String qcname, String sort,
			Integer start, int number);
	
	List adminfindstorerecordlistcount(String jqcode, String from_store,
			String to_store, String qccode, String qcname);
	
	//读取目标仓库库存
	List desstorecount(String jqcode, String desbdcode, String qccode, String qcname);
	//更新目标仓库
	void updatestorehouse(String jqcode, String bdcode,String desbdcode,String qccode, String qcname,Integer new_store,Integer old_store);	
	//插入目标仓库
	void insertstorehouse(String jqcode, String bdcode,String desbdcode,String zbcode,String qccode, String qcname,String unit,String unitprice,String dzys,Integer new_store,Integer old_store);
	//生成仓库调拨记录
	void insertstorerecord(String jqcode, String bdcode,String desbdcode,String zbcode,String qccode, String qcname,Integer recordnumber,String create_people,String createtime,String receive_people);
	
	List admindeletefd_SafetyStocklist();
	//这个是干嘛的来着？
	void adminrealsupplyedit();
	
	void refreshstorerecord(Integer allot_id,String jqcode,String from_store,String to_store,
			String qccode,String qcname,Integer this_allot_number,Integer old_allot_number);

	List querybdcode(String jqcode,String qccode);
	
}
