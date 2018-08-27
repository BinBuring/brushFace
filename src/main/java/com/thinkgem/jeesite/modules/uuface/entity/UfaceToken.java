/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uuface.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * tokenEntity
 * @author zb
 * @version 2018-08-27
 */
public class UfaceToken extends DataEntity<UfaceToken> {
	
	private static final long serialVersionUID = 1L;
	private String token;		// 沃土访问token值
	private String state;		// token状态。1-正常；0-作废
	
	public UfaceToken() {
		super();
	}

	public UfaceToken(String id){
		super(id);
	}

	@Length(min=1, max=128, message="沃土访问token值长度必须介于 1 和 128 之间")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	@Length(min=1, max=1, message="token状态。1-正常；0-作废长度必须介于 1 和 1 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
}