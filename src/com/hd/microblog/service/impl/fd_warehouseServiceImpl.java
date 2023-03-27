package com.hd.microblog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hd.common.dao.IBaseDao;
import com.hd.common.service.impl.BaseService;
import com.hd.microblog.dao.fd_warehouseDao;

import com.hd.microblog.model.fd_warehouse;

import com.hd.microblog.service.fd_warehouseService;

@Service("fd_warehouseService")
public class fd_warehouseServiceImpl extends BaseService<fd_warehouse, Integer> implements fd_warehouseService{
	private fd_warehouseDao fd_warehousedao;
	@Autowired
	@Qualifier("fd_warehouseDao")
	@Override
	public void setBaseDao(IBaseDao<fd_warehouse, Integer> fd_warehousedao) {
		this.baseDao = fd_warehousedao;
		this.fd_warehousedao = (fd_warehouseDao) fd_warehousedao;
	}
	@Override
	public List adminfindfd_warehouselist1(String jqcode,String bdcode,Integer start, int number) {
		// TODO Auto-generated method stub		
		List<Object> paramlist = new ArrayList();
		String sql = "select * from fd_warehouse where 1=1 ";
		if(jqcode!=""){
			sql+=" and warehouseNum=? ";
			paramlist.add(jqcode);
		}
		if(bdcode!=""){
			sql+=" and warehouseName=? ";
			paramlist.add(bdcode);
		}
		sql += " limit ?, ? ";
		paramlist.add(start);
		paramlist.add(number);
		return fd_warehousedao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindfd_warehouselistcount1(String jqcode,String bdcode) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from fd_warehouse where 1=1 ";;
		if(jqcode!=""){
			sql+=" and warehouseNum=? ";
			paramlist.add(jqcode);
		}
		if(bdcode!=""){
			sql+=" and warehouseName=? ";
			paramlist.add(bdcode);
		}
		
		return fd_warehousedao.exesqlrelist(sql, paramlist);
	}

	
	@Override
	public List adminfindfd_warehouselistcount(String warehouseName) {
		// TODO Auto-generated method stub
		String sql = "From fd_warehouse where warehouseName=?";
		List<Object> paramlist = new ArrayList();
		paramlist.add(warehouseName);
		return fd_warehousedao.exehqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindfd_warehouselist(Integer start, int number) {
		// TODO Auto-generated method stub
		String sql = "select * from fd_warehouse where 1=1 ";
		sql += " limit ?, ? ";
		List<Object> paramlist = new ArrayList();
		paramlist.add(start);
		paramlist.add(number);
		return fd_warehousedao.exesqlrelist(sql, paramlist);
	}
	
	
	
	@Override
	public List adminfindfd_warehouselist() {
		// TODO Auto-generated method stub
		String sql = "select * from fd_warehouse where 1=1 ";
		return fd_warehousedao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfindfd_warehouselistall() {
		// TODO Auto-generated method stub
		String sql = "select * from fd_warehouse";
		return fd_warehousedao.exesqlrelist(sql, null);
	}
	
	@Override
	public List adminfindfd_warehousefordidandtype(Integer dataprocess_id, int flag) {
		// TODO Auto-generated method stub
		String sql = "select * from fd_warehouse where dataprocess_id="+dataprocess_id+" and ycff="+flag;
		return fd_warehousedao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfindfd_warehouselistfordpid(int dataprocess_id) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select sd.sharedpart_id,sd.kyd,pt.ycz11,pt.bzc11,pt.fx11,pt.zsz1,pt.zsz2,pt.zsz3,pt.zsz4,pt.zsz5,pt.zsz6,pt.zsz7,pt.zsz8,pt.zsz9,pt.zsz10 from dt_sharedpart sd left join dt_dataprocess dp on sd.dataprocess_id=dp.dataprocess_id left join dt_prediction pt on dp.dataprocess_id=pt.dataprocess_id where pt.ycff=dp.ycff and pt.dataprocess_id=? ";
		paramlist.add(dataprocess_id);
		return fd_warehousedao.exesqlrelist(sql, paramlist);
	}
	
	
	@Override
	public List adminfindaccount(int warehouseNum, String warehouseName,  String warehouseFreight) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from fd_warehouse where 1=1 ";;
		if(warehouseNum!=0){
			sql+=" and warehouseNum=? ";
			paramlist.add(warehouseNum);
		}
		return fd_warehousedao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindfd_warehouselistcount(String warehouseName, String warehouseFreight) {
		// TODO 自动生成的方法存根
		return null;
	}
	@Override
	public List adminfindfd_warehouselist(String warehouseName, String warehouseFreight, Integer start, int number) {
		// TODO 自动生成的方法存根
		return null;
	}
}
