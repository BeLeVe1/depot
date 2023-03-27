package com.hd.microblog.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fd_people")
public class fd_people implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	public int perNum;
	public String perName;
	public String phoneNum;
	public String perWork;
	
	public int getPerNum() {
		return perNum;
	}
	public void setPerNum(int perNum) {
		this.perNum = perNum;
	}
	public String getPerName() {
		return perName;
	}
	public void setPerName(String perName) {
		this.perName = perName;
	}
	
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getPerWork() {
		return perWork;
	}
	public void setPerWork(String perWork) {
		this.perWork = perWork;
	}
	
	
	
}
