/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.brf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.brf.entity.DevUser;
import com.thinkgem.jeesite.modules.brf.dao.DevUserDao;

/**
 * 设备人员授权Service
 * @author 张斌
 * @version 2018-08-26
 */
@Service
@Transactional(readOnly = true)
public class DevUserService extends CrudService<DevUserDao, DevUser> {

	public DevUser get(String id) {
		return super.get(id);
	}
	
	public List<DevUser> findList(DevUser devUser) {
		return super.findList(devUser);
	}
	
	public Page<DevUser> findPage(Page<DevUser> page, DevUser devUser) {
		return super.findPage(page, devUser);
	}
	
	@Transactional(readOnly = false)
	public void save(DevUser devUser) {
		super.save(devUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(DevUser devUser) {
		super.delete(devUser);
	}
	
}