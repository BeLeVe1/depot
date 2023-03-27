package com.hd.microblog.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="dt_orderlist")
public class dt_orderlist implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	public int orderdata_id;
	
	public String orcode;
	public String cpname;
	public String cpcode;
	public String gjname;
	public String gjcode;
	public int gjnumb;
	public String orstime;
	public String oretime;
	public String orstat;
	public String gjstat;

	public int getorderdata_id() {
		return orderdata_id;
	}
	public void setorderdata_id(int orderdata_id) {
		this.orderdata_id = orderdata_id;
	}
	
	public String getorcode() {
		return orcode;
	}
	public void setorcode(String orcode) {
		this.orcode = orcode;
	}
	public String getcpname() {
		return cpname;
	}
	public void setcpname(String cpname) {
		this.cpname = cpname;
	}
	public String getcpcode() {
		return cpcode;
	}
	public void setcpcode(String cpcode) {
		this.cpcode = cpcode;
	}
	public String getgjname() {
		return gjname;
	}
	public void setgjname(String gjname) {
		this.gjname = gjname;
	}
	public String getgjcode() {
		return gjcode;
	}
	public void setgjcode(String gjcode) {
		this.gjcode = gjcode;
	}
	public int getgjnumb() {
		return gjnumb;
	}
	public void setgjnumb(int gjnumb) {
		this.gjnumb = gjnumb;
	}
	public String getorstime() {
		return orstime;
	}
	public void setorstime(String orstime) {
		this.orstime = orstime;
	}
	public String getoretime() {
		return oretime;
	}
	public void setoretime(String oretime) {
		this.oretime = oretime;
	}
	public String getorstat() {
		return orstat;
	}
	public void setorstat(String orstat) {
		this.orstat = orstat;
	}
	public String getgjstat() {
		return gjstat;
	}
	public void setgjstat(String gjstat) {
		this.gjstat = gjstat;
	}
}
