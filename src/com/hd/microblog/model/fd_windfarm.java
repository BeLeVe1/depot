package com.hd.microblog.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fd_windfarm")
public class fd_windfarm implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	public int windfarmNum;
	public String windfarmName;
	
	public String windfarmRegion;
	
	public int getWindfarmNum() {
		return windfarmNum;
	}
	public void setWindfarmNum(int windfarmNum) {
		this.windfarmNum = windfarmNum;
	}
	public String getWindfarmName() {
		return windfarmName;
	}
	public void setWindfarmName(String windfarmName) {
		this.windfarmName = windfarmName;
	}
	

	public String getWindfarmRegion() {
		return windfarmRegion;
	}
	public void setWindfarmRegion(String windfarmRegion) {
		this.windfarmRegion = windfarmRegion;
	}
	
	
	
}
