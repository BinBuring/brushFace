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
import com.thinkgem.jeesite.modules.uuface.entity.App;
import com.thinkgem.jeesite.modules.uuface.service.AppService;

/**
 * 接口app数据Controller
 * @author zb
 * @version 2018-08-27
 */
@Controller
@RequestMapping(value = "${adminPath}/uuface/app")
public class AppController extends BaseController {

	@Autowired
	private AppService appService;
	
	@ModelAttribute
	public App get(@RequestParam(required=false) String id) {
		App entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = appService.get(id);
		}
		if (entity == null){
			entity = new App();
		}
		return entity;
	}
	
	@RequiresPermissions("uuface:app:view")
	@RequestMapping(value = {"list", ""})
	public String list(App app, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<App> page = appService.findPage(new Page<App>(request, response), app); 
		model.addAttribute("page", page);
		return "modules/uuface/appList";
	}

	@RequiresPermissions("uuface:app:view")
	@RequestMapping(value = "form")
	public String form(App app, Model model) {
		model.addAttribute("app", app);
		return "modules/uuface/appForm";
	}

	@RequiresPermissions("uuface:app:edit")
	@RequestMapping(value = "save")
	public String save(App app, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, app)){
			return form(app, model);
		}
		appService.save(app);
		addMessage(redirectAttributes, "保存app成功");
		return "redirect:"+Global.getAdminPath()+"/uuface/app/?repage";
	}
	
	@RequiresPermissions("uuface:app:edit")
	@RequestMapping(value = "delete")
	public String delete(App app, RedirectAttributes redirectAttributes) {
		appService.delete(app);
		addMessage(redirectAttributes, "删除app成功");
		return "redirect:"+Global.getAdminPath()+"/uuface/app/?repage";
	}

}