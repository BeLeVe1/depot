package com.hd.microblog.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fd_inbound_category")
public class fd_inbound_category implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	public int inbound_categoryNum;
	public String inbound_categoryName;
	
	public int getInbound_categoryNum() {
		return inbound_categoryNum;
	}
	public void setInbound_categoryNum(int inbound_categoryNum) {
		this.inbound_categoryNum = inbound_categoryNum;
	}
	public String getInbound_categoryName() {
		return inbound_categoryName;
	}
	public void setInbound_categoryName(String inbound_categoryName) {
		this.inbound_categoryName = inbound_categoryName;
	}
	
	
}
