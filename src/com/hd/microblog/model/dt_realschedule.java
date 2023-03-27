package com.hd.microblog.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dt_realschedule")
public class dt_realschedule implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	public int realschedule_id;
	
	public String orcode;
	public String cpname;
	public String cpcode;
	public String gjname;
	public String gjcode;
	public String opname;
	public String opcode;
	public String jqname;
	public String jqcode;
	
	public String sttime;
	public String edtime;
	public double pctime;
	
	public int getrealschedule_id() {
		return realschedule_id;
	}
	public void setrealschedule_id(int realschedule_id) {
		this.realschedule_id = realschedule_id;
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
	public String getopname() {
		return opname;
	}
	public void setopname(String opname) {
		this.opname = opname;
	}
	public String getopcode() {
		return opcode;
	}
	public void setopcode(String opcode) {
		this.opcode = opcode;
	}
	public String getjqname() {
		return jqname;
	}
	public void setjqname(String jqname) {
		this.jqname = jqname;
	}
	public String getjqcode() {
		return jqcode;
	}
	public void setjqcode(String jqcode) {
		this.jqcode = jqcode;
	}
	
	public String getsttime() {
		return sttime;
	}
	public void setsttime(String sttime) {
		this.sttime = sttime;
	}
	public String getedtime() {
		return edtime;
	}
	public void setedtime(String edtime) {
		this.edtime = edtime;
	}
	public double getpctime() {
		return pctime;
	}
	public void setpctime(double pctime) {
		this.pctime = pctime;
	}
	
}
