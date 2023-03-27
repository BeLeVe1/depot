package com.hd.microblog.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fd_supplier")
public class fd_supplier implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	public int supNum;
	public String supName;
	
	public int getsupNum() {
		return supNum;
	}
	public void setsupNum(int supNum) {
		this.supNum = supNum;
	}
	public String getsupName() {
		return supName;
	}
	public void setsupName(String supName) {
		this.supName = supName;
	}
	
	
}
