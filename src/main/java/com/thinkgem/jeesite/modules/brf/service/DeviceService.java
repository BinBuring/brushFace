/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.brf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.brf.entity.Device;
import com.thinkgem.jeesite.modules.brf.dao.DeviceDao;

/**
 * 设备管理Service
 * @author 张斌
 * @version 2018-08-26
 */
@Service
@Transactional(readOnly = true)
public class DeviceService extends CrudService<DeviceDao, Device> {

	public Device get(String id) {
		return super.get(id);
	}
	public Device getBySeq(String id) {
		return dao.getBySeq(id);
	}
	
	public List<Device> findList(Device device) {
		return super.findList(device);
	}
	
	public Page<Device> findPage(Page<Device> page, Device device) {
		return super.findPage(page, device);
	}
	
	@Transactional(readOnly = false)
	public void save(Device device) {
		super.save(device);
	}
	
	@Transactional(readOnly = false)
	public void delete(Device device) {
		super.delete(device);
	}
	
}