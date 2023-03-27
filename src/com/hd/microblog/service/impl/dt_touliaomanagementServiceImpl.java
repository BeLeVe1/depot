package com.hd.microblog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hd.common.dao.IBaseDao;
import com.hd.common.service.impl.BaseService;
import com.hd.microblog.dao.dt_touliaomanagementDao;
import com.hd.microblog.model.dt_touliaomanagement;
import com.hd.microblog.service.dt_touliaomanagementService;
@Service("dt_touliaomanagementService")
public class dt_touliaomanagementServiceImpl extends BaseService<dt_touliaomanagement, Integer> implements dt_touliaomanagementService{
	private dt_touliaomanagementDao dt_touliaomanagementdao;
	
	@Autowired
	@Qualifier("dt_touliaomanagementDao")
	@Override
	public void setBaseDao(IBaseDao<dt_touliaomanagement, Integer> dt_touliaomanagementdao) {
		this.baseDao = dt_touliaomanagementdao;
		this.dt_touliaomanagementdao = (dt_touliaomanagementDao) dt_touliaomanagementdao;
	}

	@Override
	public List adminfindtouliaomanagement(String orcode, String cpname, String cpcode, String gjname, String gjcode,String touliaostate,
			Integer start, int number) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select * from dt_touliaomanagement where 1=1 ";
		if(orcode!=""){
			sql+=" and orcode=? ";
			paramlist.add(orcode);
		}
		if(cpname!=""){
			sql+=" and cpname=? ";
			paramlist.add(cpname);
		}
		if(cpcode!=""){
			sql+=" and cpcode=? ";
			paramlist.add(cpcode);
		}
		if(gjname!=""){
			sql+=" and gjname=? ";
			paramlist.add(gjname);
		}
		if(gjcode!=""){
			sql+=" and gjcode=? ";
			paramlist.add(gjcode);
		}
		if(touliaostate!=""){
			sql+=" and touliaostate=? ";
			paramlist.add(touliaostate);
		}
		sql += " limit ?, ? ";
		paramlist.add(start);
		paramlist.add(number);
		System.out.println(paramlist);
		return dt_touliaomanagementdao.exesqlrelist(sql, paramlist);
	}

	@Override
	public List adminfindtouliaomanagementcount(String orcode, String cpname, String cpcode, String gjname,
			String gjcode,String touliaostate) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from dt_touliaomanagement where 1=1 ";
		if(orcode!=""){
			sql+=" and orcode=? ";
			paramlist.add(orcode);
		}
		if(cpname!=""){
			sql+=" and cpname=? ";
			paramlist.add(cpname);
		}
		if(cpcode!=""){
			sql+=" and cpcode=? ";
			paramlist.add(cpcode);
		}
		if(gjname!=""){
			sql+=" and gjname=? ";
			paramlist.add(gjname);
		}
		if(gjcode!=""){
			sql+=" and gjcode=? ";
			paramlist.add(gjcode);
		}
		if(touliaostate!=""){
			sql+=" and touliaostate=? ";
			paramlist.add(touliaostate);
		}
		return dt_touliaomanagementdao.exesqlrelist(sql, paramlist);
	}

	@Override
	public List adminfindalltouliaomanagement() {
		// TODO Auto-generated method stub
		String sql = "select * from dt_touliaomanagement where 1=1 ";
		return dt_touliaomanagementdao.exesqlrelist(sql, null);
	}
	
}