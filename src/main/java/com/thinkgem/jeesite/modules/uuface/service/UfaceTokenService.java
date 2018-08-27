/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uuface.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.uuface.entity.UfaceToken;
import com.thinkgem.jeesite.modules.uuface.dao.UfaceTokenDao;

/**
 * tokenService
 * @author zb
 * @version 2018-08-27
 */
@Service
@Transactional(readOnly = true)
public class UfaceTokenService extends CrudService<UfaceTokenDao, UfaceToken> {

	public UfaceToken get(String id) {
		return super.get(id);
	}
	
	public List<UfaceToken> findList(UfaceToken ufaceToken) {
		return super.findList(ufaceToken);
	}
	
	public Page<UfaceToken> findPage(Page<UfaceToken> page, UfaceToken ufaceToken) {
		return super.findPage(page, ufaceToken);
	}
	
	@Transactional(readOnly = false)
	public void save(UfaceToken ufaceToken) {
		super.save(ufaceToken);
	}
	
	@Transactional(readOnly = false)
	public void delete(UfaceToken ufaceToken) {
		super.delete(ufaceToken);
	}
	
}