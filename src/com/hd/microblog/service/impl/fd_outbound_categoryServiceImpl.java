package com.hd.microblog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hd.common.dao.IBaseDao;
import com.hd.common.service.impl.BaseService;
import com.hd.microblog.dao.fd_outbound_categoryDao;

import com.hd.microblog.model.fd_outbound_category;

import com.hd.microblog.service.fd_outbound_categoryService;

@Service("fd_outbound_categoryService")
public class fd_outbound_categoryServiceImpl extends BaseService<fd_outbound_category, Integer> implements fd_outbound_categoryService{
	private fd_outbound_categoryDao fd_outbound_categorydao;
	@Autowired
	@Qualifier("fd_outbound_categoryDao")
	@Override
	public void setBaseDao(IBaseDao<fd_outbound_category, Integer> fd_outbound_categorydao) {
		this.baseDao = fd_outbound_categorydao;
		this.fd_outbound_categorydao = (fd_outbound_categoryDao) fd_outbound_categorydao;
	}
	@Override
	public List adminfindfd_outbound_categorylist1(String jqcode,String bdcode,Integer start, int number) {
		// TODO Auto-generated method stub		
		List<Object> paramlist = new ArrayList();
		String sql = "select * from fd_outbound_category where 1=1 ";
		if(jqcode!=""){
			sql+=" and outbound_categoryNum=? ";
			paramlist.add(jqcode);
		}
		if(bdcode!=""){
			sql+=" and outbound_categoryName=? ";
			paramlist.add(bdcode);
		}
		sql += " limit ?, ? ";
		paramlist.add(start);
		paramlist.add(number);
		return fd_outbound_categorydao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindfd_outbound_categorylistcount1(String jqcode,String bdcode) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from fd_outbound_category where 1=1 ";;
		if(jqcode!=""){
			sql+=" and outbound_categoryNum=? ";
			paramlist.add(jqcode);
		}
		if(bdcode!=""){
			sql+=" and outbound_categoryName=? ";
			paramlist.add(bdcode);
		}
		
		return fd_outbound_categorydao.exesqlrelist(sql, paramlist);
	}

	
	@Override
	public List adminfindfd_outbound_categorylistcount(String outbound_categoryName) {
		// TODO Auto-generated method stub
		String sql = "From fd_outbound_category where outbound_categoryName=?";
		List<Object> paramlist = new ArrayList();
		paramlist.add(outbound_categoryName);
		return fd_outbound_categorydao.exehqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindfd_outbound_categorylist(Integer start, int number) {
		// TODO Auto-generated method stub
		String sql = "select * from fd_outbound_category where 1=1 ";
		sql += " limit ?, ? ";
		List<Object> paramlist = new ArrayList();
		paramlist.add(start);
		paramlist.add(number);
		return fd_outbound_categorydao.exesqlrelist(sql, paramlist);
	}
	
	
	
	@Override
	public List adminfindfd_outbound_categorylist() {
		// TODO Auto-generated method stub
		String sql = "select * from fd_outbound_category where 1=1 ";
		return fd_outbound_categorydao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfindfd_outbound_categorylistall() {
		// TODO Auto-generated method stub
		String sql = "select * from fd_outbound_category";
		return fd_outbound_categorydao.exesqlrelist(sql, null);
	}
	
	@Override
	public List adminfindfd_outbound_categoryfordidandtype(Integer dataprocess_id, int flag) {
		// TODO Auto-generated method stub
		String sql = "select * from fd_outbound_category where dataprocess_id="+dataprocess_id+" and ycff="+flag;
		return fd_outbound_categorydao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfindfd_outbound_categorylistfordpid(int dataprocess_id) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select sd.sharedpart_id,sd.kyd,pt.ycz11,pt.bzc11,pt.fx11,pt.zsz1,pt.zsz2,pt.zsz3,pt.zsz4,pt.zsz5,pt.zsz6,pt.zsz7,pt.zsz8,pt.zsz9,pt.zsz10 from dt_sharedpart sd left join dt_dataprocess dp on sd.dataprocess_id=dp.dataprocess_id left join dt_prediction pt on dp.dataprocess_id=pt.dataprocess_id where pt.ycff=dp.ycff and pt.dataprocess_id=? ";
		paramlist.add(dataprocess_id);
		return fd_outbound_categorydao.exesqlrelist(sql, paramlist);
	}
	
	
	@Override
	public List adminfindaccount(int outbound_categoryNum, String outbound_categoryName) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from fd_outbound_category where 1=1 ";;
		if(outbound_categoryNum!=0){
			sql+=" and outbound_categoryNum=? ";
			paramlist.add(outbound_categoryNum);
		}
		
		return fd_outbound_categorydao.exesqlrelist(sql, paramlist);
	}
	
}
