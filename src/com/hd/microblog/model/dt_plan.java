package com.hd.microblog.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dt_plan")
public class dt_plan implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	public int plan_id;
	public int dataprocess_id;
	public int number;
	public int makingplansnumber;
	public int thistimeplansnumber;
	public int lastnumber;
	public int finaldeternumber;
	
	public int getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}
	public int getDataprocess_id() {
		return dataprocess_id;
	}
	public void setDataprocess_id(int dataprocess_id) {
		this.dataprocess_id = dataprocess_id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getMakingplansnumber() {
		return makingplansnumber;
	}
	public void setMakingplansnumber(int makingplansnumber) {
		this.makingplansnumber = makingplansnumber;
	}
	public int getThistimeplansnumber() {
		return thistimeplansnumber;
	}
	public void setThistimeplansnumber(int thistimeplansnumber) {
		this.thistimeplansnumber = thistimeplansnumber;
	}
	public int getLastnumber() {
		return lastnumber;
	}
	public void setLastnumber(int lastnumber) {
		this.lastnumber = lastnumber;
	}
	public int getFinaldeternumber() {
		return finaldeternumber;
	}
	public void setFinaldeternumber(int finaldeternumber) {
		this.finaldeternumber = finaldeternumber;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
