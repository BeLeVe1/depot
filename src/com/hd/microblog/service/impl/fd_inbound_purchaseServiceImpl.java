package com.hd.microblog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hd.common.dao.IBaseDao;
import com.hd.common.service.impl.BaseService;
import com.hd.microblog.dao.fd_inbound_purchaseDao;

import com.hd.microblog.model.fd_inbound_purchase;

import com.hd.microblog.service.fd_inbound_purchaseService;

@Service("fd_inbound_purchaseService")
public class fd_inbound_purchaseServiceImpl extends BaseService<fd_inbound_purchase, Integer> implements fd_inbound_purchaseService{
	private fd_inbound_purchaseDao fd_inbound_purchasedao;
	@Autowired
	@Qualifier("fd_inbound_purchaseDao")
	@Override
	public void setBaseDao(IBaseDao<fd_inbound_purchase, Integer> fd_inbound_purchasedao) {
		this.baseDao = fd_inbound_purchasedao;
		this.fd_inbound_purchasedao = (fd_inbound_purchaseDao) fd_inbound_purchasedao;
	}
	@Override
	public List adminfindfd_inbound_purchaselist1(String jqcode,String bdcode,Integer start, int number) {
		// TODO Auto-generated method stub		
		List<Object> paramlist = new ArrayList();
		String sql = "select * from fd_inbound_purchase where 1=1 ";
		if(jqcode!=""){
			sql+=" and inbound_purchaseNum=? ";
			paramlist.add(jqcode);
		}
		if(bdcode!=""){
			sql+=" and inbound_purchaseName=? ";
			paramlist.add(bdcode);
		}
		sql += " limit ?, ? ";
		paramlist.add(start);
		paramlist.add(number);
		return fd_inbound_purchasedao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindfd_inbound_purchaselistcount1(String jqcode,String bdcode) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from fd_inbound_purchase where 1=1 ";;
		if(jqcode!=""){
			sql+=" and inbound_purchaseNum=? ";
			paramlist.add(jqcode);
		}
		if(bdcode!=""){
			sql+=" and inbound_purchaseName=? ";
			paramlist.add(bdcode);
		}
		
		return fd_inbound_purchasedao.exesqlrelist(sql, paramlist);
	}

	
	@Override
	public List adminfindfd_inbound_purchaselistcount(String inbound_purchaseName) {
		// TODO Auto-generated method stub
		String sql = "From fd_inbound_purchase where inbound_purchaseName=?";
		List<Object> paramlist = new ArrayList();
		paramlist.add(inbound_purchaseName);
		return fd_inbound_purchasedao.exehqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindfd_inbound_purchaselist(Integer start, int number) {
		// TODO Auto-generated method stub
		String sql = "select * from fd_inbound_purchase where 1=1 ";
		sql += " limit ?, ? ";
		List<Object> paramlist = new ArrayList();
		paramlist.add(start);
		paramlist.add(number);
		return fd_inbound_purchasedao.exesqlrelist(sql, paramlist);
	}
	
	
	
	@Override
	public List adminfindfd_inbound_purchaselist() {
		// TODO Auto-generated method stub
		String sql = "select * from fd_inbound_purchase where 1=1 ";
		return fd_inbound_purchasedao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfindfd_inbound_purchaselistall() {
		// TODO Auto-generated method stub
		String sql = "select * from fd_inbound_purchase";
		return fd_inbound_purchasedao.exesqlrelist(sql, null);
	}
	
	@Override
	public List adminfindfd_inbound_purchasefordidandtype(Integer dataprocess_id, int flag) {
		// TODO Auto-generated method stub
		String sql = "select * from fd_inbound_purchase where dataprocess_id="+dataprocess_id+" and ycff="+flag;
		return fd_inbound_purchasedao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfindfd_inbound_purchaselistfordpid(int dataprocess_id) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select sd.sharedpart_id,sd.kyd,pt.ycz11,pt.bzc11,pt.fx11,pt.zsz1,pt.zsz2,pt.zsz3,pt.zsz4,pt.zsz5,pt.zsz6,pt.zsz7,pt.zsz8,pt.zsz9,pt.zsz10 from dt_sharedpart sd left join dt_dataprocess dp on sd.dataprocess_id=dp.dataprocess_id left join dt_prediction pt on dp.dataprocess_id=pt.dataprocess_id where pt.ycff=dp.ycff and pt.dataprocess_id=? ";
		paramlist.add(dataprocess_id);
		return fd_inbound_purchasedao.exesqlrelist(sql, paramlist);
	}
	
	
	@Override
	public List adminfindaccount(int inbound_purchaseNum, String inbound_purchaseName) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from fd_inbound_purchase where 1=1 ";;
		if(inbound_purchaseNum!=0){
			sql+=" and inbound_purchaseNum=? ";
			paramlist.add(inbound_purchaseNum);
		}
		
		return fd_inbound_purchasedao.exesqlrelist(sql, paramlist);
	}
	
}
