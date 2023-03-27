package com.hd.microblog.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fd_spareparts")
public class fd_spareparts implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	
	public int sparepartsNum;
	public String sparepartsType;
	public String sparepartsName;
	
	public String sparepartsSpecification;
	
	public int sparepartsStocks;
	public String sparepartsUnit;
	public double sparepartsPrice;
	
  public int getSparepartsNum() {
	    return sparepartsNum;
	  }

	  public void setSparepartsNum(int sparepartsNum) {
	    this.sparepartsNum = sparepartsNum;
	  }


	  public String getSparepartsType() {
	    return sparepartsType;
	  }

	  public void setSparepartsType(String sparepartsType) {
	    this.sparepartsType = sparepartsType;
	  }


	  public String getSparepartsName() {
	    return sparepartsName;
	  }

	  public void setSparepartsName(String sparepartsName) {
	    this.sparepartsName = sparepartsName;
	  }


	  public String getSparepartsSpecification() {
	    return sparepartsSpecification;
	  }

	  public void setSparepartsSpecification(String sparepartsSpecification) {
	    this.sparepartsSpecification = sparepartsSpecification;
	  }


	  public int getSparepartsStocks() {
	    return sparepartsStocks;
	  }

	  public void setSparepartsStocks(int sparepartsStocks) {
	    this.sparepartsStocks = sparepartsStocks;
	  }


	  public String getSparepartsUnit() {
	    return sparepartsUnit;
	  }

	  public void setSparepartsUnit(String sparepartsUnit) {
	    this.sparepartsUnit = sparepartsUnit;
	  }


	  public double getSparepartsPrice() {
	    return sparepartsPrice;
	  }

	  public void setSparepartsPrice(double sparepartsPrice) {
	    this.sparepartsPrice = sparepartsPrice;
	  }

	
	
	
}
