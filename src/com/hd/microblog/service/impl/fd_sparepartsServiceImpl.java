package com.hd.microblog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hd.common.dao.IBaseDao;
import com.hd.common.service.impl.BaseService;
import com.hd.microblog.dao.fd_sparepartsDao;

import com.hd.microblog.model.fd_spareparts;

import com.hd.microblog.service.fd_sparepartsService;

@Service("fd_sparepartsService")
public class fd_sparepartsServiceImpl extends BaseService<fd_spareparts, Integer> implements fd_sparepartsService{
	private fd_sparepartsDao fd_sparepartsdao;
	@Autowired
	@Qualifier("fd_sparepartsDao")
	@Override
	public void setBaseDao(IBaseDao<fd_spareparts, Integer> fd_sparepartsdao) {
		this.baseDao = fd_sparepartsdao;
		this.fd_sparepartsdao = (fd_sparepartsDao) fd_sparepartsdao;
	}
	@Override
	public List adminfindfd_sparepartslist1(String jqcode,String bdcode,Integer start, int number) {
		// TODO Auto-generated method stub		
		List<Object> paramlist = new ArrayList();
		String sql = "select * from fd_spareparts where 1=1 ";
		if(jqcode!=""){
			sql+=" and sparepartsNum=? ";
			paramlist.add(jqcode);
		}
		if(bdcode!=""){
			sql+=" and sparepartsName=? ";
			paramlist.add(bdcode);
		}
		sql += " limit ?, ? ";
		paramlist.add(start);
		paramlist.add(number);
		return fd_sparepartsdao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindfd_sparepartslistcount1(String jqcode,String bdcode) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from fd_spareparts where 1=1 ";;
		if(jqcode!=""){
			sql+=" and sparepartsNum=? ";
			paramlist.add(jqcode);
		}
		if(bdcode!=""){
			sql+=" and sparepartsName=? ";
			paramlist.add(bdcode);
		}
		
		return fd_sparepartsdao.exesqlrelist(sql, paramlist);
	}

	
	@Override
	public List adminfindfd_sparepartslistcount(String sparepartsName) {
		// TODO Auto-generated method stub
		String sql = "From fd_spareparts where sparepartsName=?";
		List<Object> paramlist = new ArrayList();
		paramlist.add(sparepartsName);
		return fd_sparepartsdao.exehqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindfd_sparepartslist(Integer start, int number) {
		// TODO Auto-generated method stub
		String sql = "select * from fd_spareparts where 1=1 ";
		sql += " limit ?, ? ";
		List<Object> paramlist = new ArrayList();
		paramlist.add(start);
		paramlist.add(number);
		return fd_sparepartsdao.exesqlrelist(sql, paramlist);
	}
	
	
	
	@Override
	public List adminfindfd_sparepartslist() {
		// TODO Auto-generated method stub
		String sql = "select * from fd_spareparts where 1=1 ";
		return fd_sparepartsdao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfindfd_sparepartslistall() {
		// TODO Auto-generated method stub
		String sql = "select * from fd_spareparts";
		return fd_sparepartsdao.exesqlrelist(sql, null);
	}
	
	@Override
	public List adminfindfd_sparepartsfordidandtype(Integer dataprocess_id, int flag) {
		// TODO Auto-generated method stub
		String sql = "select * from fd_spareparts where dataprocess_id="+dataprocess_id+" and ycff="+flag;
		return fd_sparepartsdao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfindfd_sparepartslistfordpid(int dataprocess_id) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select sd.sharedpart_id,sd.kyd,pt.ycz11,pt.bzc11,pt.fx11,pt.zsz1,pt.zsz2,pt.zsz3,pt.zsz4,pt.zsz5,pt.zsz6,pt.zsz7,pt.zsz8,pt.zsz9,pt.zsz10 from dt_sharedpart sd left join dt_dataprocess dp on sd.dataprocess_id=dp.dataprocess_id left join dt_prediction pt on dp.dataprocess_id=pt.dataprocess_id where pt.ycff=dp.ycff and pt.dataprocess_id=? ";
		paramlist.add(dataprocess_id);
		return fd_sparepartsdao.exesqlrelist(sql, paramlist);
	}
	
	
	@Override
	public List adminfindaccount(int sparepartsNum, String sparepartsType,String sparepartsName, String sparepartsSpecification,int sparepartsStocks,String sparepartsUnit,double sparepartsPrice) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from fd_spareparts where 1=1 ";;
		if(sparepartsNum!=0){
			sql+=" and sparepartsNum=? ";
			paramlist.add(sparepartsNum);
		}
		return fd_sparepartsdao.exesqlrelist(sql, paramlist);
	}
//	@Override
//	public List adminfindfd_sparepartslistcount(String sparepartsName, String sparepartsWork) {
//		// TODO 自动生成的方法存根
//		return null;
//	}
//	@Override
//	public List adminfindfd_sparepartslist(String sparepartsName, String sparepartsWork, Integer start, int number) {
//		// TODO 自动生成的方法存根
//		return null;
//	}
}
