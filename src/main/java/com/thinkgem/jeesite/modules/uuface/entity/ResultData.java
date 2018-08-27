/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uuface.entity;

import org.hibernate.validator.constraints.Length;

import com.google.gson.annotations.Expose;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 接口返回信息Entity
 * @author zb
 * @version 2018-08-27
 */
public class ResultData extends DataEntity<ResultData> {
	
	private static final long serialVersionUID = 1L;
	@Expose
	private String result;		// 处理结果,(1:成功,0:失败)
	@Expose
	private String code;		// 返回码
	@Expose
	private String msg;		// 返回信息
	@Expose
	private Object data;		// 返回数据
	@Expose
	private String success;		// 成功失败
	
	public ResultData() {
		super();
	}

	public ResultData(String id){
		super(id);
	}

	@Length(min=0, max=255, message="处理结果,(1:成功,0:失败)长度必须介于 0 和 255 之间")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	@Length(min=0, max=255, message="返回码长度必须介于 0 和 255 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=255, message="返回信息长度必须介于 0 和 255 之间")
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Length(min=0, max=1000, message="返回数据长度必须介于 0 和 1000 之间")
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	@Length(min=0, max=255, message="成功失败长度必须介于 0 和 255 之间")
	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}
	
}