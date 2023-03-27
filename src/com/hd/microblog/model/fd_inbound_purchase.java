package com.hd.microblog.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fd_inbound_purchase")
public class fd_inbound_purchase implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	public int inbound_purchaseNum;
	public String inbound_purchaseName;
	
	public int getinbound_purchaseNum() {
		return inbound_purchaseNum;
	}
	public void setinbound_purchaseNum(int inbound_purchaseNum) {
		this.inbound_purchaseNum = inbound_purchaseNum;
	}
	public String getinbound_purchaseName() {
		return inbound_purchaseName;
	}
	public void setinbound_purchaseName(String inbound_purchaseName) {
		this.inbound_purchaseName = inbound_purchaseName;
	}
	
	
}
