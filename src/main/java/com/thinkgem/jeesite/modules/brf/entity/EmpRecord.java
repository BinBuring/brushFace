/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.brf.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 员工记录Entity
 * @author 张斌
 * @version 2018-08-28
 */
public class EmpRecord extends DataEntity<EmpRecord> {
	
	private static final long serialVersionUID = 1L;
	private User emp;		// 员工
	private String devKey;		// 设备序列号
	private Device dev;
	private String photourl;		// 员工识别图片
	private Date showtime;		// 识别时间
	private String data;		// 人脸附属信息若data数据json解析不正确，可使用data.replace(
	private String type;		// 识别出的人员类型，0：时间段内，1：时间段外，2：陌生人
	private String recmode;		// 识别模式，1:刷脸，2:刷卡，3:双重认证， 4:人证比对
	private String idcardinfo;		// 人证比对详细
	private Date beginShowtime;		// 开始 识别时间
	private Date endShowtime;		// 结束 识别时间
	//查询字段
	private String empName;
	private String empNo;
	private String offName;
	
	
	
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getOffName() {
		return offName;
	}

	public void setOffName(String offName) {
		this.offName = offName;
	}

	public Device getDev() {
		return dev;
	}

	public void setDev(Device dev) {
		this.dev = dev;
	}

	public EmpRecord() {
		super();
	}

	public EmpRecord(String id){
		super(id);
	}

	public User getEmp() {
		return emp;
	}

	public void setEmp(User emp) {
		this.emp = emp;
	}
	

	public String getDevKey() {
		return devKey;
	}

	public void setDevKey(String devKey) {
		this.devKey = devKey;
	}

	@Length(min=0, max=255, message="员工识别图片长度必须介于 0 和 255 之间")
	public String getPhotourl() {
		return photourl;
	}

	public void setPhotourl(String photourl) {
		this.photourl = photourl;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getShowtime() {
		return showtime;
	}

	public void setShowtime(Date showtime) {
		this.showtime = showtime;
	}
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	@Length(min=0, max=1, message="识别出的人员类型，0：时间段内，1：时间段外，2：陌生人长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=1, message="识别模式，1:刷脸，2:刷卡，3:双重认证， 4:人证比对长度必须介于 0 和 1 之间")
	public String getRecmode() {
		return recmode;
	}

	public void setRecmode(String recmode) {
		this.recmode = recmode;
	}
	
	@Length(min=0, max=2555, message="人证比对详细长度必须介于 0 和 2555 之间")
	public String getIdcardinfo() {
		return idcardinfo;
	}

	public void setIdcardinfo(String idcardinfo) {
		this.idcardinfo = idcardinfo;
	}
	
	public Date getBeginShowtime() {
		return beginShowtime;
	}

	public void setBeginShowtime(Date beginShowtime) {
		this.beginShowtime = beginShowtime;
	}
	
	public Date getEndShowtime() {
		return endShowtime;
	}

	public void setEndShowtime(Date endShowtime) {
		this.endShowtime = endShowtime;
	}
		
}