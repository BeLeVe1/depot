package com.hd.microblog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hd.common.dao.IBaseDao;
import com.hd.common.service.impl.BaseService;
import com.hd.microblog.dao.fd_departmentDao;

import com.hd.microblog.model.fd_department;

import com.hd.microblog.service.fd_departmentService;

@Service("fd_departmentService")
public class fd_departmentServiceImpl extends BaseService<fd_department, Integer> implements fd_departmentService{
	private fd_departmentDao fd_departmentdao;
	@Autowired
	@Qualifier("fd_departmentDao")
	@Override
	public void setBaseDao(IBaseDao<fd_department, Integer> fd_departmentdao) {
		this.baseDao = fd_departmentdao;
		this.fd_departmentdao = (fd_departmentDao) fd_departmentdao;
	}
	@Override
	public List adminfindfd_departmentlist1(String jqcode,String bdcode,Integer start, int number) {
		// TODO Auto-generated method stub		
		List<Object> paramlist = new ArrayList();
		String sql = "select * from fd_department where 1=1 ";
		if(jqcode!=""){
			sql+=" and departmentNum=? ";
			paramlist.add(jqcode);
		}
		if(bdcode!=""){
			sql+=" and departmentName=? ";
			paramlist.add(bdcode);
		}
		sql += " limit ?, ? ";
		paramlist.add(start);
		paramlist.add(number);
		return fd_departmentdao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindfd_departmentlistcount1(String jqcode,String bdcode) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from fd_department where 1=1 ";;
		if(jqcode!=""){
			sql+=" and departmentNum=? ";
			paramlist.add(jqcode);
		}
		if(bdcode!=""){
			sql+=" and departmentName=? ";
			paramlist.add(bdcode);
		}
		
		return fd_departmentdao.exesqlrelist(sql, paramlist);
	}

	
	@Override
	public List adminfindfd_departmentlistcount(String departmentName) {
		// TODO Auto-generated method stub
		String sql = "From fd_department where departmentName=?";
		List<Object> paramlist = new ArrayList();
		paramlist.add(departmentName);
		return fd_departmentdao.exehqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindfd_departmentlist(Integer start, int number) {
		// TODO Auto-generated method stub
		String sql = "select * from fd_department where 1=1 ";
		sql += " limit ?, ? ";
		List<Object> paramlist = new ArrayList();
		paramlist.add(start);
		paramlist.add(number);
		return fd_departmentdao.exesqlrelist(sql, paramlist);
	}
	
	
	
	@Override
	public List adminfindfd_departmentlist() {
		// TODO Auto-generated method stub
		String sql = "select * from fd_department where 1=1 ";
		return fd_departmentdao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfindfd_departmentlistall() {
		// TODO Auto-generated method stub
		String sql = "select * from fd_department";
		return fd_departmentdao.exesqlrelist(sql, null);
	}
	
	@Override
	public List adminfindfd_departmentfordidandtype(Integer dataprocess_id, int flag) {
		// TODO Auto-generated method stub
		String sql = "select * from fd_department where dataprocess_id="+dataprocess_id+" and ycff="+flag;
		return fd_departmentdao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfindfd_departmentlistfordpid(int dataprocess_id) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select sd.sharedpart_id,sd.kyd,pt.ycz11,pt.bzc11,pt.fx11,pt.zsz1,pt.zsz2,pt.zsz3,pt.zsz4,pt.zsz5,pt.zsz6,pt.zsz7,pt.zsz8,pt.zsz9,pt.zsz10 from dt_sharedpart sd left join dt_dataprocess dp on sd.dataprocess_id=dp.dataprocess_id left join dt_prediction pt on dp.dataprocess_id=pt.dataprocess_id where pt.ycff=dp.ycff and pt.dataprocess_id=? ";
		paramlist.add(dataprocess_id);
		return fd_departmentdao.exesqlrelist(sql, paramlist);
	}
	
	
	@Override
	public List adminfindaccount(int departmentNum, String departmentName) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from fd_department where 1=1 ";;
		if(departmentNum!=0){
			sql+=" and departmentNum=? ";
			paramlist.add(departmentNum);
		}
		
		return fd_departmentdao.exesqlrelist(sql, paramlist);
	}
	
}
