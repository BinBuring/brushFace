/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.brf.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 设备管理Entity
 * @author 张斌
 * @version 2018-08-26
 */
public class Device extends DataEntity<Device> {
	
	private static final long serialVersionUID = 1L;
	private String seq;		// 设备序列号
	private String name;		// 设备名称
	private String aisle;		// 识别设备（通道名称）
	private String ver;		// 设备版本号
	private String startus;		// 1启动 2关闭
	
	public Device() {
		super();
	}

	public Device(String id){
		super(id);
	}

	@Length(min=1, max=255, message="设备序列号长度必须介于 1 和 255 之间")
	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	@Length(min=0, max=255, message="设备名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="识别设备（通道名称）长度必须介于 0 和 255 之间")
	public String getAisle() {
		return aisle;
	}

	public void setAisle(String aisle) {
		this.aisle = aisle;
	}
	
	@Length(min=0, max=255, message="设备版本号长度必须介于 0 和 255 之间")
	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}
	
	@Length(min=0, max=1, message="1启动 2关闭长度必须介于 0 和 1 之间")
	public String getStartus() {
		return startus;
	}

	public void setStartus(String startus) {
		this.startus = startus;
	}
	
}