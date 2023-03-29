package com.hd.microblog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hd.common.dao.IBaseDao;
import com.hd.common.service.impl.BaseService;
import com.hd.microblog.dao.dt_allotrecordDao;
import com.hd.microblog.model.dt_allotrecord;
import com.hd.microblog.service.dt_allotrecordService;

@Service("dt_allotrecordService")
public class dt_allotrecordServiceImpl extends BaseService<dt_allotrecord, Integer> implements dt_allotrecordService{

	private dt_allotrecordDao dt_allotrecorddao;
	@Autowired
	@Qualifier("dt_allotrecordDao")
	@Override
	public void setBaseDao(IBaseDao<dt_allotrecord, Integer> dt_allotrecorddao) {
		this.baseDao = dt_allotrecorddao;
		this.dt_allotrecorddao = (dt_allotrecordDao) dt_allotrecorddao;
	}
		
	@Override
	public List adminfindsupplyplanlist(String sparepartsNum,
										String sparepartsName,
										String sparepartsSpecification,
										String sparepartsType,
										String warehouseNum,
										String warehouseName,
										String warehouseFreight,
										String customerName,
										String ContractNum,
										String ContractName,
										String outdate,
										String auditor,
										String auditTime,
										String modifiedName,
										String modifiedDate,
										String modifiedTime,
										String preparationTime,
										String sort, Integer start, int number) {
		// TODO Auto-generated method stub
		String sql = "select * from fd_out where 1=1 ";
		List<Object> paramlist = new ArrayList();

		if(sparepartsNum!=""){
			sql+=" and sparepartsNum=? ";
			paramlist.add(sparepartsNum);
		}
		if(sparepartsName!=""){
			sql+=" and sparepartsName=? ";
			paramlist.add(sparepartsName);
		}
		if(sparepartsSpecification!=""){
			sql+=" and sparepartsSpecification=? ";
			paramlist.add(sparepartsSpecification);
		}
		if(sparepartsType!=""){
			sql+=" and sparepartsType=? ";
			paramlist.add(sparepartsType);
		}
		if(warehouseNum!=""){
			sql+=" and warehouseNum=? ";
			paramlist.add(warehouseNum);
		}
		if(warehouseName!=""){
			sql+=" and warehouseName=? ";
			paramlist.add(warehouseName);
		}

		if(customerName!=""){
			sql+=" and customerName=? ";
			paramlist.add(customerName);
		}
		if(ContractNum!=""){
			sql+=" and ContractNum=? ";
			paramlist.add(ContractNum);
		}
//
		if(ContractName!=""){
			sql+=" and ContractName=? ";
			paramlist.add(ContractName);
		}
		if(outdate!=""){
			sql+=" and outdate=? ";
			paramlist.add(outdate);
		}
		if(auditor!=""){
			sql+=" and auditor=? ";
			paramlist.add(auditor);
		}
		if(auditTime!=""){
			sql+=" and auditTime=? ";
			paramlist.add(auditTime);
		}
		if(modifiedName!=""){
			sql+=" and modifiedName=? ";
			paramlist.add(modifiedName);
		}
		if(modifiedDate!=""){
			sql+=" and modifiedDate=? ";
			paramlist.add(modifiedDate);
		}

		if(modifiedTime!=""){
			sql+=" and modifiedTime=? ";
			paramlist.add(modifiedTime);
		}
		if(preparationTime!=""){
			sql+=" and preparationTime=? ";
			paramlist.add(preparationTime);
		}
		if(sort!=""){
			sql+=" order by "+sort+" desc ";
		}
		sql += " limit ?, ? ";
		paramlist.add(start);
		paramlist.add(number);
		System.out.println(paramlist);
		System.out.printf("sql代码为：",sql);
		return dt_allotrecorddao.exesqlrelist(sql, paramlist);
	}

	@Override
	public List adminfindsupplyplanlistcount(String sparepartsNum,
											 String sparepartsName,
											 String sparepartsSpecification,
											 String sparepartsType,
											 String warehouseNum,
											 String warehouseName,
											 String warehouseFreight,
											 String customerName,
											 String ContractNum,
											 String ContractName,
											 String outdate,
											 String auditor,
											 String auditTime,
											 String modifiedName,
											 String modifiedDate,
											 String modifiedTime,
											 String preparationTime) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from fd_out where 1=1 ";
		if(sparepartsNum!=""){
			sql+=" and sparepartsNum=? ";
			paramlist.add(sparepartsNum);
		}
		if(sparepartsName!=""){
			sql+=" and sparepartsName=? ";
			paramlist.add(sparepartsName);
		}
		if(sparepartsSpecification!=""){
			sql+=" and sparepartsSpecification=? ";
			paramlist.add(sparepartsSpecification);
		}
		if(sparepartsType!=""){
			sql+=" and sparepartsType=? ";
			paramlist.add(sparepartsType);
		}
		if(warehouseNum!=""){
			sql+=" and warehouseNum=? ";
			paramlist.add(warehouseNum);
		}
		if(warehouseName!=""){
			sql+=" and warehouseName=? ";
			paramlist.add(warehouseName);
		}

		if(customerName!=""){
			sql+=" and customerName=? ";
			paramlist.add(customerName);
		}
		if(ContractNum!=""){
			sql+=" and ContractNum=? ";
			paramlist.add(ContractNum);
		}
//
		if(ContractName!=""){
			sql+=" and ContractName=? ";
			paramlist.add(ContractName);
		}
		if(outdate!=""){
			sql+=" and outdate=? ";
			paramlist.add(outdate);
		}
		if(auditor!=""){
			sql+=" and auditor=? ";
			paramlist.add(auditor);
		}
		if(auditTime!=""){
			sql+=" and auditTime=? ";
			paramlist.add(auditTime);
		}
		if(modifiedName!=""){
			sql+=" and modifiedName=? ";
			paramlist.add(modifiedName);
		}
		if(modifiedDate!=""){
			sql+=" and modifiedDate=? ";
			paramlist.add(modifiedDate);
		}

		if(modifiedTime!=""){
			sql+=" and modifiedTime=? ";
			paramlist.add(modifiedTime);
		}
		if(preparationTime!=""){
			sql+=" and preparationTime=? ";
			paramlist.add(preparationTime);
		}
//		sql += " limit ?, ? ";
		System.out.println(paramlist);
		System.out.printf("sql代码为：",sql);
		return dt_allotrecorddao.exesqlrelist(sql, paramlist);
	}

	@Override
	public List sumallotrecord(String bdcode,String qccode, String qcname) {
		// TODO Auto-generated method stub
		String sql = "select * from dt_datarecordlist where bdcode="+bdcode+" and qccode="+qccode+" and qcname= '"+qcname+"' ";
		System.out.println(bdcode+qcname);
		List<Object> paramlist = new ArrayList();
		sql+=" order by sum_allot_number desc ";
		//sql+= " limit 1 ";
		System.out.println(paramlist);
		System.out.printf("sql代码为：",sql);
		return dt_allotrecorddao.exesqlrelist(sql, paramlist);
	}

	@Override
	public List adminfindsupplyplanlist() {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select * from dt_datarecordlist where 1=1 ";
		System.out.printf("sql代码为：",sql);
		return dt_allotrecorddao.exesqlrelist(sql, paramlist);
	}

	@Override
	public void refreshallotrecord(Integer allot_id, String jqcode,
			String bdcode, String from_store, String qccode, String qcname,
			Integer this_allot_number, Integer old_allot_number,Integer sum_allot_number) {
		// TODO Auto-generated method stub
		//更新调拨记录表
		//更新当次的调拨量
		String sql = "update dt_datarecordlist set this_allot_number="+this_allot_number+" where allot_id='"+allot_id+"' and jqcode='"+jqcode+"' and bdcode='"+bdcode+"' and qccode='"+qccode+"' and qcname='"+qcname+"'";
		dt_allotrecorddao.addSumplan(sql);
		//更新相关记录的所有累计调拨量
		if(old_allot_number>this_allot_number){
			Integer differ_allotnumber=old_allot_number-this_allot_number;
			//旧的调拨数量更大，累计调拨数量减少
			String sql2 = "update dt_datarecordlist set sum_allot_number=sum_allot_number-"+differ_allotnumber+" where jqcode='"+jqcode+"' and bdcode='"+bdcode+"' and qccode='"+qccode+"' and qcname='"+qcname+"' and sum_allot_number>="+sum_allot_number+"";
			//仓库对应增加,注意仓库的bdcode是指的仓库号，不是部队号！
			String sql3 = "update dt_storehouse set currentinventory=currentinventory+"+differ_allotnumber+" where jqcode='"+jqcode+"' and bdcode='"+bdcode+"' and qccode='"+qccode+"' and qcname='"+qcname+"'";
			//再跟新dt_sumplan的总表,对应的是所有仓库的库存，所以不用加上仓库号判断
			String sql4 = "update dt_sumplan set currentinventory=currentinventory+"+differ_allotnumber+" where jqcode='"+jqcode+"' and bdcode='"+bdcode+"' and qccode='"+qccode+"' and qcname='"+qcname+"'";
			dt_allotrecorddao.addSumplan(sql2);
			dt_allotrecorddao.addSumplan(sql3);
			dt_allotrecorddao.addSumplan(sql4);
		}else{
			Integer differ_allotnumber=this_allot_number-old_allot_number;
			//旧的调拨量少，累计增加，反着来就行
			String sql2 = "update dt_datarecordlist set sum_allot_number=sum_allot_number+"+differ_allotnumber+" where jqcode='"+jqcode+"' and bdcode='"+bdcode+"' and qccode='"+qccode+"' and qcname='"+qcname+"' and sum_allot_number>="+sum_allot_number+"";
			String sql3 = "update dt_storehouse set currentinventory=currentinventory-"+differ_allotnumber+" where jqcode='"+jqcode+"' and bdcode='"+bdcode+"' and qccode='"+qccode+"' and qcname='"+qcname+"'";
			String sql4 = "update dt_sumplan set currentinventory=currentinventory-"+differ_allotnumber+" where jqcode='"+jqcode+"' and bdcode='"+bdcode+"' and qccode='"+qccode+"' and qcname='"+qcname+"'";
			dt_allotrecorddao.addSumplan(sql2);
			dt_allotrecorddao.addSumplan(sql3);
			dt_allotrecorddao.addSumplan(sql4);
		}
		System.out.println("执行完了");
	}

	@Override
	public void refreshstorehouse(String jqcode, String from_store,
			String qccode, String qcname, Integer this_allot_number,
			Integer old_allot_number) {
		// TODO Auto-generated method stub
		
		String sql = "";
		dt_allotrecorddao.addSumplan(sql);
		System.out.println(sql);
	}

}
