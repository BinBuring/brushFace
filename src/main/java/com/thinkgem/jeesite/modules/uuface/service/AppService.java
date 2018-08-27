/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uuface.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.uuface.entity.App;
import com.thinkgem.jeesite.modules.uuface.dao.AppDao;

/**
 * 接口app数据Service
 * @author zb
 * @version 2018-08-27
 */
@Service
@Transactional(readOnly = true)
public class AppService extends CrudService<AppDao, App> {

	public App get(String id) {
		return super.get(id);
	}
	
	public List<App> findList(App app) {
		return super.findList(app);
	}
	
	public Page<App> findPage(Page<App> page, App app) {
		return super.findPage(page, app);
	}
	
	@Transactional(readOnly = false)
	public void save(App app) {
		super.save(app);
	}
	
	@Transactional(readOnly = false)
	public void delete(App app) {
		super.delete(app);
	}
	
}