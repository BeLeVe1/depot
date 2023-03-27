package com.hd.microblog.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="dt_touliaomanagement")
public class dt_touliaomanagement implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	public int touliao_id;
	
	public String orcode;
	public String cpname;
	public String cpcode;
	public String gjname;
	public String gjcode;
	
	public int touliaonum;
	public String touliaotime;
	public String touliaostate;


	public int gettouliao_id() {
		return touliao_id;
	}
	public void settouliao_id(int touliao_id) {
		this.touliao_id = touliao_id;
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
	public int gettouliaonum() {
		return touliaonum;
	}
	public void settouliaonum(int touliaonum) {
		this.touliaonum = touliaonum;
	}
	public String gettouliaotime() {
		return touliaotime;
	}
	public void settouliaotime(String touliaotime) {
		this.touliaotime = touliaotime;
	}
	public String gettouliaostate() {
		return touliaostate;
	}
	public void settouliaostate(String touliaostate) {
		this.touliaostate = touliaostate;
	}
}
