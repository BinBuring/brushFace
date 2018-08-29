/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 系统参数Entity
 * @author 张斌
 * @version 2018-08-28
 */
public class SysParam extends DataEntity<SysParam> {
	
	private static final long serialVersionUID = 1L;
	private String type;		// 参数类型
	private String content;		// 参数内容
	
	public SysParam() {
		super();
	}

	public SysParam(String id){
		super(id);
	}

	@Length(min=0, max=255, message="参数类型长度必须介于 0 和 255 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="参数内容长度必须介于 0 和 255 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}