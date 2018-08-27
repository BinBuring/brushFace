/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.brf.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.brf.entity.Device;
import com.thinkgem.jeesite.modules.brf.service.DeviceService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

/**
 * 设备管理Controller
 * @author 张斌
 * @version 2018-08-26
 */
@Controller
@RequestMapping(value = "${adminPath}/brf/device")
public class DeviceController extends BaseController {

	@Autowired
	private DeviceService deviceService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private com.thinkgem.jeesite.modules.uface.service.DeviceService deviceService2;
	
	@ModelAttribute
	public Device get(@RequestParam(required=false) String id) {
		Device entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = deviceService.get(id);
		}
		if (entity == null){
			entity = new Device();
		}
		return entity;
	}
	
	@RequiresPermissions("brf:device:view")
	@RequestMapping(value = {"list", ""})
	public String list(ModelMap modelMap,Device device, HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println(modelMap.get("message"));
		Page<Device> page = deviceService.findPage(new Page<Device>(request, response), device); 
		model.addAttribute("page", page);
		return "modules/brf/deviceList";
	}

	@RequiresPermissions("brf:device:view")
	@RequestMapping(value = "form")
	public String form(Device device, Model model) {
		model.addAttribute("device", device);
		return "modules/brf/deviceForm";
	}
	/**
	 * 授权
	 * @param device
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "authorization")
	public String authorization(Device device, Model model,HttpServletRequest request,HttpServletResponse response) {
		model.addAttribute("device", device);
		User user = new User();
		user.setLoginFlag("0");
		Page<User> page = systemService.findUser(new Page<User>(request, response), user);
        model.addAttribute("page", page);
		return "modules/brf/empList";
	}
	
	@RequiresPermissions("brf:device:edit")
	@RequestMapping(value = "save")
	public String save(Device device, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, device)){
			return form(device, model);
		}
		String jsonString = deviceService2.create(device.getSeq(), device.getName(), "");
		System.out.println(jsonString);
		//deviceService.save(device);
		addMessage(redirectAttributes, "保存设备成功");
		return "redirect:"+Global.getAdminPath()+"/brf/device/?repage";
	}
	
	@RequiresPermissions("brf:device:edit")
	@RequestMapping(value = "delete")
	public String delete(Device device, RedirectAttributes redirectAttributes) {
		deviceService.delete(device);
		addMessage(redirectAttributes, "删除设备成功");
		return "redirect:"+Global.getAdminPath()+"/brf/device/?repage";
	}

}