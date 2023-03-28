package com.hd.microblog.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="fd_intemp")
public class fd_intemp implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	public int sparepartnum;
	public String month;
	public String quantity;

	public int getSparepartnum() {
		return sparepartnum;
	}
	public void setSparepartnum(int sparepartnum) {
		this.sparepartnum = sparepartnum;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

}
	

