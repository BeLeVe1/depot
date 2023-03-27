package com.hd.microblog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hd.common.dao.IBaseDao;
import com.hd.common.service.impl.BaseService;
import com.hd.microblog.dao.fd_outbusinessDao;

import com.hd.microblog.model.fd_outbusiness;

import com.hd.microblog.service.fd_outbusinessService;

@Service("fd_outbusinessService")
public class fd_outbusinessServiceImpl extends BaseService<fd_outbusiness, Integer> implements fd_outbusinessService{
	private fd_outbusinessDao fd_outbusinessdao;
	@Autowired
	@Qualifier("fd_outbusinessDao")
	@Override
	public void setBaseDao(IBaseDao<fd_outbusiness, Integer> fd_outbusinessdao) {
		this.baseDao = fd_outbusinessdao;
		this.fd_outbusinessdao = (fd_outbusinessDao) fd_outbusinessdao;
	}
	@Override
	public List adminfindfd_outbusinesslist1(String jqcode,String bdcode,Integer start, int number) {
		// TODO Auto-generated method stub		
		List<Object> paramlist = new ArrayList();
		String sql = "select * from fd_outbusiness where 1=1 ";
		if(jqcode!=""){
			sql+=" and outbusinessNum=? ";
			paramlist.add(jqcode);
		}
		if(bdcode!=""){
			sql+=" and outbusinessName=? ";
			paramlist.add(bdcode);
		}
		sql += " limit ?, ? ";
		paramlist.add(start);
		paramlist.add(number);
		return fd_outbusinessdao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindfd_outbusinesslistcount1(String jqcode,String bdcode) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from fd_outbusiness where 1=1 ";;
		if(jqcode!=""){
			sql+=" and outbusinessNum=? ";
			paramlist.add(jqcode);
		}
		if(bdcode!=""){
			sql+=" and outbusinessName=? ";
			paramlist.add(bdcode);
		}
		
		return fd_outbusinessdao.exesqlrelist(sql, paramlist);
	}

	
	@Override
	public List adminfindfd_outbusinesslistcount(String outbusinessName) {
		// TODO Auto-generated method stub
		String sql = "From fd_outbusiness where outbusinessName=?";
		List<Object> paramlist = new ArrayList();
		paramlist.add(outbusinessName);
		return fd_outbusinessdao.exehqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindfd_outbusinesslist(Integer start, int number) {
		// TODO Auto-generated method stub
		String sql = "select * from fd_outbusiness where 1=1 ";
		sql += " limit ?, ? ";
		List<Object> paramlist = new ArrayList();
		paramlist.add(start);
		paramlist.add(number);
		return fd_outbusinessdao.exesqlrelist(sql, paramlist);
	}
	
	
	
	@Override
	public List adminfindfd_outbusinesslist() {
		// TODO Auto-generated method stub
		String sql = "select * from fd_outbusiness where 1=1 ";
		return fd_outbusinessdao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfindfd_outbusinesslistall() {
		// TODO Auto-generated method stub
		String sql = "select * from fd_outbusiness";
		return fd_outbusinessdao.exesqlrelist(sql, null);
	}
	
	@Override
	public List adminfindfd_outbusinessfordidandtype(Integer dataprocess_id, int flag) {
		// TODO Auto-generated method stub
		String sql = "select * from fd_outbusiness where dataprocess_id="+dataprocess_id+" and ycff="+flag;
		return fd_outbusinessdao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfindfd_outbusinesslistfordpid(int dataprocess_id) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select sd.sharedpart_id,sd.kyd,pt.ycz11,pt.bzc11,pt.fx11,pt.zsz1,pt.zsz2,pt.zsz3,pt.zsz4,pt.zsz5,pt.zsz6,pt.zsz7,pt.zsz8,pt.zsz9,pt.zsz10 from dt_sharedpart sd left join dt_dataprocess dp on sd.dataprocess_id=dp.dataprocess_id left join dt_prediction pt on dp.dataprocess_id=pt.dataprocess_id where pt.ycff=dp.ycff and pt.dataprocess_id=? ";
		paramlist.add(dataprocess_id);
		return fd_outbusinessdao.exesqlrelist(sql, paramlist);
	}
	
	
	@Override
	public List adminfindaccount(int outbusinessNum, String outbusinessName) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from fd_outbusiness where 1=1 ";;
		if(outbusinessNum!=0){
			sql+=" and outbusinessNum=? ";
			paramlist.add(outbusinessNum);
		}
		
		return fd_outbusinessdao.exesqlrelist(sql, paramlist);
	}
	
}
