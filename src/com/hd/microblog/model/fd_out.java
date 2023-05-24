package com.hd.microblog.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fd_out")

public class fd_out implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	public int outid;
	public String sparepartsNum;
	public String sparepartsName;
	public String sparepartsSpecification;
	public String sparepartsType;
	

	public String warehouseNum;
	public String warehouseName;
	public String warehouseFreight;
	public String sparepartsUnit;
	public String quantity;
	public String unitPrice;
	public String price;
	public String customerName;
	public String ContractNum;
	public String ContractName;
	public String outdate;
	public String auditor;
	public String auditTime;
	public String modifiedName;
	public String modifiedDate;
	public String modifiedTime;
	public String preparationTime;

	
	public int sort;
	public int start;
	public int number;
	

	
	public String getSparepartsType() {
		return sparepartsType;
	}
	public void setSparepartsType(String sparepartsType) {
		this.sparepartsType = sparepartsType;
	}
	
	public String getSparepartsSpecification() {
		return sparepartsSpecification;
	}
	public void setSparepartsSpecification(String sparepartsSpecification) {
		this.sparepartsSpecification = sparepartsSpecification;
	}
	

	
	public String getWarehouseNum() {
		return warehouseNum;
	}
	public void setWarehouseNum(String warehouseNum) {
		this.warehouseNum = warehouseNum;
	}
	
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	
	public String getWarehouseFreight() {
		return warehouseFreight;
	}
	public void setWarehouseFreight(String warehouseFreight) {
		this.warehouseFreight = warehouseFreight;
	}
	
	public String getSparepartsUnit() {
		return sparepartsUnit;
	}

	public void setSparepartsUnit(String sparepartsUnit) {
		this.sparepartsUnit = sparepartsUnit;
	}

	public void settotal_price(String total_price) {
		this.quantity = total_price;
	}

	public void setcustomer_name(String customer_name) {
		this.unitPrice = customer_name;
	}

	public void setchecker(String checker) {
		this.price = checker;
	}

	public void setcontract(String contract) {
		this.customerName = contract;
	}

	public void setreviser(String reviser) {
		this.ContractNum = reviser;
	}

	public void setmodification_date(String modification_date) {
		this.ContractName = modification_date;
	}

	public void setpreparation_time(String preparation_time) {
		this.outdate = preparation_time;
	}
	public void setAudit_time(String Audit_time) {
		this.auditor = Audit_time;
	}

	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}

}
