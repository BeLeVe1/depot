package com.hd.microblog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hd.common.dao.IBaseDao;
import com.hd.common.service.impl.BaseService;
import com.hd.microblog.dao.fd_peopleDao;

import com.hd.microblog.model.fd_people;

import com.hd.microblog.service.fd_peopleService;

@Service("fd_peopleService")
public class fd_peopleServiceImpl extends BaseService<fd_people, Integer> implements fd_peopleService{
	private fd_peopleDao fd_peopledao;
	@Autowired
	@Qualifier("fd_peopleDao")
	@Override
	public void setBaseDao(IBaseDao<fd_people, Integer> fd_peopledao) {
		this.baseDao = fd_peopledao;
		this.fd_peopledao = (fd_peopleDao) fd_peopledao;
	}
	@Override
	public List adminfindfd_peoplelist1(String jqcode,String bdcode,Integer start, int number) {
		// TODO Auto-generated method stub		
		List<Object> paramlist = new ArrayList();
		String sql = "select * from fd_people where 1=1 ";
		if(jqcode!=""){
			sql+=" and perNum=? ";
			paramlist.add(jqcode);
		}
		if(bdcode!=""){
			sql+=" and perName=? ";
			paramlist.add(bdcode);
		}
		sql += " limit ?, ? ";
		paramlist.add(start);
		paramlist.add(number);
		return fd_peopledao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindfd_peoplelistcount1(String jqcode,String bdcode) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from fd_people where 1=1 ";;
		if(jqcode!=""){
			sql+=" and perNum=? ";
			paramlist.add(jqcode);
		}
		if(bdcode!=""){
			sql+=" and perName=? ";
			paramlist.add(bdcode);
		}
		
		return fd_peopledao.exesqlrelist(sql, paramlist);
	}

	
	@Override
	public List adminfindfd_peoplelistcount(String perName) {
		// TODO Auto-generated method stub
		String sql = "From fd_people where perName=?";
		List<Object> paramlist = new ArrayList();
		paramlist.add(perName);
		return fd_peopledao.exehqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindfd_peoplelist(Integer start, int number) {
		// TODO Auto-generated method stub
		String sql = "select * from fd_people where 1=1 ";
		sql += " limit ?, ? ";
		List<Object> paramlist = new ArrayList();
		paramlist.add(start);
		paramlist.add(number);
		return fd_peopledao.exesqlrelist(sql, paramlist);
	}
	
	
	
	@Override
	public List adminfindfd_peoplelist() {
		// TODO Auto-generated method stub
		String sql = "select * from fd_people where 1=1 ";
		return fd_peopledao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfindfd_peoplelistall() {
		// TODO Auto-generated method stub
		String sql = "select * from fd_people";
		return fd_peopledao.exesqlrelist(sql, null);
	}
	
	@Override
	public List adminfindfd_peoplefordidandtype(Integer dataprocess_id, int flag) {
		// TODO Auto-generated method stub
		String sql = "select * from fd_people where dataprocess_id="+dataprocess_id+" and ycff="+flag;
		return fd_peopledao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfindfd_peoplelistfordpid(int dataprocess_id) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select sd.sharedpart_id,sd.kyd,pt.ycz11,pt.bzc11,pt.fx11,pt.zsz1,pt.zsz2,pt.zsz3,pt.zsz4,pt.zsz5,pt.zsz6,pt.zsz7,pt.zsz8,pt.zsz9,pt.zsz10 from dt_sharedpart sd left join dt_dataprocess dp on sd.dataprocess_id=dp.dataprocess_id left join dt_prediction pt on dp.dataprocess_id=pt.dataprocess_id where pt.ycff=dp.ycff and pt.dataprocess_id=? ";
		paramlist.add(dataprocess_id);
		return fd_peopledao.exesqlrelist(sql, paramlist);
	}
	
	
	@Override
	public List adminfindaccount(int perNum, String perName, String phoneNum, String perWork) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from fd_people where 1=1 ";;
		if(perNum!=0){
			sql+=" and perNum=? ";
			paramlist.add(perNum);
		}
		return fd_peopledao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindfd_peoplelistcount(String perName, String perWork) {
		// TODO 自动生成的方法存根
		return null;
	}
	@Override
	public List adminfindfd_peoplelist(String perName, String perWork, Integer start, int number) {
		// TODO 自动生成的方法存根
		return null;
	}
}
