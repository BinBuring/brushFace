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
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sun.misc.BASE64Encoder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.GsonUtils;
import com.thinkgem.jeesite.common.utils.InterfaceUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.brf.entity.Device;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.uuface.dao.AppDao;
import com.thinkgem.jeesite.modules.uuface.entity.App;
import com.thinkgem.jeesite.modules.uuface.entity.ResultData;
import com.thinkgem.jeesite.modules.uuface.entity.ResultFaceData;
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
	 * 员工删除
	 * @param device
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public ResultData deleteEmp(User user){
		UfaceToken token = getToken("1");
		Map<String, String> maps = new HashMap<String, String>();
		App app = getApp("1");
		maps.put("appId", app.getAppid());
		maps.put("token", token.getToken());
		maps.put("guid", user.getGuid());//员工id
		String response = null;
		ResultData hr = new ResultData();
		try {
			response = InterfaceUtils.HttpDelete(app.getApiurl()+app.getAppid() + "/person/" +  user.getGuid(), InterfaceUtils.getMapToString(maps));
			hr = GsonUtils.getObjectFromJson(response, ResultData.class);
			System.out.println("=====================================");
			System.out.println("执行结果：" + response);
			System.out.println("=====================================");
		}
		catch(Exception ex){
			System.out.println("=====================================");
			System.out.println("系统异常：" + ex.getMessage());
			System.out.println("=====================================");
			return null;
		}
		return hr;
	}
	/**
	 * 设备删除
	 * @param device
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public ResultData deleteDev(Device dev){
		UfaceToken token = getToken("1");
		Map<String, String> maps = new HashMap<String, String>();
		App app = getApp("1");
		maps.put("appId", app.getAppid());
		maps.put("token", token.getToken());
		maps.put("deviceKey", dev.getSeq());//设备序列号
		String response = null;
		ResultData hr = new ResultData();
		try {
			response = InterfaceUtils.HttpDelete(app.getApiurl()+app.getAppid() + "/device/" +  dev.getSeq(), InterfaceUtils.getMapToString(maps));
			hr = GsonUtils.getObjectFromJson(response, ResultData.class);
			System.out.println("=====================================");
			System.out.println("执行结果：" + response);
			System.out.println("=====================================");
		}
		catch(Exception ex){
			System.out.println("=====================================");
			System.out.println("系统异常：" + ex.getMessage());
			System.out.println("=====================================");
			return null;
		}
		return hr;
	}
	/**
	 * 照片删除
	 * @param device
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public ResultData deletePhoto(User user){
		UfaceToken token = getToken("1");
		Map<String, String> maps = new HashMap<String, String>();
		App app = getApp("1");
		maps.put("appId", app.getAppid());
		maps.put("token", token.getToken());
		maps.put("guid", user.getPhotoId());//照片id
		maps.put("personGuid", user.getGuid());
		String response = null;
		ResultData hr = new ResultData();
		try {
			response = InterfaceUtils.HttpDelete(app.getApiurl()+app.getAppid() + "/person/" + user.getGuid()+"/face/"+user.getPhotoId(), InterfaceUtils.getMapToString(maps));
			hr = GsonUtils.getObjectFromJson(response, ResultData.class);
			System.out.println("=====================================");
			System.out.println("执行结果：" + response);
			System.out.println("=====================================");
		}
		catch(Exception ex){
			System.out.println("=====================================");
			System.out.println("系统异常：" + ex.getMessage());
			System.out.println("=====================================");
			return null;
		}
		return hr;
	}
	/**
	 * 修改员工
	 * @param device
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public ResultData updateEmp(User user){
		UfaceToken token = getToken("1");
		App app = getApp("1");
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("appId", app.getAppid());
		maps.put("token", token.getToken());
		maps.put("guid", user.getGuid());
		try {
			maps.put("name", URLEncoder.encode(user.getName(), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		/*maps.put("idNo", user.getIdCard());
		maps.put("phone", user.getPhone());
		maps.put("tag", user.getRemarks());
		maps.put("type", user.getUserType());*/
		String response = null;
		ResultData hr = new ResultData();
		try {
			response = InterfaceUtils.HttpPut(app.getApiurl()+app.getAppid() + "/person/"+user.getGuid(), InterfaceUtils.getMapToString(maps));
			hr = GsonUtils.getObjectFromJson(response, ResultData.class);
			System.out.println("=====================================");
			System.out.println("执行结果：" + response);
			System.out.println("=====================================");
		}
		catch(Exception ex){
			System.out.println("=====================================");
			System.out.println("系统异常：" + ex.getMessage());
			System.out.println("=====================================");
			return null;
		}
		return hr;
	}
	/**
	 * 员工查询
	 * @param device
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public ResultData selectEmp(User user){
		UfaceToken token = getToken("1");
		Map<String, String> maps = new HashMap<String, String>();
		App app = getApp("1");
		maps.put("appId", app.getAppid());
		maps.put("token", token.getToken());
		maps.put("guid", user.getGuid());
		String response = null;
		ResultData hr = new ResultData();
		try {
			response = InterfaceUtils.sendGet(app.getApiurl()+app.getAppid() + "/person/" + user.getGuid(), InterfaceUtils.getMapToString(maps));
			hr = GsonUtils.getObjectFromJson(response, ResultData.class);
			System.out.println("=====================================");
			System.out.println("执行结果：" + response);
			System.out.println("=====================================");
		}
		catch(Exception ex){
			System.out.println("=====================================");
			System.out.println("系统异常：" + ex.getMessage());
			System.out.println("=====================================");
			return null;
		}
		return hr;
	}
	/**
	 * 人脸检测
	 * @param device
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public ResultData faceDetect(User user,HttpServletRequest request){
		UfaceToken token = getToken("2");
		Map<String, String> maps = new HashMap<String, String>();
		App app = getApp("2");
		//设置照片的路径
		String path = request.getSession().getServletContext().getRealPath("/");
		String url = user.getPhoto();
		url = user.getPhoto().substring(url.indexOf("/")+1, url.length());
		url = user.getPhoto().substring(url.indexOf("/")+1, url.length()+1);
		path = path.replaceAll("\\\\", "/");
		url = path.substring(0, path.length()-1)+url;
		System.out.println(url);
		url = getImageBinary(new File(url));
		maps.put("imageBase64", url);
		String response = null;
		ResultData hr = new ResultData();
		JsonObject jsonObject = new JsonObject();
		JsonPrimitive j = new JsonPrimitive(url);
		JsonPrimitive b = new JsonPrimitive(true);
		jsonObject.add("imageBase64", j);
		jsonObject.add("returnAttributes", b);
		String param = jsonObject.toString();
		System.out.println(param);
		try {
			response = InterfaceUtils.HttpPost(app.getApiurl()+"/detection/detect",token.getToken(), param);
			System.out.println(response);
			hr = GsonUtils.getObjectFromJson(response, ResultData.class);
			System.out.println("=====================================");
			System.out.println("执行结果：" + response);
			System.out.println("=====================================");
		}
		catch(Exception ex){
			System.out.println("=====================================");
			System.out.println("系统异常：" + ex.getMessage());
			System.out.println("=====================================");
			return null;
		}
		return hr;
	}	
	public Map<String, String> personDetect(ResultData data){
		Map<String, String> map = new HashMap<String, String>();
		String jsonData = GsonUtils.getJsonFromObject(data.getData()); //把resuleData转成json字符串
	    JSONObject obj = JSONObject.fromObject(jsonData);				//把json字符串转成jsonObject
        JSONArray faces = (JSONArray) obj.get("faces");				//获取到返回faces的信息
        if (faces.size()>1) {										//1.判断有多少个人脸
        	map.put("success", "error");
			map.put("msg", "只能有一个人脸");
			return map;
		}
        JSONObject erMsg =  (JSONObject) faces.get(0);            	//去掉faces的[]
        String angle = erMsg.getString("angles");					//获取到faces的angles(数组形式，三个值在+-30以内)
        angle = angle.replaceAll("\\[", "");
        angle = angle.replaceAll("\\]", "");
		System.out.println(angle);
		String[] angles = angle.split(",");						
		for (int i = 0; i < angles.length; i++) {					//2.判断人脸侧脸角度
			int a = StringUtils.toInteger(angles[i]);
			if (-15>a && a<15) {
				map.put("success", "error");
				map.put("msg", "人物角度不能大于15");
				return map;
			}
		}
		String box = erMsg.getString("box");						//获取到faces的box
		JSONObject boxj = JSONObject.fromObject(box);
		double fw = boxj.getDouble("w");		//人脸宽
		double fh = boxj.getDouble("h");		//人脸高
		JSONObject image = (JSONObject) obj.get("image");		
		double iw = image.getDouble("w");		//图片宽
		double ih = image.getDouble("h");		//图片高
		double bi = (fw*fh)/(iw*ih);
		if (bi<0.1) {												//3.判断人脸占比
			map.put("success", "error");
			map.put("msg", "人脸比例不能小于10%");
			return map;
		}
		String attribute = erMsg.getString("attributes");						//获取到人脸属性
		JSONObject attributes = JSONObject.fromObject(attribute);
		int light = attributes.getInt("light");
		if(light<70){												//4.验证光线
			map.put("success", "error");
			map.put("msg", "光线太暗");
			return map;
		}else if (light>210) {
			map.put("success", "error");
			map.put("msg", "光线太亮");
			return map;
		}
		double blur = attributes.getDouble("blur");
		if (blur>0.4) {
			map.put("success", "error");
			map.put("msg", "人脸模糊");								//5.验证模糊度
			return map;
		}
		double asym = attributes.getDouble("asym");
		if (asym>0.1) {
			map.put("success", "error");
			map.put("msg", "左右不对称");								//6.验证对称
			return map;
		}
		map.put("success", "success");
		return map; 
	} 
	/**
	 * 人员照片注册
	 * @param device
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public ResultData empimageUrl(User user,HttpServletRequest request){
		UfaceToken token = getToken("1");
		Map<String, String> maps = new HashMap<String, String>();
		App app = getApp("1");
		maps.put("appId", app.getAppid());
		maps.put("token", token.getToken());
		maps.put("guid", user.getGuid());
		//maps.put("useUFaceCloud", "true");
		//设置照片的路径
		String path = request.getSession().getServletContext().getRealPath("/");
		String url = user.getPhoto();
		url = user.getPhoto().substring(url.indexOf("/")+1, url.length());
		url = user.getPhoto().substring(url.indexOf("/")+1, url.length()+1);
		//TODO 我也不知道为什么+1，有机会研究
		/*url = url.replaceAll("/", "\\\\");*/
		path = path.replaceAll("\\\\", "/");
		url = path.substring(0, path.length()-1)+url;
		System.out.println(url);
		url = getImageBinary(new File(url));
		//maps.put("imageUrl", "http://124.126.150.115:8080"+user.getPhoto());
		maps.put("img", url);
		String response = null;
		ResultData hr = new ResultData();
		try {
			//response = InterfaceUtils.HttpPost(app.getApiurl()+app.getAppid() + "/person/"+ user.getGuid()+"/face/imageUrl", InterfaceUtils.getMapToString(maps));
			response = InterfaceUtils.HttpPost(app.getApiurl()+app.getAppid() + "/person/"+ user.getGuid()+"/face", InterfaceUtils.getMapToString(maps));
			hr = GsonUtils.getObjectFromJson(response, ResultData.class);
			System.out.println("=====================================");
			System.out.println("执行结果：" + response);
			System.out.println("=====================================");
		}
		catch(Exception ex){
			System.out.println("=====================================");
			System.out.println("系统异常：" + ex.getMessage());
			System.out.println("=====================================");
			return null;
		}
		return hr;
	}	
	/**
	 * base64加密
	 * @param f
	 * @return
	 */
	private  String getImageBinary(File f){     
		BASE64Encoder encoder = new BASE64Encoder();   //加密
        BufferedImage bi;      
        try {      
            bi = ImageIO.read(f);      
            ByteArrayOutputStream baos = new ByteArrayOutputStream();      
            ImageIO.write(bi, "jpg", baos);      
            byte[] bytes = baos.toByteArray();      

            return com.thinkgem.jeesite.common.utils.Base64.encode(bytes);    
        } catch (IOException e) {      
            e.printStackTrace();      
        }      
        return null;      
    }
	/**
	 * 人员授权设备
	 * @param device
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public ResultData empDev(User user,String devGuids){
		UfaceToken token = getToken("1");
		App app = getApp("1");
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("appId", app.getAppid());
		maps.put("token", token.getToken());
		maps.put("guid", user.getGuid());
		maps.put("deviceKeys", devGuids);
		maps.put("passTimes", "");
		String response = null;
		ResultData hr = new ResultData();
		try {
			response = InterfaceUtils.HttpPost(app.getApiurl()+app.getAppid() + "/person/"+ user.getGuid()+"/devices", InterfaceUtils.getMapToString(maps));
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
			return null;
		}
		return hr;
	}
	/**
	 * 设备授权人员
	 * @param device
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public ResultData devEmp(Device device,String personGuids){
		UfaceToken token = getToken("1");
		Map<String, String> maps = new HashMap<String, String>();
		App app = getApp("1");
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
			return null;
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
		UfaceToken token = getToken("1");
		App app = getApp("1");
		Map<String, String> maps = new HashMap<String, String>();
		//String name = new String(user.getName().getBytes("ISO-8859-1"), "UTF-8"); 
		maps.put("appId", app.getAppid());
		maps.put("token", token.getToken());
		try {
			maps.put("name", URLEncoder.encode(user.getName(), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		/*maps.put("idNo", user.getIdCard());
		maps.put("phone", user.getPhone());
		maps.put("tag", user.getRemarks());
		maps.put("type", user.getUserType());*/
		String response = null;
		ResultData hr = new ResultData();
		System.out.println(InterfaceUtils.getMapToString(maps));
		try {
			response = InterfaceUtils.post(app.getApiurl()+app.getAppid() + "/person", InterfaceUtils.getMapToString(maps));
			hr = GsonUtils.getObjectFromJson(response, ResultData.class);
			System.out.println("=====================================");
			System.out.println("添加人员成功，执行结果：" + response);
			System.out.println("=====================================");
		}
		catch(Exception ex){
			System.out.println("=====================================");
			System.out.println("系统异常：" + ex.getMessage());
			System.out.println("=====================================");
			return null;
		}
		return hr;
	}
	/**
	 * 添加设备
	 * @param device
	 * @return
	 */
	public ResultData createDevice(Device device) {
		UfaceToken token = getToken("1");
		App app = getApp("1");
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
			return null;
		}
		return hr;
	}
	/**
	 * 更新token
	 * @return
	 */
	public UfaceToken getToken(String type) {
		long hh = 36000000; //10小时以上更新token
		UfaceToken token = new UfaceToken();
		token.setType(type);
		//先判断token是否需要更新
		List<UfaceToken> list = ufaceTokenService.findList(token);
		if(list.size()>0){
			token = list.get(0);
		}else {
			token = createAiotToken();
			token.setState("1");
			token.setType("2");
			ufaceTokenService.save(token);
		}
		if (token.getCreateDate().getTime()+hh > new Date().getTime()) {  //有效期内
			return token;
		}else {
			if (type.equals("1")) {
				token = createToken();
				token.setState("1");
				token.setType("1");
				ufaceTokenService.save(token);
			}else {
				token = createAiotToken();
				token.setState("1");
				token.setType("2");
				ufaceTokenService.save(token);
			}
			return token;
		}
	}
	/**
	 * 获取应用信息
	 * @return
	 */
	private App getApp(String type){
		App app = new App();
		app.setType(type);
		app = appService.findList(app).get(0);
		return app;
	}
	/**
	 * 获取到aiottoken
	 * @param app
	 * @return
	 */
	private UfaceToken createAiotToken() {
		App app =getApp("2");
		UfaceToken token = new UfaceToken();
		Map<String, String> maps = new HashMap<String, String>();
		String timestamp = String.valueOf(new Date().getTime());
		String sign = InterfaceUtils.MD5(app.getAppkey() + timestamp + app.getAppsecret());
		maps.put("accessKey", app.getAppkey());
		maps.put("unixTime", timestamp);
		maps.put("sign", sign);
		try {
			String response = InterfaceUtils.sendGet(app.getApiurl()+"/auth", InterfaceUtils.getMapToString(maps));
			System.out.println(response);
			ResultData hr = GsonUtils.getObjectFromJson(response, ResultData.class);
			resultDataService.save(hr);
			token.setToken(hr.getData().toString());
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
	/**
	 * 获取到token
	 * @param app
	 * @return
	 */
	private UfaceToken createToken() {
		App app =getApp("1");
		UfaceToken token = new UfaceToken();
		Map<String, String> maps = new HashMap<String, String>();
		String timestamp = Long.toString(System.currentTimeMillis());
		String sign = InterfaceUtils.MD5(app.getAppkey() + timestamp + app.getAppsecret());
		maps.put("appId", app.getAppkey());
		maps.put("appKey", app.getAppkey());
		maps.put("timestamp", timestamp);
		maps.put("sign", sign);
		try {
			String response = InterfaceUtils.HttpPost(app.getApiurl()+app.getAppid() + "/auth", InterfaceUtils.getMapToString(maps));
			System.out.println(response);
			ResultData hr = GsonUtils.getObjectFromJson(response, ResultData.class);
			resultDataService.save(hr);
			token.setToken(hr.getData().toString());
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