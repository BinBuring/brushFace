/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uuface.entity;

import com.google.gson.annotations.Expose;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 接口返回信息Entity
 * @author zb
 * @version 2018-08-27
 */
public class ResultFaceData extends DataEntity<ResultFaceData> {
	
	private static final long serialVersionUID = 1L;
	@Expose
	private String faces;		//脸部信息
	@Expose
	private String image;		//图片大小 
	@Expose
	private String createTime;		//脸部信息
	@Expose
	private String ufaceId;		//图片大小 
	@Expose
	private String imageId;		//脸部信息
	public String getFaces() {
		return faces;
	}
	public void setFaces(String faces) {
		this.faces = faces;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUfaceId() {
		return ufaceId;
	}
	public void setUfaceId(String ufaceId) {
		this.ufaceId = ufaceId;
	}
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	
}