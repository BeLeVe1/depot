package com.hd.microblog.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fd_warehouse")
public class fd_warehouse implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	public int warehouseNum;
	public String warehouseName;
	public String warehouseFreight;


	
	
	public int getWarehouseNum() {
		return warehouseNum;
	}
	public void setWarehouseNum(int warehouseNum) {
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
	
	
	
}
