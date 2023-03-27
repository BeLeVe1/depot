package com.hd.microblog.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fd_outbound_category")
public class fd_outbound_category implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	public int outbound_categoryNum;
	public String outbound_categoryName;
	
	public int getoutbound_categoryNum() {
		return outbound_categoryNum;
	}
	public void setoutbound_categoryNum(int outbound_categoryNum) {
		this.outbound_categoryNum = outbound_categoryNum;
	}
	public String getoutbound_categoryName() {
		return outbound_categoryName;
	}
	public void setoutbound_categoryName(String outbound_categoryName) {
		this.outbound_categoryName = outbound_categoryName;
	}
	
	
}
