package com.hd.microblog.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dt_sharedpart")
public class dt_sharedpart implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	public int sharedpart_id;
	public int dataprocess_id;
	public String zbzyd;
	public int predictionnumber;
	public int maxnumber;
	public int plannumber;
	public double kyd;
	public int realnumber;
	
	public int getSharedpart_id() {
		return sharedpart_id;
	}
	public void setSharedpart_id(int sharedpart_id) {
		this.sharedpart_id = sharedpart_id;
	}
	public int getDataprocess_id() {
		return dataprocess_id;
	}
	public void setDataprocess_id(int dataprocess_id) {
		this.dataprocess_id = dataprocess_id;
	}
	public String getZbzyd() {
		return zbzyd;
	}
	public void setZbzyd(String zbzyd) {
		this.zbzyd = zbzyd;
	}
	public int getPredictionnumber() {
		return predictionnumber;
	}
	public void setPredictionnumber(int predictionnumber) {
		this.predictionnumber = predictionnumber;
	}
	public int getMaxnumber() {
		return maxnumber;
	}
	public void setMaxnumber(int maxnumber) {
		this.maxnumber = maxnumber;
	}
	public int getPlannumber() {
		return plannumber;
	}
	public void setPlannumber(int plannumber) {
		this.plannumber = plannumber;
	}
	public double getKyd() {
		return kyd;
	}
	public void setKyd(double kyd) {
		this.kyd = kyd;
	}
	public double getRealnumber() {
		return realnumber;
	}
	public void setRealnumber(int realnumber) {
		this.realnumber = realnumber;
	}
}
