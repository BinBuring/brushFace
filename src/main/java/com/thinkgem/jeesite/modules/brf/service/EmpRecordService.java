/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.brf.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.brf.entity.EmpRecord;
import com.thinkgem.jeesite.modules.brf.dao.EmpRecordDao;

/**
 * 员工记录Service
 * @author 张斌
 * @version 2018-08-28
 */
@Service
@Transactional(readOnly = true)
public class EmpRecordService extends CrudService<EmpRecordDao, EmpRecord> {

	public EmpRecord get(String id) {
		return super.get(id);
	}
	/**
	 * 通过时间过滤重复数据
	 * @param date
	 * @return
	 */
	public boolean getByDate(Date date) {
		int a = dao.getByDate(date);
		if (a>0) {
			return false;
		}else {
			return true;
		}
	}
	
	public List<EmpRecord> findList(EmpRecord empRecord) {
		return super.findList(empRecord);
	}
	
	public Page<EmpRecord> findPage(Page<EmpRecord> page, EmpRecord empRecord) {
		return super.findPage(page, empRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(EmpRecord empRecord) {
		super.save(empRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(EmpRecord empRecord) {
		super.delete(empRecord);
	}
	
}