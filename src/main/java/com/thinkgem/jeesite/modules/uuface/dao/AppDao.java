/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uuface.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.uuface.entity.App;

/**
 * 接口app数据DAO接口
 * @author zb
 * @version 2018-08-27
 */
@MyBatisDao
public interface AppDao extends CrudDao<App> {
	
}