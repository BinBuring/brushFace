/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.brf.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.GsonUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.brf.entity.EmpRecord;
import com.thinkgem.jeesite.modules.brf.service.EmpRecordService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 员工记录Controller
 * @author 张斌
 * @version 2018-08-28
 */
@Controller
@RequestMapping(value = "${adminPath}/brf/empRecord")
public class EmpRecordController extends BaseController {

	@Autowired
	private EmpRecordService empRecordService;
	
	@ModelAttribute
	public EmpRecord get(@RequestParam(required=false) String id) {
		EmpRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = empRecordService.get(id);
		}
		if (entity == null){
			entity = new EmpRecord();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(EmpRecord empRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EmpRecord> page = empRecordService.findPage(new Page<EmpRecord>(request, response), empRecord); 
		model.addAttribute("page", page);
		return "modules/brf/empRecordList";
	}


	@RequestMapping(value = "save")
	public String save(EmpRecord empRecord, Model model, HttpServletRequest request,RedirectAttributes redirectAttributes) {
		//接收参数
		String personGuid = request.getParameter("personGuid");
		String showTime = request.getParameter("showTime");
		String photoUrl = request.getParameter("photoUrl");
		String data = request.getParameter("data");
		String type = request.getParameter("type");
		String recMode = request.getParameter("recMode");
		String idCardInfo = request.getParameter("idCardInfo");
		String devKey = request.getParameter("deviceKey");
		//设置实体属性
		empRecord.setEmp(UserUtils.get(personGuid));
		empRecord.setPhotourl(photoUrl);
		empRecord.setData(data);
		empRecord.setType(type);
		empRecord.setIdcardinfo(idCardInfo);
		empRecord.setRecmode(recMode);
		empRecord.setDevKey(devKey);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date showDate = new Date();
        try {
        	showDate = sdf.parse(showTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		empRecord.setShowtime(showDate);
		if (empRecordService.getByDate(showDate)) {
			empRecordService.save(empRecord);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("result", "1");
		map.put("success", "true");
		return GsonUtils.getJsonFromObject(map);
	}
	
	@RequestMapping(value = "delete")
	public String delete(EmpRecord empRecord, RedirectAttributes redirectAttributes) {
		empRecordService.delete(empRecord);
		addMessage(redirectAttributes, "删除记录成功");
		return "redirect:"+Global.getAdminPath()+"/brf/empRecord/?repage";
	}

}