package com.hd.microblog.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fd_outbound_item_properties")
public class fd_outbound_item_properties implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	public int outbound_item_propertiesNum;
	public String outbound_item_propertiesName;
	
	public int getoutbound_item_propertiesNum() {
		return outbound_item_propertiesNum;
	}
	public void setoutbound_item_propertiesNum(int outbound_item_propertiesNum) {
		this.outbound_item_propertiesNum = outbound_item_propertiesNum;
	}
	public String getoutbound_item_propertiesName() {
		return outbound_item_propertiesName;
	}
	public void setoutbound_item_propertiesName(String outbound_item_propertiesName) {
		this.outbound_item_propertiesName = outbound_item_propertiesName;
	}
	
	
}
