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
import com.thinkgem.jeesite.modules.brf.entity.Device;
import com.thinkgem.jeesite.modules.brf.service.DevUserService;
import com.thinkgem.jeesite.modules.brf.service.DeviceService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.uuface.service.InterfaceService;

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
	@Autowired
	private InterfaceService interfaceService;
	@Autowired 
	private DeviceService deviceService;
	
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
	/**
	 * 一个用户，多个设备
	 * @param request
	 * @param response
	 * @param devUser
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "saveDev")
	public String saveDev(HttpServletRequest request,HttpServletResponse response,DevUser devUser, Model model, RedirectAttributes redirectAttributes) {
		String devids = request.getParameter("devids");
		String devKeys = new String();
		String userId = request.getParameter("userId");
		if (devids.charAt(0) == ',') {
			devids = devids.substring(1);
		}
		String[] devid = devids.split(",");
		User user = UserUtils.get(userId);
		for (int i = 0; i < devid.length; i++) {
			devKeys += deviceService.get(devid[i]).getSeq()+",";
		}
		devKeys = devKeys.substring(0, devKeys.length()-1);
		interfaceService.empDev(user, devKeys);
		
		for (int i = 0; i < devid.length; i++) {
			Device dev = deviceService.get(devid[i]);
			devUser.setUser(user);
			devUser.setDevId(dev.getId());
			devUser.setIsNewRecord(true);
			devUser.setId(IdGen.uuid());
			List<DevUser> list = devUserService.findList(devUser);  //查询表中数据，看是否插入
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
		//修改员工授权状态
		user.setAuthPhone("2");
		systemrService.saveUser(user);
		return "redirect:"+Global.getAdminPath()+"/sys/emp/?repage";
	}
	/**
	 * 一个设备，多个用户
	 * @param request
	 * @param response
	 * @param devUser
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save")
	public String save(HttpServletRequest request,HttpServletResponse response,DevUser devUser, Model model, RedirectAttributes redirectAttributes) {
		String guids = request.getParameter("guids");
		if (guids.charAt(0) == ',') {
			guids = guids.substring(1);
		}
		String[] guid = guids.split(",");
		Device device = deviceService.get(devUser.getDevId());
		interfaceService.devEmp(device, guids);
		
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