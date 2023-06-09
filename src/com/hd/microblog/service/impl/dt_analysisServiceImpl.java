package com.hd.microblog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hd.common.dao.IBaseDao;
import com.hd.common.service.impl.BaseService;
import com.hd.microblog.dao.dt_analysisDao;
import com.hd.microblog.model.dt_analysis;
import com.hd.microblog.service.dt_analysisService;
@Service("dt_analysisService")
public class dt_analysisServiceImpl extends BaseService<dt_analysis, Integer> implements dt_analysisService{
	private dt_analysisDao dt_analysisdao;
	@Autowired
	@Qualifier("dt_analysisDao")
	@Override
	public void setBaseDao(IBaseDao<dt_analysis, Integer> dt_analysisdao) {
		this.baseDao = dt_analysisdao;
		this.dt_analysisdao = (dt_analysisDao) dt_analysisdao;
	}
	@Override
	public List adminfindanalysislist(String jqcode,String bdcode,String zbcode,String qccode,String qcname,String fg,String sort,Integer start, int number) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select d.*,p.ycz11,p.bzc11 from dt_dataprocess d left join dt_prediction p on d.dataprocess_id=p.dataprocess_id where d.ycff=p.ycff ";
		if(jqcode!=""){
			sql+=" and d.jqcode=? ";
			paramlist.add(jqcode);
		}
		if(bdcode!=""){
			sql+=" and d.bdcode=? ";
			paramlist.add(bdcode);
		}
		if(zbcode!=""){
			sql+=" and d.zbcode=? ";
			paramlist.add(zbcode);
		}
		if(qccode!=""){
			sql+=" and d.qccode=? ";
			paramlist.add(qccode);
		}
		if(qcname!=""){
			sql+=" and d.qcname=? ";
			paramlist.add(qcname);
		}
		if(fg!=""){
			sql+=" and d.fg=? ";
			paramlist.add(fg);
		}
		if(sort!=""){
			if(sort.equals("ycz11")||sort.equals("bzc11")) {
				sql+=" order by p."+sort+" desc ";
			}else {
				sql+=" order by d."+sort+" desc ";
			}
		}else {
			sql+=" order by d.dataprocess_id asc ";
		}
		sql += " limit ?, ? ";
		paramlist.add(start);
		paramlist.add(number);
		return dt_analysisdao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindanalysislistcount(String jqcode,String bdcode,String zbcode,String qccode,String qcname,String fg) {
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
		return dt_analysisdao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindanalysislist() {
		// TODO Auto-generated method stub
		String sql = "select d.*,p.ycz11,p.fx11 from dt_dataprocess d left join dt_prediction p on d.dataprocess_id=p.dataprocess_id where d.ycff=p.ycff order by d.dataprocess_id asc ";
		return dt_analysisdao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfindanalysislistall() {
		// TODO Auto-generated method stub
		String sql = "select * from dt_analysis where 1=1 ";
		return dt_analysisdao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfindanalysislistfordataprocess_id(int dataprocess_id) {
		// TODO Auto-generated method stub
		String sql = "select d.*,p.ycz11,p.bzc11,p.zsz11,p.fx11 from dt_dataprocess d left join dt_prediction p on d.dataprocess_id=p.dataprocess_id where d.ycff=p.ycff and d.dataprocess_id="+dataprocess_id+" order by d.dataprocess_id asc ";
		return dt_analysisdao.exesqlrelist(sql, null);
	}
}
