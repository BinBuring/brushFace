/**  
 * All rights Reserved, Designed By www.trustepay.cn
 * @Title：UfaceResultData.java   
 * @Package：com.thinkgem.jeesite.modules.uface.dto   
 * @Description：
 * @author：信逸科技     
 * @date：2018年8月26日   
 * @version  
 * @Copyright：2018 www.trustepay.cn Inc. All rights reserved. 
 * @History：
 */  
package com.thinkgem.jeesite.modules.uface.dto;

import lombok.Data;

/**   
 * @ClassName：UfaceResultData   
 * @Description：
 * @author：信逸科技 
 * @date：2018年8月26日
 *     
 * @Copyright：2018 www.trustepay.cn Inc. All rights reserved. 
 * 
 */
@Data
public class UfaceResultData {
	private Integer result;
	private String code;
	private String msg;
	private String data;
	private String success;
	
}
