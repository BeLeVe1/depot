package com.hd.microblog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hd.common.dao.IBaseDao;
import com.hd.common.service.impl.BaseService;
import com.hd.microblog.dao.dt_predictionDao;
import com.hd.microblog.model.dt_prediction;
import com.hd.microblog.service.dt_predictionService;
@Service("dt_predictionService")
public class dt_predictionServiceImpl extends BaseService<dt_prediction, Integer> implements dt_predictionService{
	private dt_predictionDao dt_predictiondao;
	@Autowired
	@Qualifier("dt_predictionDao")
	@Override
	public void setBaseDao(IBaseDao<dt_prediction, Integer> dt_predictiondao) {
		this.baseDao = dt_predictiondao;
		this.dt_predictiondao = (dt_predictionDao) dt_predictiondao;
	}
	@Override
	public List adminfindpredictionlist(String jqcode,String bdcode,String zbcode,String qccode,String qcname,String sort,Integer start, int number) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select d.*,p.ycz9,p.ycz10,p.ycz11,p.ycz12,p.bzc9,p.bzc10,p.zsz9,p.zsz10 from dt_dataprocess d left join dt_prediction p on d.dataprocess_id=p.dataprocess_id where d.ycff=p.ycff ";
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
		if(sort!=""){
			sql+=" order by p."+sort+" desc ";
		}else {
			sql+=" order by d.dataprocess_id asc ";
		}
		sql += " limit ?, ? ";
		paramlist.add(start);
		paramlist.add(number);
		return dt_predictiondao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindpredictionlistcount(String jqcode,String bdcode,String zbcode,String qccode,String qcname) {
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
		return dt_predictiondao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindpredictionlist() {
		// TODO Auto-generated method stub
		String sql = "select d.*,p.ycz9,p.ycz10,p.ycz11,p.ycz12,p.fx9,p.fx10,p.zsz9,p.zsz10 from dt_dataprocess d left join dt_prediction p on d.dataprocess_id=p.dataprocess_id where d.ycff=p.ycff order by d.dataprocess_id asc ";
		return dt_predictiondao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfindpredictionlistall() {
		// TODO Auto-generated method stub
		String sql = "select * from dt_prediction where 1=1 ";
		return dt_predictiondao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfindpredictionfordidandtype(Integer dataprocess_id, int flag) {
		// TODO Auto-generated method stub
		String sql = "select * from dt_prediction where dataprocess_id="+dataprocess_id+" and ycff="+flag;
		return dt_predictiondao.exesqlrelist(sql, null);
	}
	@Override
	public List adminfindpredictionlistfordpid(int dataprocess_id) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select sd.sharedpart_id,sd.kyd,pt.ycz11,pt.bzc11,pt.fx11,pt.zsz1,pt.zsz2,pt.zsz3,pt.zsz4,pt.zsz5,pt.zsz6,pt.zsz7,pt.zsz8,pt.zsz9,pt.zsz10 from dt_sharedpart sd left join dt_dataprocess dp on sd.dataprocess_id=dp.dataprocess_id left join dt_prediction pt on dp.dataprocess_id=pt.dataprocess_id where pt.ycff=dp.ycff and pt.dataprocess_id=? ";
		paramlist.add(dataprocess_id);
		return dt_predictiondao.exesqlrelist(sql, paramlist);
	}
}
