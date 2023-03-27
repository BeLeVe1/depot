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
import com.hd.microblog.dao.dt_orderlistDao;
import com.hd.microblog.model.dt_orderlist;
import com.hd.microblog.service.dt_orderlistService;
@Service("dt_orderlistService")
public class dt_orderlistServiceImpl extends BaseService<dt_orderlist, Integer> implements dt_orderlistService{
	private dt_orderlistDao dt_orderlistdao;
	
	@Autowired
	@Qualifier("dt_orderlistDao")
	@Override
	public void setBaseDao(IBaseDao<dt_orderlist, Integer> dt_orderlistdao) {
		this.baseDao = dt_orderlistdao;
		this.dt_orderlistdao = (dt_orderlistDao) dt_orderlistdao;
	}

	@Override
	public List adminfindorderlist(String orcode, String cpname, String cpcode, String gjname, String gjcode,
			String orstat, String gjstat, Integer start, int number) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select * from dt_orderlist where 1=1 ";
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
		if(orstat!=""){
			sql+=" and orstat=? ";
			paramlist.add(orstat);
		}
		if(gjstat!=""){
			sql+=" and gjstat=? ";
			paramlist.add(gjstat);
		}
		sql += " limit ?, ? ";
		paramlist.add(start);
		paramlist.add(number);
		System.out.println(paramlist);
		return dt_orderlistdao.exesqlrelist(sql, paramlist);
	}

	@Override
	public List adminfindorderlistcount(String orcode, String cpname, String cpcode, String gjname, String gjcode,
			String orstat, String gjstat) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from dt_orderlist where 1=1 ";
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
		if(orstat!=""){
			sql+=" and orstat=? ";
			paramlist.add(orstat);
		}
		if(gjstat!=""){
			sql+=" and gjstat=? ";
			paramlist.add(gjstat);
		}
		return dt_orderlistdao.exesqlrelist(sql, paramlist);
	}

	@Override
	public List adminfindorderlist(String select_order_list) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select cpname,cpcode,gjname,gjcode,sum(gjnumb) as gjnumb  from dt_orderlist where 1=1 ";
		if(select_order_list!=""){
			sql+=" and orderdata_id in (" + select_order_list + ")";
//			paramlist.add(select_order_list);
			sql += " group by gjcode";
		}
		System.out.println(paramlist);
		return dt_orderlistdao.exesqlrelist(sql, paramlist);
	}

	@Override
	public List adminselectorderlistcount(String select_order_list) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from dt_orderlist where 1=1 ";
		if(select_order_list!=""){
			sql+=" and orderdata_id in (" + select_order_list + ")";
//			paramlist.add(select_order_list);
			sql += " group by gjcode";
		}
		return dt_orderlistdao.exesqlrelist(sql, paramlist);
	}

	@Override
	public List adminfindallorderlist() {
		// TODO Auto-generated method stub
		String sql = "select * from dt_orderlist where 1=1 ";
		return dt_orderlistdao.exesqlrelist(sql, null);
	}

}
