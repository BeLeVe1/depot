package com.hd.microblog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hd.common.dao.IBaseDao;
import com.hd.common.service.impl.BaseService;
import com.hd.microblog.dao.fd_inbound_categoryDao;

import com.hd.microblog.model.fd_inbound_category;

import com.hd.microblog.service.fd_inbound_categoryService;

@Service("fd_inbound_categoryService")
public class fd_inbound_categoryServiceImpl extends BaseService<fd_inbound_category, Integer> implements fd_inbound_categoryService{
	private fd_inbound_categoryDao fd_inbound_categorydao;
	@Autowired
	@Qualifier("fd_inbound_categoryDao")
	@Override
	public void setBaseDao(IBaseDao<fd_inbound_category, Integer> fd_inbound_categorydao) {
		this.baseDao = fd_inbound_categorydao;
		this.fd_inbound_categorydao = (fd_inbound_categoryDao) fd_inbound_categorydao;
	}
	@Override
	public List adminfindfd_inbound_categorylist1(String jqcode,String bdcode,Integer start, int number) {
		// TODO Auto-generated method stub		
		List<Object> paramlist = new ArrayList();
		String sql = "select * from fd_inbound_category where 1=1 ";
		if(jqcode!=""){
			sql+=" and inbound_categoryNum=? ";
			paramlist.add(jqcode);
		}
		if(bdcode!=""){
			sql+=" and inbound_categoryName=? ";
			paramlist.add(bdcode);
		}
		sql += " limit ?, ? ";
		paramlist.add(start);
		paramlist.add(number);
		return fd_inbound_categorydao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindfd_inbound_categorylistcount1(String jqcode,String bdcode) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from fd_inbound_category where 1=1 ";;
		if(jqcode!=""){
			sql+=" and inbound_categoryNum=? ";
			paramlist.add(jqcode);
		}
		if(bdcode!=""){
			sql+=" and inbound_categoryName=? ";
			paramlist.add(bdcode);
		}
		
		return fd_inbound_categorydao.exesqlrelist(sql, paramlist);
	}

	
	@Override
	public List adminfindfd_inbound_categorylistcount(String inbound_categoryName) {
		// TODO Auto-generated method stub
		String sql = "From fd_inbound_category where inbound_categoryName=?";
		List<Object> paramlist = new ArrayList();
		paramlist.add(inbound_categoryName);
		return fd_inbound_categorydao.exehqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindfd_inbound_categorylist(Integer start, int number) {
		// TODO Auto-generated method stub
		String sql = "select * from fd_inbound_category where 1=1 ";
		sql += " limit ?, ? ";
		List<Object> paramlist = new ArrayList();
		paramlist.add(start);
		paramlist.add(number);
		return fd_inbound_categorydao.exesqlrelist(sql, paramlist);
	}
	
	
	
	@Override
	public List adminfindfd_inbound_categorylist() {
		// TODO Auto-generated method stub
		String sql = "select * from fd_inbound_category where 1=1 ";
		return fd_inbound_categorydao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfindfd_inbound_categorylistall() {
		// TODO Auto-generated method stub
		String sql = "select * from fd_inbound_category";
		return fd_inbound_categorydao.exesqlrelist(sql, null);
	}
	
	@Override
	public List adminfindfd_inbound_categoryfordidandtype(Integer dataprocess_id, int flag) {
		// TODO Auto-generated method stub
		String sql = "select * from fd_inbound_category where dataprocess_id="+dataprocess_id+" and ycff="+flag;
		return fd_inbound_categorydao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfindfd_inbound_categorylistfordpid(int dataprocess_id) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select sd.sharedpart_id,sd.kyd,pt.ycz11,pt.bzc11,pt.fx11,pt.zsz1,pt.zsz2,pt.zsz3,pt.zsz4,pt.zsz5,pt.zsz6,pt.zsz7,pt.zsz8,pt.zsz9,pt.zsz10 from dt_sharedpart sd left join dt_dataprocess dp on sd.dataprocess_id=dp.dataprocess_id left join dt_prediction pt on dp.dataprocess_id=pt.dataprocess_id where pt.ycff=dp.ycff and pt.dataprocess_id=? ";
		paramlist.add(dataprocess_id);
		return fd_inbound_categorydao.exesqlrelist(sql, paramlist);
	}
	
	
	@Override
	public List adminfindaccount(int inbound_categoryNum, String inbound_categoryName) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from fd_inbound_category where 1=1 ";;
		if(inbound_categoryNum!=0){
			sql+=" and inbound_categoryNum=? ";
			paramlist.add(inbound_categoryNum);
		}
		
		return fd_inbound_categorydao.exesqlrelist(sql, paramlist);
	}
	
}
