/**  
 * All rights Reserved, Designed By www.trustepay.cn
 * @Title：DeviceServiceImpl.java   
 * @Package：com.thinkgem.jeesite.modules.uface.service.impl   
 * @Description：
 * @author：信逸科技     
 * @date：2018年8月26日   
 * @version  
 * @Copyright：2018 www.trustepay.cn Inc. All rights reserved. 
 * @History：
 */  
package com.thinkgem.jeesite.modules.uface.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.common.utils.GsonUtils;
import com.thinkgem.jeesite.common.utils.InterfaceUtils;
import com.thinkgem.jeesite.modules.uface.dto.UfaceResultData;
import com.thinkgem.jeesite.modules.uface.entity.UfaceToken;
import com.thinkgem.jeesite.modules.uface.service.DeviceService;
import com.thinkgem.jeesite.modules.uface.service.TokenService;

import lombok.extern.slf4j.Slf4j;

/**   
 * @ClassName：DeviceServiceImpl   
 * @Description：
 * @author：信逸科技 
 * @date：2018年8月26日
 *     
 * @Copyright：2018 www.trustepay.cn Inc. All rights reserved. 
 * 
 */
@Slf4j
@Service
public class DeviceServiceImpl implements DeviceService {

	@Value("${uface.api.url}")
	String apiUrl;
	
	@Value("${uface.api.id}")
	String appId;
	
	@Value("${uface.api.key}")
	String appKey;
	
	@Value("${uface.api.secret}")
	String appSecret;
	
	@Autowired
	TokenService tokenService;
	
	/**   
	 * <p>Title：create</p>   
	 * <p>Description：</p>   
	 * @param deviceKey
	 * @return   
	 * @see com.thinkgem.jeesite.modules.uface.service.DeviceService#create(java.lang.String)   
	 */
	@Override
	public String create(String deviceKey, String name, String tag) {
		// TODO Auto-generated method stub
		
		UfaceToken token = tokenService.getToken();
		
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("appId", appId);
		maps.put("token", token.getToken());
		maps.put("deviceKey", deviceKey);
		maps.put("name", "");
		maps.put("tag", "");
		
		String response = null;
		
		try {
			response = InterfaceUtils.HttpPost(apiUrl + "device", InterfaceUtils.getMapToString(maps));
			/*UfaceResultData hr = GsonUtils.getObjectFromJson(response, UfaceResultData.class);
			if("true".equals(hr.getSuccess())) {
				token.setToken(hr.getData());
			}*/
			System.out.println("=====================================");
			System.out.println("执行结果：" + response);
			System.out.println("=====================================");
		}
		catch(Exception ex){
			System.out.println("=====================================");
			System.out.println("系统异常：" + ex.getMessage());
			System.out.println("=====================================");
		}
		
		return response;
	}

}
