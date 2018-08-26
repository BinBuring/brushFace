/**  
 * All rights Reserved, Designed By www.trustepay.cn
 * @Title：TokenDao.java   
 * @Package：com.thinkgem.jeesite.modules.uface.dao   
 * @Description：
 * @author：信逸科技     
 * @date：2018年8月26日   
 * @version  
 * @Copyright：2018 www.trustepay.cn Inc. All rights reserved. 
 * @History：
 */  
package com.thinkgem.jeesite.modules.uface.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.thinkgem.jeesite.modules.uface.entity.UfaceToken;

/**   
 * @ClassName：TokenDao   
 * @Description：
 * @author：信逸科技 
 * @date：2018年8月26日
 *     
 * @Copyright：2018 www.trustepay.cn Inc. All rights reserved. 
 * 
 */
public interface TokenDao {
	
	@Select("SELECT token FROM `sys_uface_token` WHERE state = 1 limit 1")
	public UfaceToken selectByState();
	
	@Insert("INSERT INTO sys_uface_token(token, state) VALUES(#{token}, '1')")
	public Integer insert(@Param("token") String token);
	
	@Delete("DELETE FROM sys_uface_token")
	public void delete();

}
