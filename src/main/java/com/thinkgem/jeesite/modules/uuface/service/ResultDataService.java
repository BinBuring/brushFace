/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uuface.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.uuface.entity.ResultData;
import com.thinkgem.jeesite.modules.uuface.dao.ResultDataDao;

/**
 * 接口返回信息Service
 * @author zb
 * @version 2018-08-27
 */
@Service
@Transactional(readOnly = true)
public class ResultDataService extends CrudService<ResultDataDao, ResultData> {

	public ResultData get(String id) {
		return super.get(id);
	}
	
	public List<ResultData> findList(ResultData resultData) {
		return super.findList(resultData);
	}
	
	public Page<ResultData> findPage(Page<ResultData> page, ResultData resultData) {
		return super.findPage(page, resultData);
	}
	
	@Transactional(readOnly = false)
	public void save(ResultData resultData) {
		super.save(resultData);
	}
	
	@Transactional(readOnly = false)
	public void delete(ResultData resultData) {
		super.delete(resultData);
	}
	
}