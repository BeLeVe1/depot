package com.hd.microblog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hd.common.dao.IBaseDao;
import com.hd.common.service.impl.BaseService;
import com.hd.microblog.dao.fd_windfarmDao;

import com.hd.microblog.model.fd_windfarm;

import com.hd.microblog.service.fd_windfarmService;

@Service("fd_windfarmService")
public class fd_windfarmServiceImpl extends BaseService<fd_windfarm, Integer> implements fd_windfarmService{
	private fd_windfarmDao fd_windfarmdao;
	@Autowired
	@Qualifier("fd_windfarmDao")
	@Override
	public void setBaseDao(IBaseDao<fd_windfarm, Integer> fd_windfarmdao) {
		this.baseDao = fd_windfarmdao;
		this.fd_windfarmdao = (fd_windfarmDao) fd_windfarmdao;
	}
	@Override
	public List adminfindfd_windfarmlist1(String jqcode,String bdcode,Integer start, int number) {
		// TODO Auto-generated method stub		
		List<Object> paramlist = new ArrayList();
		String sql = "select * from fd_windfarm where 1=1 ";
		if(jqcode!=""){
			sql+=" and windfarmNum=? ";
			paramlist.add(jqcode);
		}
		if(bdcode!=""){
			sql+=" and windfarmName=? ";
			paramlist.add(bdcode);
		}
		sql += " limit ?, ? ";
		paramlist.add(start);
		paramlist.add(number);
		return fd_windfarmdao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindfd_windfarmlistcount1(String jqcode,String bdcode) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from fd_windfarm where 1=1 ";;
		if(jqcode!=""){
			sql+=" and windfarmNum=? ";
			paramlist.add(jqcode);
		}
		if(bdcode!=""){
			sql+=" and windfarmName=? ";
			paramlist.add(bdcode);
		}
		
		return fd_windfarmdao.exesqlrelist(sql, paramlist);
	}

	
	@Override
	public List adminfindfd_windfarmlistcount(String windfarmName) {
		// TODO Auto-generated method stub
		String sql = "From fd_windfarm where windfarmName=?";
		List<Object> paramlist = new ArrayList();
		paramlist.add(windfarmName);
		return fd_windfarmdao.exehqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindfd_windfarmlist(Integer start, int number) {
		// TODO Auto-generated method stub
		String sql = "select * from fd_windfarm where 1=1 ";
		sql += " limit ?, ? ";
		List<Object> paramlist = new ArrayList();
		paramlist.add(start);
		paramlist.add(number);
		return fd_windfarmdao.exesqlrelist(sql, paramlist);
	}
	
	
	
	@Override
	public List adminfindfd_windfarmlist() {
		// TODO Auto-generated method stub
		String sql = "select * from fd_windfarm where 1=1 ";
		return fd_windfarmdao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfindfd_windfarmlistall() {
		// TODO Auto-generated method stub
		String sql = "select * from fd_windfarm";
		return fd_windfarmdao.exesqlrelist(sql, null);
	}
	
	@Override
	public List adminfindfd_windfarmfordidandtype(Integer dataprocess_id, int flag) {
		// TODO Auto-generated method stub
		String sql = "select * from fd_windfarm where dataprocess_id="+dataprocess_id+" and ycff="+flag;
		return fd_windfarmdao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfindfd_windfarmlistfordpid(int dataprocess_id) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select sd.sharedpart_id,sd.kyd,pt.ycz11,pt.bzc11,pt.fx11,pt.zsz1,pt.zsz2,pt.zsz3,pt.zsz4,pt.zsz5,pt.zsz6,pt.zsz7,pt.zsz8,pt.zsz9,pt.zsz10 from dt_sharedpart sd left join dt_dataprocess dp on sd.dataprocess_id=dp.dataprocess_id left join dt_prediction pt on dp.dataprocess_id=pt.dataprocess_id where pt.ycff=dp.ycff and pt.dataprocess_id=? ";
		paramlist.add(dataprocess_id);
		return fd_windfarmdao.exesqlrelist(sql, paramlist);
	}
	
	
	@Override
	public List adminfindaccount(int windfarmNum, String windfarmName, String windfarmRegion) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from fd_windfarm where 1=1 ";;
		if(windfarmNum!=0){
			sql+=" and windfarmNum=? ";
			paramlist.add(windfarmNum);
		}
		return fd_windfarmdao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindfd_windfarmlistcount(String windfarmName, String windfarmRegion) {
		// TODO 自动生成的方法存根
		return null;
	}
	@Override
	public List adminfindfd_windfarmlist(String windfarmName, String windfarmRegion, Integer start, int number) {
		// TODO 自动生成的方法存根
		return null;
	}
}
