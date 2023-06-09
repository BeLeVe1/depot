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
import com.hd.microblog.dao.dt_dataprocessDao;
import com.hd.microblog.model.dt_dataprocess;
import com.hd.microblog.service.dt_dataprocessService;
@Service("dt_dataprocessService")
public class dt_dataprocessServiceImpl extends BaseService<dt_dataprocess, Integer> implements dt_dataprocessService{
	private dt_dataprocessDao dt_dataprocessdao;
	@Autowired
	@Qualifier("dt_dataprocessDao")
	@Override
	public void setBaseDao(IBaseDao<dt_dataprocess, Integer> dt_dataprocessdao) {
		this.baseDao = dt_dataprocessdao;
		this.dt_dataprocessdao = (dt_dataprocessDao) dt_dataprocessdao;
	}
	@Override
	public List adminfinddataprocessslist() {
		// TODO Auto-generated method stub
		String sql = "select * from dt_dataprocess where 1=1 ";
		return dt_dataprocessdao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfinddataprocesslist(String jqcode,String bdcode,String zbcode, String qccode, String qcname, String fg, String sort,
			Integer start, int number) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select * from dt_dataprocess where 1=1 ";
		if(jqcode!=""){
			sql+=" and jqcode=? ";
			paramlist.add(jqcode);
		}
		if(bdcode!=""){
			sql+=" and bdcode=? ";
			paramlist.add(bdcode);
		}
		if(zbcode!=""){
			sql+=" and zbcode=? ";
			paramlist.add(zbcode);
		}
		if(qccode!=""){
			sql+=" and qccode=? ";
			paramlist.add(qccode);
		}
		if(qcname!=""){
			sql+=" and qcname=? ";
			paramlist.add(qcname);
		}
		if(fg!=""){
			sql+=" and fg=? ";
			paramlist.add(fg);
		}
		if(sort!=""){
			sql+=" order by "+sort+" desc ";
		}
		sql += " limit ?, ? ";
		paramlist.add(start);
		paramlist.add(number);
		System.out.println(paramlist);
		return dt_dataprocessdao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfinddataprocesslistcount(String jqcode,String bdcode,String zbcode, String qccode, String qcname, String fg) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from dt_dataprocess where 1=1 ";
		if(jqcode!=""){
			sql+=" and jqcode=? ";
			paramlist.add(jqcode);
		}
		if(bdcode!=""){
			sql+=" and bdcode=? ";
			paramlist.add(bdcode);
		}
		if(zbcode!=""){
			sql+=" and zbcode=? ";
			paramlist.add(zbcode);
		}
		if(qccode!=""){
			sql+=" and qccode=? ";
			paramlist.add(qccode);
		}
		if(qcname!=""){
			sql+=" and qcname=? ";
			paramlist.add(qcname);
		}
		if(fg!=""){
			sql+=" and fg=? ";
			paramlist.add(fg);
		}
		return dt_dataprocessdao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfinddataprocessgroup(String fg) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "SELECT d.*,SUM(d.unitprice*s.plannumber) zbmoney FROM dt_dataprocess d left join dt_sharedpart s on d.dataprocess_id=s.dataprocess_id where d.fg=? GROUP BY d.zbcode ";
		paramlist.add(fg);
		return dt_dataprocessdao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfinddataprocesslistforqccode(String qccode) {
		// TODO Auto-generated method stub
		String sql = "From dt_dataprocess where qccode="+qccode;
		return dt_dataprocessdao.exehqlrelist(sql, null);
	}
}
