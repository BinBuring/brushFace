/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.SysParam;

/**
 * 系统参数DAO接口
 * @author 张斌
 * @version 2018-08-28
 */
@MyBatisDao
public interface SysParamDao extends CrudDao<SysParam> {
	
}