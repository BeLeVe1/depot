package com.hd.microblog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hd.common.dao.IBaseDao;
import com.hd.common.service.impl.BaseService;
import com.hd.microblog.dao.fd_clientDao;

import com.hd.microblog.model.fd_client;

import com.hd.microblog.service.fd_clientService;

@Service("fd_clientService")
public class fd_clientServiceImpl extends BaseService<fd_client, Integer> implements fd_clientService{
	private fd_clientDao fd_clientdao;
	@Autowired
	@Qualifier("fd_clientDao")
	@Override
	public void setBaseDao(IBaseDao<fd_client, Integer> fd_clientdao) {
		this.baseDao = fd_clientdao;
		this.fd_clientdao = (fd_clientDao) fd_clientdao;
	}
	@Override
	public List adminfindfd_clientlist1(String jqcode,String bdcode,Integer start, int number) {
		// TODO Auto-generated method stub		
		List<Object> paramlist = new ArrayList();
		String sql = "select * from fd_client where 1=1 ";
		if(jqcode!=""){
			sql+=" and clientNum=? ";
			paramlist.add(jqcode);
		}
		if(bdcode!=""){
			sql+=" and clientName=? ";
			paramlist.add(bdcode);
		}
		sql += " limit ?, ? ";
		paramlist.add(start);
		paramlist.add(number);
		return fd_clientdao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindfd_clientlistcount1(String jqcode,String bdcode) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from fd_client where 1=1 ";;
		if(jqcode!=""){
			sql+=" and clientNum=? ";
			paramlist.add(jqcode);
		}
		if(bdcode!=""){
			sql+=" and clientName=? ";
			paramlist.add(bdcode);
		}
		
		return fd_clientdao.exesqlrelist(sql, paramlist);
	}

	
	@Override
	public List adminfindfd_clientlistcount(String clientName) {
		// TODO Auto-generated method stub
		String sql = "From fd_client where clientName=?";
		List<Object> paramlist = new ArrayList();
		paramlist.add(clientName);
		return fd_clientdao.exehqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindfd_clientlist(Integer start, int number) {
		// TODO Auto-generated method stub
		String sql = "select * from fd_client where 1=1 ";
		sql += " limit ?, ? ";
		List<Object> paramlist = new ArrayList();
		paramlist.add(start);
		paramlist.add(number);
		return fd_clientdao.exesqlrelist(sql, paramlist);
	}
	
	
	
	@Override
	public List adminfindfd_clientlist() {
		// TODO Auto-generated method stub
		String sql = "select * from fd_client where 1=1 ";
		return fd_clientdao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfindfd_clientlistall() {
		// TODO Auto-generated method stub
		String sql = "select * from fd_client";
		return fd_clientdao.exesqlrelist(sql, null);
	}
	
	@Override
	public List adminfindfd_clientfordidandtype(Integer dataprocess_id, int flag) {
		// TODO Auto-generated method stub
		String sql = "select * from fd_client where dataprocess_id="+dataprocess_id+" and ycff="+flag;
		return fd_clientdao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfindfd_clientlistfordpid(int dataprocess_id) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select sd.sharedpart_id,sd.kyd,pt.ycz11,pt.bzc11,pt.fx11,pt.zsz1,pt.zsz2,pt.zsz3,pt.zsz4,pt.zsz5,pt.zsz6,pt.zsz7,pt.zsz8,pt.zsz9,pt.zsz10 from dt_sharedpart sd left join dt_dataprocess dp on sd.dataprocess_id=dp.dataprocess_id left join dt_prediction pt on dp.dataprocess_id=pt.dataprocess_id where pt.ycff=dp.ycff and pt.dataprocess_id=? ";
		paramlist.add(dataprocess_id);
		return fd_clientdao.exesqlrelist(sql, paramlist);
	}
	
	
	@Override
	public List adminfindaccount(int clientNum, String clientName) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from fd_client where 1=1 ";;
		if(clientNum!=0){
			sql+=" and clientNum=? ";
			paramlist.add(clientNum);
		}
		
		return fd_clientdao.exesqlrelist(sql, paramlist);
	}
	
}
