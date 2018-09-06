/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.SysParam;
import com.thinkgem.jeesite.modules.sys.dao.SysParamDao;

/**
 * 系统参数Service
 * @author 张斌
 * @version 2018-08-28
 */
@Service
@Transactional(readOnly = true)
public class SysParamService extends CrudService<SysParamDao, SysParam> {
	
	public int getDays(String id){
		return super.getDays(id);
	}

	public SysParam get(String id) {
		return super.get(id);
	}
	
	public List<SysParam> findList(SysParam sysParam) {
		return super.findList(sysParam);
	}
	
	public Page<SysParam> findPage(Page<SysParam> page, SysParam sysParam) {
		return super.findPage(page, sysParam);
	}
	
	@Transactional(readOnly = false)
	public void save(SysParam sysParam) {
		super.save(sysParam);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysParam sysParam) {
		super.delete(sysParam);
	}
	
}