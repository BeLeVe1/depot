package com.hd.microblog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hd.common.dao.IBaseDao;
import com.hd.common.service.impl.BaseService;
import com.hd.microblog.dao.fd_supplierDao;

import com.hd.microblog.model.fd_supplier;

import com.hd.microblog.service.fd_supplierService;

@Service("fd_supplierService")
public class fd_supplierServiceImpl extends BaseService<fd_supplier, Integer> implements fd_supplierService{
	private fd_supplierDao fd_supplierdao;
	@Autowired
	@Qualifier("fd_supplierDao")
	@Override
	public void setBaseDao(IBaseDao<fd_supplier, Integer> fd_supplierdao) {
		this.baseDao = fd_supplierdao;
		this.fd_supplierdao = (fd_supplierDao) fd_supplierdao;
	}
	@Override
	public List adminfindfd_supplierlist1(String jqcode,String bdcode,Integer start, int number) {
		// TODO Auto-generated method stub		
		List<Object> paramlist = new ArrayList();
		String sql = "select * from fd_supplier where 1=1 ";
		if(jqcode!=""){
			sql+=" and supNum=? ";
			paramlist.add(jqcode);
		}
		if(bdcode!=""){
			sql+=" and supName=? ";
			paramlist.add(bdcode);
		}
		sql += " limit ?, ? ";
		paramlist.add(start);
		paramlist.add(number);
		return fd_supplierdao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindfd_supplierlistcount1(String jqcode,String bdcode) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from fd_supplier where 1=1 ";;
		if(jqcode!=""){
			sql+=" and supNum=? ";
			paramlist.add(jqcode);
		}
		if(bdcode!=""){
			sql+=" and supName=? ";
			paramlist.add(bdcode);
		}
		
		return fd_supplierdao.exesqlrelist(sql, paramlist);
	}

	
	@Override
	public List adminfindfd_supplierlistcount(String supName) {
		// TODO Auto-generated method stub
		String sql = "From fd_supplier where supName=?";
		List<Object> paramlist = new ArrayList();
		paramlist.add(supName);
		return fd_supplierdao.exehqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindfd_supplierlist(Integer start, int number) {
		// TODO Auto-generated method stub
		String sql = "select * from fd_supplier where 1=1 ";
		sql += " limit ?, ? ";
		List<Object> paramlist = new ArrayList();
		paramlist.add(start);
		paramlist.add(number);
		return fd_supplierdao.exesqlrelist(sql, paramlist);
	}
	
	
	
	@Override
	public List adminfindfd_supplierlist() {
		// TODO Auto-generated method stub
		String sql = "select * from fd_supplier where 1=1 ";
		return fd_supplierdao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfindfd_supplierlistall() {
		// TODO Auto-generated method stub
		String sql = "select * from fd_supplier";
		return fd_supplierdao.exesqlrelist(sql, null);
	}
	
	@Override
	public List adminfindfd_supplierfordidandtype(Integer dataprocess_id, int flag) {
		// TODO Auto-generated method stub
		String sql = "select * from fd_supplier where dataprocess_id="+dataprocess_id+" and ycff="+flag;
		return fd_supplierdao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfindfd_supplierlistfordpid(int dataprocess_id) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select sd.sharedpart_id,sd.kyd,pt.ycz11,pt.bzc11,pt.fx11,pt.zsz1,pt.zsz2,pt.zsz3,pt.zsz4,pt.zsz5,pt.zsz6,pt.zsz7,pt.zsz8,pt.zsz9,pt.zsz10 from dt_sharedpart sd left join dt_dataprocess dp on sd.dataprocess_id=dp.dataprocess_id left join dt_prediction pt on dp.dataprocess_id=pt.dataprocess_id where pt.ycff=dp.ycff and pt.dataprocess_id=? ";
		paramlist.add(dataprocess_id);
		return fd_supplierdao.exesqlrelist(sql, paramlist);
	}
	
	
	@Override
	public List adminfindaccount(int supNum, String supName) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from fd_supplier where 1=1 ";;
		if(supNum!=0){
			sql+=" and supNum=? ";
			paramlist.add(supNum);
		}
		
		return fd_supplierdao.exesqlrelist(sql, paramlist);
	}
	
}
