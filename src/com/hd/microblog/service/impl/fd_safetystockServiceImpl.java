package com.hd.microblog.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hd.common.dao.IBaseDao;
import com.hd.common.service.impl.BaseService;
import com.hd.microblog.dao.fd_safetystockDao;
import com.hd.microblog.model.fd_safetystock;
import com.hd.microblog.service.fd_safetystockService;
@Service("fd_safetystockService")

public class fd_safetystockServiceImpl extends BaseService<fd_safetystock, Integer> implements fd_safetystockService {
	private fd_safetystockDao fd_safetystockdao;
	@Autowired
	@Qualifier("fd_safetystockDao")
	@Override
	public void setBaseDao(IBaseDao<fd_safetystock, Integer> fd_safetystockdao) {
		// TODO Auto-generated method stub
		this.baseDao = fd_safetystockdao;
		this.fd_safetystockdao = (fd_safetystockDao) fd_safetystockdao;
	}
	@Override
	public List adminfindfd_safetystocklist(
											String sort,
											Integer start, int number) {


		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();

		String sql = "select * from fd_safetystock where 1=1 ";
		/*if(num !=""){
			sql+=" and num=? ";
			paramlist.add(num);
			System.out.println(paramlist);
		}
		if(sparepartNum!=""){
			sql+=" and sparepartNum=? ";
			paramlist.add(sparepartNum);
		}
		if(sparepartName!=""){
			sql+=" and sparepartName=? ";
			paramlist.add(sparepartName);
		}
		if(Unit!=""){
			sql+=" and Unit=? ";
			paramlist.add(Unit);
		}
		if(unitprice!=""){
			sql+=" and unitprice=? ";
			paramlist.add(unitprice);
		}
		if(inuse!=""){
			sql+=" and inuse=? ";
			paramlist.add(inuse);
		}
		if(inventary!=""){
			sql+=" and inventary=? ";
			paramlist.add(Unit);
		}
		if(ss!=""){
			sql+=" and ss=? ";
			paramlist.add(ss);
		}
		if(R!=""){
			sql+=" and R=? ";
			paramlist.add(R);
		}
		if(maxInventory!=""){
			sql+=" and maxInventory=? ";
			paramlist.add(maxInventory);
		}
		if(sstime !=""){
			sql+=" and sstime=? ";
			paramlist.add(sstime);
		}*/
		if(sort!=""){
			sql+=" order by "+sort+" desc ";
		}
		sql += " limit ?, ? ";
		paramlist.add(start);
		paramlist.add(number);
		System.out.println(paramlist);
		return fd_safetystockdao.exesqlrelist(sql, paramlist);
	}

	@Override
	public List adminfindfd_safetystocklistcount() {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from fd_safetystock where 1=1 ";
		/*if(num!=""){
			sql+=" and num=? ";
			paramlist.add(num);
		}
		if(sparepartNum!=""){
			sql+=" and sparepartNum=? ";
			paramlist.add(sparepartNum);
		}
		if(sparepartName!=""){
			sql+=" and sparepartName=? ";
			paramlist.add(sparepartName);
		}
		if(Unit!=""){
			sql+=" and Unit=? ";
			paramlist.add(Unit);
		}
		if(unitprice!=""){
			sql+=" and unitprice=? ";
			paramlist.add(unitprice);
		}
		if(inuse!=""){
			sql+=" and inuse=? ";
			paramlist.add(inuse);
		}
		if(inventary!=""){
			sql+=" and inventary=? ";
			paramlist.add(inventary);
		}
		if(ss!=""){
			sql+=" and ss=? ";
			paramlist.add(ss);
		}
		if(R!=""){
			sql+=" and R=? ";
			paramlist.add(R);
		}
		if(maxInventory!=""){
			sql+=" and maxInventory=? ";
			paramlist.add(maxInventory);
		}if(sstime !=""){
			sql+=" and sstime=? ";
			paramlist.add(sstime);
		}*/

		return fd_safetystockdao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindfd_safetystocklist() {
		// TODO Auto-generated method stub
		String sql = "select * from fd_safetystock where 1=1 ";
		return fd_safetystockdao.exesqlrelist(sql, null);
	}
	@Override
	public List admindeletefd_SafetyStocklist() {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "delete * from fd_safetystock where 1=1 ";
		fd_safetystockdao.exesql(sql, paramlist);
		return null;
	}
	@Override
	public void adminrealsupplyedit() {
		// TODO Auto-generated method stub

	}
	@Override
	public List desstorecount(String jqcode, String desbdcode, String qccode,
			String qcname) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select * from fd_safetystock where sparepartNum="+desbdcode+" and num="+jqcode+" and Unit="+qccode+" and unitprice= '"+qcname+"' ";
		String sql1 = "select * from fd_safetystock where sparepartNum="+desbdcode+" and num="+jqcode+" and Unit="+qccode+" and unitprice= '"+qcname+"'";
		return fd_safetystockdao.exesqlrelist(sql1, paramlist);
	}
	@Override
	public void updatestorehouse(String jqcode, String bdcode,
			String desbdcode, String qccode, String qcname, Integer new_store,
			Integer old_store) {
		// TODO Auto-generated method stub
		String sql = "update fd_safetystock set currentinventory="+new_store+" where sparepartNum="+desbdcode+" and num="+jqcode+" and Unit="+qccode+" ";
		String sql1 = "update fd_safetystock set currentinventory="+old_store+" where sparepartNum="+bdcode+" and num="+jqcode+" and Unit="+qccode+" ";
		fd_safetystockdao.addSumplan(sql);
		fd_safetystockdao.addSumplan(sql1);
		System.out.println(sql);
		System.out.println(sql1);
	}
	@Override
	public void insertstorehouse(String jqcode, String bdcode,
			String desbdcode, String zbcode,String qccode, String qcname, String unit,String unitprice,String dzys,Integer new_store,
			Integer old_store) {
		// TODO Auto-generated method stub
		String sql = "insert into fd_safetystock(initinventory,currentinventory,sparepartNum,num,sparepartName,Unit,unitprice,unit,unitprice,dzys) values(0,'"+new_store+"','"+desbdcode+"','"+jqcode+"','"+zbcode+"','"+qccode+"','"+qcname+"','"+unit+"','"+unitprice+"','"+dzys+"')";
		//String sql0 = "update fd_safetystock set sparepartName="+old_store+" where sparepartNum="+sparepartNum+" and num="+num+" and Unit="+Unit+" and unitprice='"+unitprice+"'";
		//String sql = "insert fd_safetystock set currentinventory="+new_store+" where sparepartNum="+desbdcode+" and num="+num+" and Unit="+Unit+" and unitprice="+unitprice+"";
		String sql1 = "update fd_safetystock set currentinventory="+old_store+" where sparepartNum="+bdcode+" and num="+jqcode+" and Unit="+qccode+" and unitprice='"+qcname+"'";
		fd_safetystockdao.addSumplan(sql);
		fd_safetystockdao.addSumplan(sql1);
		System.out.println(sql);
		System.out.println(sql1);
	}
	@Override
	public List querybdcode(String jqcode,String qccode) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql1 = "select sparepartNum,currentinventory from fd_safetystock where num="+jqcode+" and Unit="+qccode+"";
		return fd_safetystockdao.exesqlrelist(sql1, paramlist);
	}
	@Override
	public void insertstorerecord(String jqcode, String bdcode,
			String desbdcode, String zbcode, String qccode, String qcname,
			Integer recordnumber, String create_people, String createtime,
			String receive_people) {
		// TODO Auto-generated method stub
		String sql = "insert into dt_store_datarecordlist(num,from_store,to_store,sparepartName,Unit,unitprice,this_allot_number,create_people,createtime,receive_people) values('"+jqcode+"','"+bdcode+"','"+desbdcode+"','"+zbcode+"','"+qccode+"','"+qcname+"','"+recordnumber+"','"+create_people+"','"+createtime+"','"+receive_people+"')";
		fd_safetystockdao.addSumplan(sql);
		System.out.println(sql);
	}
	@Override
	public List adminfindstorerecordlist(String jqcode, String from_store,
			String to_store, String qccode, String qcname, String sort,
			Integer start, int number) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select * from dt_store_datarecordlist where 1=1 ";
		if(jqcode!=""){
			sql+=" and num=? ";
			paramlist.add(jqcode);
			System.out.println(paramlist);
		}
		if(from_store!=""){
			sql+=" and from_store=? ";
			paramlist.add(from_store);
		}
		if(to_store!=""){
			sql+=" and to_store=? ";
			paramlist.add(to_store);
		}
		if(qccode!=""){
			sql+=" and Unit=? ";
			paramlist.add(qccode);
		}
		if(qcname!=""){
			sql+=" and unitprice=? ";
			paramlist.add(qcname);
		}
		if(sort!=""){
			sql+=" order by "+sort+" desc ";
		}
		sql += " limit ?, ? ";
		paramlist.add(start);
		paramlist.add(number);
		System.out.println(paramlist);
		return fd_safetystockdao.exesqlrelist(sql, paramlist);
	}
	@Override
	public List adminfindstorerecordlistcount(String jqcode, String from_store,
			String to_store, String qccode, String qcname) {
		// TODO Auto-generated method stub
		List<Object> paramlist = new ArrayList();
		String sql = "select count(*) count from dt_store_datarecordlist where 1=1 ";
		if(jqcode!=""){
			sql+=" and num=? ";
			paramlist.add(jqcode);
		}
		if(from_store!=""){
			sql+=" and from_store=? ";
			paramlist.add(from_store);
		}
		if(to_store!=""){
			sql+=" and to_store=? ";
			paramlist.add(to_store);
		}
		if(qccode!=""){
			sql+=" and Unit=? ";
			paramlist.add(qccode);
		}
		if(qcname!=""){
			sql+=" and unitprice=? ";
			paramlist.add(qcname);
		}
		return fd_safetystockdao.exesqlrelist(sql, paramlist);
	}
	@Override
	public void refreshstorerecord(Integer allot_id,String jqcode, String from_store,
			String to_store, String qccode, String qcname,
			Integer this_allot_number, Integer old_allot_number) {
		// TODO Auto-generated method stub
		//更新当次的调拨量
		String sql = "update dt_store_datarecordlist set this_allot_number="+this_allot_number+" where allot_id='"+allot_id+"' and num='"+jqcode+"' and from_store='"+from_store+"' and to_store='"+to_store+"' and Unit='"+qccode+"' and unitprice='"+qcname+"'";
		fd_safetystockdao.addSumplan(sql);
		//更新相关记录的所有累计调拨量
		if(old_allot_number>this_allot_number){
			Integer differ_allotnumber=old_allot_number-this_allot_number;
			//旧的调拨数量更大，to_store减少
			String sql2 = "update fd_safetystock set currentinventory=currentinventory-"+differ_allotnumber+" where num='"+jqcode+"' and sparepartNum='"+to_store+"' and Unit='"+qccode+"' and unitprice='"+qcname+"'";
			//from_store增加,注意仓库的bdcode是指的仓库号，不是部队号！
			String sql3 = "update fd_safetystock set currentinventory=currentinventory+"+differ_allotnumber+" where num='"+jqcode+"' and sparepartNum='"+from_store+"' and Unit='"+qccode+"' and unitprice='"+qcname+"'";
			fd_safetystockdao.addSumplan(sql2);
			fd_safetystockdao.addSumplan(sql3);
		}else{
			Integer differ_allotnumber=this_allot_number-old_allot_number;
			//旧的调拨数量更小，to_store增加
			String sql2 = "update fd_safetystock set currentinventory=currentinventory+"+differ_allotnumber+" where num='"+jqcode+"' and sparepartNum='"+to_store+"' and Unit='"+qccode+"' and unitprice='"+qcname+"'";
			//from_store减少,注意仓库的bdcode是指的仓库号，不是部队号！
			String sql3 = "update fd_safetystock set currentinventory=currentinventory-"+differ_allotnumber+" where num='"+jqcode+"' and sparepartNum='"+from_store+"' and Unit='"+qccode+"' and unitprice='"+qcname+"'";
			fd_safetystockdao.addSumplan(sql2);
			fd_safetystockdao.addSumplan(sql3);
		}
		System.out.println("执行完了");
	}
	@Override
	public List adminfindstorerecordlist() {
		// TODO Auto-generated method stub
		String sql = "select * from dt_store_datarecordlist where 1=1 ";
		return fd_safetystockdao.exesqlrelist(sql, null);
	}
	
}
