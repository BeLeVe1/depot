package com.hd.microblog.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fd_outbusiness")
public class fd_outbusiness implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	public int outbusinessNum;
	public String outbusinessName;
	
	public int getoutbusinessNum() {
		return outbusinessNum;
	}
	public void setoutbusinessNum(int outbusinessNum) {
		this.outbusinessNum = outbusinessNum;
	}
	public String getoutbusinessName() {
		return outbusinessName;
	}
	public void setoutbusinessName(String outbusinessName) {
		this.outbusinessName = outbusinessName;
	}
	
	
}
