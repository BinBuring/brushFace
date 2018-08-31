/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.brf.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.brf.entity.Device;

/**
 * 设备管理DAO接口
 * @author 张斌
 * @version 2018-08-26
 */
@MyBatisDao
public interface DeviceDao extends CrudDao<Device> {

	Device getBySeq(String id);
	
}