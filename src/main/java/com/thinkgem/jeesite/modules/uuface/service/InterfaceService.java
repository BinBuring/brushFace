/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uuface.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.sql.visitor.functions.Now;
import com.google.gson.Gson;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.GsonUtils;
import com.thinkgem.jeesite.common.utils.InterfaceUtils;
import com.thinkgem.jeesite.modules.brf.entity.Device;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.uuface.entity.App;
import com.thinkgem.jeesite.modules.uuface.entity.ResultData;
import com.thinkgem.jeesite.modules.uuface.entity.UfaceToken;
import com.thinkgem.jeesite.modules.uuface.dao.AppDao;

/**
 * 接口appService
 * @author zb
 * @version 2018-08-27
 */
@Service
@Transactional(readOnly = false)
public class InterfaceService extends CrudService<AppDao, App> {
	
	@Autowired
	UfaceTokenService ufaceTokenService;
	@Autowired
	AppService	appService;
	@Autowired
	ResultDataService resultDataService;
	
	/**
	 * 设备授权人员
	 * @param device
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public ResultData devEmp(Device device,String personGuids,HttpServletRequest request) throws UnsupportedEncodingException {
		UfaceToken token = getToken();
		App app = getApp();
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("appId", app.getAppid());
		maps.put("token", token.getToken());
		maps.put("deviceKey", device.getId());
		maps.put("personGuids", personGuids);
		maps.put("passTimes", "");
		String response = null;
		ResultData hr = new ResultData();
		try {
			response = InterfaceUtils.HttpPost(app.getApiurl()+app.getAppid() + "/person", InterfaceUtils.getMapToString(maps));
			hr = GsonUtils.getObjectFromJson(response, ResultData.class);
			System.out.println("=====================================");
			System.out.println("执行结果：" + response);
			System.out.println("=====================================");
		}
		catch(Exception ex){
			System.out.println("=====================================");
			System.out.println("系统异常：" + ex.getMessage());
			System.out.println("=====================================");
		}
		return hr;
	}
	/**
	 * 添加员工
	 * @param device
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public ResultData createEmp(User user,HttpServletRequest request) throws UnsupportedEncodingException {
		UfaceToken token = getToken();
		App app = getApp();
		//String name = new String(user.getName().getBytes("ISO-8859-1"), "UTF-8"); 
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("appId", app.getAppid());
		maps.put("token", token.getToken());
		maps.put("name", URLEncoder.encode(user.getName(), "utf-8"));
		maps.put("idNo", user.getIdCard());
		maps.put("phone", user.getPhone());
		maps.put("tag", user.getRemarks());
		maps.put("type", user.getUserType());
		String response = null;
		ResultData hr = new ResultData();
		try {
			response = InterfaceUtils.post(app.getApiurl()+app.getAppid() + "/person", InterfaceUtils.getMapToString(maps));
			hr = GsonUtils.getObjectFromJson(response, ResultData.class);
			System.out.println("=====================================");
			System.out.println("执行结果：" + response);
			System.out.println("=====================================");
		}
		catch(Exception ex){
			System.out.println("=====================================");
			System.out.println("系统异常：" + ex.getMessage());
			System.out.println("=====================================");
		}
		return hr;
	}
	/**
	 * 添加设备
	 * @param device
	 * @return
	 */
	public ResultData createDevice(Device device) {
		UfaceToken token = getToken();
		App app = getApp();
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("appId", app.getAppid());
		maps.put("token", token.getToken());
		maps.put("deviceKey", device.getSeq());
		maps.put("name", device.getName());
		//maps.put("tag", "");
		String response = null;
		ResultData hr = new ResultData();
		try {
			response = InterfaceUtils.HttpPost(app.getApiurl()+app.getAppid() + "/device", InterfaceUtils.getMapToString(maps));
			hr = GsonUtils.getObjectFromJson(response, ResultData.class);
			System.out.println("=====================================");
			System.out.println("执行结果：" + response);
			System.out.println("=====================================");
		}
		catch(Exception ex){
			System.out.println("=====================================");
			System.out.println("系统异常：" + ex.getMessage());
			System.out.println("=====================================");
		}
		return hr;
	}
	/**
	 * 更新token
	 * @return
	 */
	public UfaceToken getToken() {
		long hh = 36000000; //10小时以上更新token
		//先判断token是否需要更新
		UfaceToken token = ufaceTokenService.findList(null).get(0);
		System.out.println(token.getCreateDate());
		if (token.getCreateDate().getTime()+hh > new Date().getTime()) {  //有效期内
			return token;
		}else {
			token = createToken();
			token.setState("1");
			ufaceTokenService.save(token);
			return token;
		}
	}
	/**
	 * 获取应用信息
	 * @return
	 */
	private App getApp(){
		App app = appService.findList(null).get(0);
		return app;
	}
	/**
	 * 获取到token
	 * @param app
	 * @return
	 */
	private UfaceToken createToken() {
		App app =getApp();
		UfaceToken token = new UfaceToken();
		String timestamp = Long.toString(System.currentTimeMillis());
		String sign = InterfaceUtils.MD5(app.getAppkey() + timestamp + app.getAppsecret());
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("appId", app.getAppkey());
		maps.put("appKey", app.getAppkey());
		maps.put("timestamp", timestamp);
		maps.put("sign", sign);
		try {
			String response = InterfaceUtils.HttpPost(app.getApiurl()+app.getAppid() + "/auth", InterfaceUtils.getMapToString(maps));
			System.out.println(response);
			ResultData hr = GsonUtils.getObjectFromJson(response, ResultData.class);
			resultDataService.save(hr);
			System.out.println("=====================================");
			System.out.println("执行结果：" + response);
			System.out.println("=====================================");
		}
		catch(Exception ex){
			System.out.println("=====================================");
			System.out.println("系统异常：" + ex.getMessage());
			System.out.println("=====================================");
		}
		
		return token;
	}
	
}