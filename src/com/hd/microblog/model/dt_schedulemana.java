package com.hd.microblog.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dt_schedulemana")
public class dt_schedulemana implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	public int schedulemana_id;
	
	public String schedule_number;
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
	
	public String scsttime;
	public String scedtime;
	public double scpctime;
	
	public int getschedulemana_id() {
		return schedulemana_id;
	}
	public void setschedulemana_id(int schedulemana_id) {
		this.schedulemana_id = schedulemana_id;
	}
	
	public String getschedule_number() {
		return schedule_number;
	}
	public void setschedule_number(String schedule_number) {
		this.schedule_number = schedule_number;
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
	
	public String getscsttime() {
		return scsttime;
	}
	public void setscsttime(String scsttime) {
		this.scsttime = scsttime;
	}
	public String getscedtime() {
		return scedtime;
	}
	public void setscedtime(String scedtime) {
		this.scedtime = scedtime;
	}
	public double getscpctime() {
		return scpctime;
	}
	public void setscpctime(double scpctime) {
		this.scpctime = scpctime;
	}
	
}
