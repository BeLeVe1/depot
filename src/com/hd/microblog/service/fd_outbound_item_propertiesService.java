package com.hd.microblog.service;

import java.util.List;
import java.util.Map;

import com.hd.common.service.IBaseService;
import com.hd.microblog.model.fd_outbound_item_properties;


public interface fd_outbound_item_propertiesService  extends IBaseService<fd_outbound_item_properties, Integer> {

	List adminfindfd_outbound_item_propertieslist1(String jqcode, String bdcode, Integer start, int number);

	List adminfindfd_outbound_item_propertieslistcount1(String jqcode, String bdcode);

	List adminfindfd_outbound_item_propertieslistcount(String outbound_item_propertiesName);

	List adminfindfd_outbound_item_propertieslist(Integer start, int number);

	List adminfindfd_outbound_item_propertieslist();

	List adminfindfd_outbound_item_propertieslistall();

	List adminfindfd_outbound_item_propertiesfordidandtype(Integer dataprocess_id, int flag);

	List adminfindfd_outbound_item_propertieslistfordpid(int dataprocess_id);

	List adminfindaccount(int outbound_item_propertiesNum, String outbound_item_propertiesName);

	

	

	

	
}

