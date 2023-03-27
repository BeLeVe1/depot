package com.hd.microblog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hd.common.dao.IBaseDao;
import com.hd.common.service.impl.BaseService;
import com.hd.microblog.dao.fd_outbound_item_propertiesDao;

import com.hd.microblog.model.fd_outbound_item_properties;

import com.hd.microblog.service.fd_outbound_item_propertiesService;

@Service("fd_outbound_item_propertiesService")
public class fd_outbound_item_propertiesServiceImpl extends BaseService<fd_outbound_item_properties, Integer> implements fd_outbound_item_propertiesService{
	private fd_outbound_item_propertiesDao fd_outbound_item_propertiesdao;
	@Autowired
	@Qualifier("fd_outbound_item_propertiesDao")
	@Override
	public void setBaseDao(IBaseDao<fd_outbound_item_properties, Integer> fd_outbound_item_propertiesdao) {
		this.baseDao = fd_outbound_item_propertiesdao;
		this.fd_outbound_item_propertiesdao = (fd_outbound_item_propertiesDao) fd_outbound_item_propertiesdao;
	}
	@Override
	public List adminfindfd_outbound_item_propertieslist1(String jqcode,String bdcode,Integer start, int number) {
		// TODO Auto-generated method stub		
		List<Object> paramlist = new ArrayList();
		String sql = "select * from fd_outbound_item_properties where 1=1 ";
		if(jqcode!=""){
			sql+=" and outbound_item_propertiesNum=? ";
			paramlist.add(jqcode);
		}
		if(bdcode!=""){
			sql+=" and outbound_item_propertiesName=? ";
			paramlist.add(bdcode);
		}
		sql += " limit ?, ? ";
		paramlist.add(start);
		paramlist.add(number);
		return fd_outbound_item_propertiesdao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindfd_outbound_item_propertieslistcount1(String jqcode,String bdcode) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from fd_outbound_item_properties where 1=1 ";;
		if(jqcode!=""){
			sql+=" and outbound_item_propertiesNum=? ";
			paramlist.add(jqcode);
		}
		if(bdcode!=""){
			sql+=" and outbound_item_propertiesName=? ";
			paramlist.add(bdcode);
		}
		
		return fd_outbound_item_propertiesdao.exesqlrelist(sql, paramlist);
	}

	
	@Override
	public List adminfindfd_outbound_item_propertieslistcount(String outbound_item_propertiesName) {
		// TODO Auto-generated method stub
		String sql = "From fd_outbound_item_properties where outbound_item_propertiesName=?";
		List<Object> paramlist = new ArrayList();
		paramlist.add(outbound_item_propertiesName);
		return fd_outbound_item_propertiesdao.exehqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindfd_outbound_item_propertieslist(Integer start, int number) {
		// TODO Auto-generated method stub
		String sql = "select * from fd_outbound_item_properties where 1=1 ";
		sql += " limit ?, ? ";
		List<Object> paramlist = new ArrayList();
		paramlist.add(start);
		paramlist.add(number);
		return fd_outbound_item_propertiesdao.exesqlrelist(sql, paramlist);
	}
	
	
	
	@Override
	public List adminfindfd_outbound_item_propertieslist() {
		// TODO Auto-generated method stub
		String sql = "select * from fd_outbound_item_properties where 1=1 ";
		return fd_outbound_item_propertiesdao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfindfd_outbound_item_propertieslistall() {
		// TODO Auto-generated method stub
		String sql = "select * from fd_outbound_item_properties";
		return fd_outbound_item_propertiesdao.exesqlrelist(sql, null);
	}
	
	@Override
	public List adminfindfd_outbound_item_propertiesfordidandtype(Integer dataprocess_id, int flag) {
		// TODO Auto-generated method stub
		String sql = "select * from fd_outbound_item_properties where dataprocess_id="+dataprocess_id+" and ycff="+flag;
		return fd_outbound_item_propertiesdao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfindfd_outbound_item_propertieslistfordpid(int dataprocess_id) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select sd.sharedpart_id,sd.kyd,pt.ycz11,pt.bzc11,pt.fx11,pt.zsz1,pt.zsz2,pt.zsz3,pt.zsz4,pt.zsz5,pt.zsz6,pt.zsz7,pt.zsz8,pt.zsz9,pt.zsz10 from dt_sharedpart sd left join dt_dataprocess dp on sd.dataprocess_id=dp.dataprocess_id left join dt_prediction pt on dp.dataprocess_id=pt.dataprocess_id where pt.ycff=dp.ycff and pt.dataprocess_id=? ";
		paramlist.add(dataprocess_id);
		return fd_outbound_item_propertiesdao.exesqlrelist(sql, paramlist);
	}
	
	
	@Override
	public List adminfindaccount(int outbound_item_propertiesNum, String outbound_item_propertiesName) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from fd_outbound_item_properties where 1=1 ";;
		if(outbound_item_propertiesNum!=0){
			sql+=" and outbound_item_propertiesNum=? ";
			paramlist.add(outbound_item_propertiesNum);
		}
		
		return fd_outbound_item_propertiesdao.exesqlrelist(sql, paramlist);
	}
	
}
