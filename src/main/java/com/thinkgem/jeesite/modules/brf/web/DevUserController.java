/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.brf.web;

import java.util.List;

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
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.brf.entity.DevUser;
import com.thinkgem.jeesite.modules.brf.service.DevUserService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 设备人员授权Controller
 * @author 张斌
 * @version 2018-08-26
 */
@Controller
@RequestMapping(value = "${adminPath}/brf/devUser")
public class DevUserController extends BaseController {

	@Autowired
	private DevUserService devUserService;
	@Autowired
	private SystemService systemrService;
	
	@ModelAttribute
	public DevUser get(@RequestParam(required=false) String id) {
		DevUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = devUserService.get(id);
		}
		if (entity == null){
			entity = new DevUser();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(DevUser devUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DevUser> page = devUserService.findPage(new Page<DevUser>(request, response), devUser); 
		model.addAttribute("page", page);
		return "modules/brf/devUserList";
	}

	@RequestMapping(value = "form")
	public String form(DevUser devUser, Model model) {
		model.addAttribute("devUser", devUser);
		return "modules/brf/devUserForm";
	}

	@RequestMapping(value = "save")
	public String save(HttpServletRequest request,HttpServletResponse response,DevUser devUser, Model model, RedirectAttributes redirectAttributes) {
		String guids = request.getParameter("guids");
		if (guids.charAt(0) == ',') {
			guids = guids.substring(1);
		}
		String[] guid = guids.split(",");
		for (int i = 0; i < guid.length; i++) {
			User user = UserUtils.get(guid[i]);
			devUser.setUser(user);
			List<DevUser> list = devUserService.findList(devUser);
			if (list.size() <= 0) {
				devUser.setIsNewRecord(true);
				devUser.setId(IdGen.uuid());
				if (!beanValidator(model, devUser)){
					return form(devUser, model);
				}
				devUserService.save(devUser);
			}
		}
		addMessage(redirectAttributes, "保存授权成功");
		return "redirect:"+Global.getAdminPath()+"/brf/device/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(DevUser devUser, RedirectAttributes redirectAttributes) {
		devUserService.delete(devUser);
		addMessage(redirectAttributes, "删除授权成功");
		return "redirect:"+Global.getAdminPath()+"/brf/device/?repage";
	}

}