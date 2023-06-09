package com.hd.common.service.impl;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import com.hd.common.dao.IBaseDao;
import com.hd.common.service.IBaseService;

public abstract class BaseService<M extends java.io.Serializable, PK extends java.io.Serializable> implements IBaseService<M, PK> {
    
    protected IBaseDao<M, PK> baseDao;
    
    public abstract void setBaseDao(IBaseDao<M, PK> baseDao);
    
    InputStream in = this.getClass().getClassLoader().getResourceAsStream("messages.properties");
    Properties props = new Properties();
    
    @Override
    public PK save(M model) {
    baseDao.save(model);
        return  baseDao.save(model);
    }
    
    @Override
    public void merge(M model) {
        baseDao.merge(model);
    }

   @Override
    public void saveOrUpdate(M model) {
        baseDao.saveOrUpdate(model);
    }

    @Override
    public void update(M model) {
        baseDao.update(model);
    }
    
    @Override
    public void delete(PK id) {
        baseDao.delete(id);
    }

    @Override
    public void deleteObject(M model) {
        baseDao.deleteObject(model);
    }

    @Override
    public M get(PK id) {
        return baseDao.get(id);
    }   
    
    @Override
    public int countAll() {
        return baseDao.countAll();
    }

    @Override
    public List<M> listAll() {
        return baseDao.listAll();
    }
    
    @Override
    public void flush(){
    	baseDao.flush();
    }
    @Override
    public void clear(){
    	baseDao.clear();
    }
    
    @Override
    public void deleteTable(String sql){
    	baseDao.deleteTable(sql);
    }
    
    @Override
    public void addSumplan(String sql){
    	baseDao.addSumplan(sql);
    }
}
