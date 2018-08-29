/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.brf.dao;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.brf.entity.EmpRecord;

/**
 * 员工记录DAO接口
 * @author 张斌
 * @version 2018-08-28
 */
@MyBatisDao
public interface EmpRecordDao extends CrudDao<EmpRecord> {

	int getByDate(Date date);
	
}