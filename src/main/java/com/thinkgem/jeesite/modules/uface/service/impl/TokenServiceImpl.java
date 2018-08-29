/**  
 * All rights Reserved, Designed By www.trustepay.cn
 * @Title：TokenServiceImpl.java   
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
import com.thinkgem.jeesite.modules.uface.dao.TokenDao;
import com.thinkgem.jeesite.modules.uface.dto.UfaceResultData;
import com.thinkgem.jeesite.modules.uface.entity.UfaceToken;
import com.thinkgem.jeesite.modules.uface.service.TokenService;

import lombok.extern.slf4j.Slf4j;

/**   
 * @ClassName：TokenServiceImpl   
 * @Description：
 * @author：信逸科技 
 * @date：2018年8月26日
 *     
 * @Copyright：2018 www.trustepay.cn Inc. All rights reserved. 
 * 
 */
@Slf4j
@Service
public class TokenServiceImpl implements TokenService{

	@Value("${uface.api.url}")
	String apiUrl;
	
	@Value("${uface.api.id}")
	String appId;
	
	@Value("${uface.api.key}")
	String appKey;
	
	@Value("${uface.api.secret}")
	String appSecret;
	
	//@Autowired 
	//TokenDao tokenDao;
	
	/**   
	 * <p>Title：selectByState</p>   
	 * <p>Description：</p>   
	 * @return   
	 * @see com.thinkgem.jeesite.modules.uface.service.TokenService#selectByState()   
	 */
	@Override
	public UfaceToken getToken() {
		// TODO Auto-generated method stub
		
		UfaceToken token = new UfaceToken();//tokenDao.selectByState();
		token.setToken("ecd679a5057c18b34c1603cf5ff38ef8205f30ff9cb6a460c48ca4e730ad235c");
		//if(token == null) {
		//	token = createToken();
		//	tokenDao.insert(token.getToken());
		//}
		
		return token;
	}

	private UfaceToken createToken() {
		UfaceToken token = new UfaceToken();
		
		String timestamp = Long.toString(System.currentTimeMillis());
		String sign = InterfaceUtils.MD5(appKey + timestamp + appSecret);
		
		
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("appId", appId);
		maps.put("appKey", appKey);
		maps.put("timestamp", timestamp);
		maps.put("sign", sign);
		
		try {
			String response = InterfaceUtils.HttpPost(apiUrl + "auth", InterfaceUtils.getMapToString(maps));
			UfaceResultData hr = GsonUtils.getObjectFromJson(response, UfaceResultData.class);
			if("true".equals(hr.getSuccess())) {
				token.setToken(hr.getData());
			}
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
