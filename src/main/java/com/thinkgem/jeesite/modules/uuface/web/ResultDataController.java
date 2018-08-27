/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uuface.web;

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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.uuface.entity.ResultData;
import com.thinkgem.jeesite.modules.uuface.service.ResultDataService;

/**
 * 接口返回信息Controller
 * @author zb
 * @version 2018-08-27
 */
@Controller
@RequestMapping(value = "${adminPath}/uuface/resultData")
public class ResultDataController extends BaseController {

	@Autowired
	private ResultDataService resultDataService;
	
	@ModelAttribute
	public ResultData get(@RequestParam(required=false) String id) {
		ResultData entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = resultDataService.get(id);
		}
		if (entity == null){
			entity = new ResultData();
		}
		return entity;
	}
	
	@RequiresPermissions("uuface:resultData:view")
	@RequestMapping(value = {"list", ""})
	public String list(ResultData resultData, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ResultData> page = resultDataService.findPage(new Page<ResultData>(request, response), resultData); 
		model.addAttribute("page", page);
		return "modules/uuface/resultDataList";
	}

	@RequiresPermissions("uuface:resultData:view")
	@RequestMapping(value = "form")
	public String form(ResultData resultData, Model model) {
		model.addAttribute("resultData", resultData);
		return "modules/uuface/resultDataForm";
	}

	@RequiresPermissions("uuface:resultData:edit")
	@RequestMapping(value = "save")
	public String save(ResultData resultData, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, resultData)){
			return form(resultData, model);
		}
		resultDataService.save(resultData);
		addMessage(redirectAttributes, "保存信息成功");
		return "redirect:"+Global.getAdminPath()+"/uuface/resultData/?repage";
	}
	
	@RequiresPermissions("uuface:resultData:edit")
	@RequestMapping(value = "delete")
	public String delete(ResultData resultData, RedirectAttributes redirectAttributes) {
		resultDataService.delete(resultData);
		addMessage(redirectAttributes, "删除信息成功");
		return "redirect:"+Global.getAdminPath()+"/uuface/resultData/?repage";
	}

}