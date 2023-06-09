package com.hd.microblog.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dt_prediction")
public class dt_prediction implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	public int prediction_id;
	public int dataprocess_id;
	public String ycz1;
	public String ycz2;
	public String ycz3;
	public String ycz4;
	public String ycz5;
	public String ycz6;
	public String ycz7;
	public String ycz8;
	public String ycz9;
	public String ycz10;
	public String ycz11;
	public String ycz12;
	public String bzc1;
	public String bzc2;
	public String bzc3;
	public String bzc4;
	public String bzc5;
	public String bzc6;
	public String bzc7;
	public String bzc8;
	public String bzc9;
	public String bzc10;
	public String bzc11;
	public String bzc12;
	public String zsz1;
	public String zsz2;
	public String zsz3;
	public String zsz4;
	public String zsz5;
	public String zsz6;
	public String zsz7;
	public String zsz8;
	public String zsz9;
	public String zsz10;
	public String zsz11;
	public String zsz12;
	public String fx1;
	public String fx2;
	public String fx3;
	public String fx4;
	public String fx5;
	public String fx6;
	public String fx7;
	public String fx8;
	public String fx9;
	public String fx10;
	public String fx11;
	public String fx12;
	public Integer ycff;
	public int getPrediction_id() {
		return prediction_id;
	}
	public void setPrediction_id(int prediction_id) {
		this.prediction_id = prediction_id;
	}
	public int getDataprocess_id() {
		return dataprocess_id;
	}
	public void setDataprocess_id(int dataprocess_id) {
		this.dataprocess_id = dataprocess_id;
	}
	public String getYcz1() {
		return ycz1;
	}
	public void setYcz1(String ycz1) {
		this.ycz1 = ycz1;
	}
	public String getYcz2() {
		return ycz2;
	}
	public void setYcz2(String ycz2) {
		this.ycz2 = ycz2;
	}
	public String getYcz3() {
		return ycz3;
	}
	public void setYcz3(String ycz3) {
		this.ycz3 = ycz3;
	}
	public String getYcz4() {
		return ycz4;
	}
	public void setYcz4(String ycz4) {
		this.ycz4 = ycz4;
	}
	public String getYcz5() {
		return ycz5;
	}
	public void setYcz5(String ycz5) {
		this.ycz5 = ycz5;
	}
	public String getYcz6() {
		return ycz6;
	}
	public void setYcz6(String ycz6) {
		this.ycz6 = ycz6;
	}
	public String getYcz7() {
		return ycz7;
	}
	public void setYcz7(String ycz7) {
		this.ycz7 = ycz7;
	}
	public String getYcz8() {
		return ycz8;
	}
	public void setYcz8(String ycz8) {
		this.ycz8 = ycz8;
	}
	public String getYcz9() {
		return ycz9;
	}
	public void setYcz9(String ycz9) {
		this.ycz9 = ycz9;
	}
	public String getYcz10() {
		return ycz10;
	}
	public void setYcz10(String ycz10) {
		this.ycz10 = ycz10;
	}
	public String getYcz11() {
		return ycz11;
	}
	public void setYcz11(String ycz11) {
		this.ycz11 = ycz11;
	}
	public String getYcz12() {
		return ycz12;
	}
	public void setYcz12(String ycz12) {
		this.ycz12 = ycz12;
	}
	public String getBzc1() {
		return bzc1;
	}
	public void setBzc1(String bzc1) {
		this.bzc1 = bzc1;
	}
	public String getBzc2() {
		return bzc2;
	}
	public void setBzc2(String bzc2) {
		this.bzc2 = bzc2;
	}
	public String getBzc3() {
		return bzc3;
	}
	public void setBzc3(String bzc3) {
		this.bzc3 = bzc3;
	}
	public String getBzc4() {
		return bzc4;
	}
	public void setBzc4(String bzc4) {
		this.bzc4 = bzc4;
	}
	public String getBzc5() {
		return bzc5;
	}
	public void setBzc5(String bzc5) {
		this.bzc5 = bzc5;
	}
	public String getBzc6() {
		return bzc6;
	}
	public void setBzc6(String bzc6) {
		this.bzc6 = bzc6;
	}
	public String getBzc7() {
		return bzc7;
	}
	public void setBzc7(String bzc7) {
		this.bzc7 = bzc7;
	}
	public String getBzc8() {
		return bzc8;
	}
	public void setBzc8(String bzc8) {
		this.bzc8 = bzc8;
	}
	public String getBzc9() {
		return bzc9;
	}
	public void setBzc9(String bzc9) {
		this.bzc9 = bzc9;
	}
	public String getBzc10() {
		return bzc10;
	}
	public void setBzc10(String bzc10) {
		this.bzc10 = bzc10;
	}
	public String getBzc11() {
		return bzc11;
	}
	public void setBzc11(String bzc11) {
		this.bzc11 = bzc11;
	}
	public String getBzc12() {
		return bzc12;
	}
	public void setBzc12(String bzc12) {
		this.bzc12 = bzc12;
	}
	public String getZsz1() {
		return zsz1;
	}
	public void setZsz1(String zsz1) {
		this.zsz1 = zsz1;
	}
	public String getZsz2() {
		return zsz2;
	}
	public void setZsz2(String zsz2) {
		this.zsz2 = zsz2;
	}
	public String getZsz3() {
		return zsz3;
	}
	public void setZsz3(String zsz3) {
		this.zsz3 = zsz3;
	}
	public String getZsz4() {
		return zsz4;
	}
	public void setZsz4(String zsz4) {
		this.zsz4 = zsz4;
	}
	public String getZsz5() {
		return zsz5;
	}
	public void setZsz5(String zsz5) {
		this.zsz5 = zsz5;
	}
	public String getZsz6() {
		return zsz6;
	}
	public void setZsz6(String zsz6) {
		this.zsz6 = zsz6;
	}
	public String getZsz7() {
		return zsz7;
	}
	public void setZsz7(String zsz7) {
		this.zsz7 = zsz7;
	}
	public String getZsz8() {
		return zsz8;
	}
	public void setZsz8(String zsz8) {
		this.zsz8 = zsz8;
	}
	public String getZsz9() {
		return zsz9;
	}
	public void setZsz9(String zsz9) {
		this.zsz9 = zsz9;
	}
	public String getZsz10() {
		return zsz10;
	}
	public void setZsz10(String zsz10) {
		this.zsz10 = zsz10;
	}
	public String getZsz11() {
		return zsz11;
	}
	public void setZsz11(String zsz11) {
		this.zsz11 = zsz11;
	}
	public String getZsz12() {
		return zsz12;
	}
	public void setZsz12(String zsz12) {
		this.zsz12 = zsz12;
	}
	public String getFx1() {
		return fx1;
	}
	public void setFx1(String fx1) {
		this.fx1 = fx1;
	}
	public String getFx2() {
		return fx2;
	}
	public void setFx2(String fx2) {
		this.fx2 = fx2;
	}
	public String getFx3() {
		return fx3;
	}
	public void setFx3(String fx3) {
		this.fx3 = fx3;
	}
	public String getFx4() {
		return fx4;
	}
	public void setFx4(String fx4) {
		this.fx4 = fx4;
	}
	public String getFx5() {
		return fx5;
	}
	public void setFx5(String fx5) {
		this.fx5 = fx5;
	}
	public String getFx6() {
		return fx6;
	}
	public void setFx6(String fx6) {
		this.fx6 = fx6;
	}
	public String getFx7() {
		return fx7;
	}
	public void setFx7(String fx7) {
		this.fx7 = fx7;
	}
	public String getFx8() {
		return fx8;
	}
	public void setFx8(String fx8) {
		this.fx8 = fx8;
	}
	public String getFx9() {
		return fx9;
	}
	public void setFx9(String fx9) {
		this.fx9 = fx9;
	}
	public String getFx10() {
		return fx10;
	}
	public void setFx10(String fx10) {
		this.fx10 = fx10;
	}
	public String getFx11() {
		return fx11;
	}
	public void setFx11(String fx11) {
		this.fx11 = fx11;
	}
	public String getFx12() {
		return fx12;
	}
	public void setFx12(String fx12) {
		this.fx12 = fx12;
	}
	public Integer getYcff() {
		return ycff;
	}
	public void setYcff(Integer ycff) {
		this.ycff = ycff;
	}
	
}
