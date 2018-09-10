/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uuface.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 接口app数据Entity
 * @author zb
 * @version 2018-08-27
 */
public class App extends DataEntity<App> {
	
	private static final long serialVersionUID = 1L;
	private String apiurl;		// 接口地址
	private String appid;		// 应用id
	private String appkey;		// key
	private String appsecret;		// 标识
	private String type;		// 1是沃土，2是aiot
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public App() {
		super();
	}

	public App(String id){
		super(id);
	}

	@Length(min=0, max=255, message="接口地址长度必须介于 0 和 255 之间")
	public String getApiurl() {
		return apiurl;
	}

	public void setApiurl(String apiurl) {
		this.apiurl = apiurl;
	}
	
	@Length(min=0, max=255, message="应用id长度必须介于 0 和 255 之间")
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	@Length(min=0, max=255, message="key长度必须介于 0 和 255 之间")
	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}
	
	@Length(min=0, max=255, message="标识长度必须介于 0 和 255 之间")
	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}
	
}