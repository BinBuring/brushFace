/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uuface.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sun.misc.BASE64Encoder;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.GsonUtils;
import com.thinkgem.jeesite.common.utils.InterfaceUtils;
import com.thinkgem.jeesite.modules.brf.entity.Device;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.uuface.dao.AppDao;
import com.thinkgem.jeesite.modules.uuface.entity.App;
import com.thinkgem.jeesite.modules.uuface.entity.ResultData;
import com.thinkgem.jeesite.modules.uuface.entity.UfaceToken;

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
	 * 人员照片注册
	 * @param device
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public ResultData empimageUrl(User user,HttpServletRequest request){
		UfaceToken token = getToken();
		App app = getApp();
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("appId", app.getAppid());
		maps.put("token", token.getToken());
		maps.put("guid", user.getId());
		//设置照片的路径
		String path = request.getSession().getServletContext().getRealPath("/");
		String url = user.getPhoto();
		url = user.getPhoto().substring(url.indexOf("/")+1, url.length());
		url = user.getPhoto().substring(url.indexOf("/")+1, url.length()+1);
		//TODO 我也不知道为什么+1，有机会研究
		url = url.replaceAll("/", "\\\\");
		url = path.substring(0, path.length()-1)+url;
		System.out.println(url);
		url = getImageBinary(new File(url));
		//maps.put("imageUrl", "http://124.126.150.115:8080"+user.getPhoto());
		maps.put("img", url);
		String response = null;
		ResultData hr = new ResultData();
		try {
			//response = InterfaceUtils.HttpPost(app.getApiurl()+app.getAppid() + "/person/"+ user.getId()+"/face/imageUrl", InterfaceUtils.getMapToString(maps));
			response = InterfaceUtils.HttpPost(app.getApiurl()+app.getAppid() + "/person/"+ user.getId()+"/face", InterfaceUtils.getMapToString(maps));
			//hr = GsonUtils.getObjectFromJson(response, ResultData.class);
			System.out.println(hr);
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
	private  String getImageBinary(File f){     
		BASE64Encoder encoder = new BASE64Encoder();   //加密
        BufferedImage bi;      
        try {      
            bi = ImageIO.read(f);      
            ByteArrayOutputStream baos = new ByteArrayOutputStream();      
            ImageIO.write(bi, "jpg", baos);      
            byte[] bytes = baos.toByteArray();      

            return encoder.encodeBuffer(bytes).trim();      
        } catch (IOException e) {      
            e.printStackTrace();      
        }      
        return null;      
    }
	/**
	 * 设备授权人员
	 * @param device
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public ResultData devEmp(Device device,String personGuids){
		UfaceToken token = getToken();
		App app = getApp();
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("appId", app.getAppid());
		maps.put("token", token.getToken());
		maps.put("deviceKey", device.getSeq());
		maps.put("personGuids", personGuids);
		maps.put("passTimes", "");
		String response = null;
		ResultData hr = new ResultData();
		try {
			response = InterfaceUtils.HttpPost(app.getApiurl()+app.getAppid() + "/device/"+ device.getSeq()+"/people", InterfaceUtils.getMapToString(maps));
			hr = GsonUtils.getObjectFromJson(response, ResultData.class);
			System.out.println(hr);
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
	public ResultData createEmp(User user){
		UfaceToken token = getToken();
		App app = getApp();
		//String name = new String(user.getName().getBytes("ISO-8859-1"), "UTF-8"); 
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("appId", app.getAppid());
		maps.put("token", token.getToken());
		try {
			maps.put("name", URLEncoder.encode(user.getName(), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
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
		try {
			maps.put("name", URLEncoder.encode(device.getName(),"utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
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