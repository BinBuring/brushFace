/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.GsonUtils;
import com.thinkgem.jeesite.common.utils.InterfaceUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.brf.entity.Device;
import com.thinkgem.jeesite.modules.brf.service.DeviceService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.uuface.entity.ResultData;
import com.thinkgem.jeesite.modules.uuface.service.InterfaceService;

/**
 * 员工Controller
 * @author ThinkGem
 * @version 2013-8-29
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/emp")
public class EmpController extends BaseController {

	@Autowired
	private SystemService systemService;
	@Autowired 
	private InterfaceService interfaceService;
	@Autowired
	private DeviceService deviceService;
	
	@ModelAttribute
	public User get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return systemService.getUser(id);
		}else{
			return new User();
		}
	}

	@RequestMapping(value = {"empIndex"})
	public String empIndex(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/sys/empIndex";
	}
	
	@RequestMapping(value = {"list", ""})
	public String emp(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		user.setLoginFlag("0");
		Page<User> page = systemService.findUser(new Page<User>(request, response), user);
        model.addAttribute("page", page);
		return "modules/sys/empList";
	}
	@RequestMapping(value = "empform")
	public String empform(User user, Model model) {
		if (user.getCompany()==null || user.getCompany().getId()==null){
			user.setCompany(UserUtils.getUser().getCompany());
		}
		if (user.getOffice()==null || user.getOffice().getId()==null){
			user.setOffice(UserUtils.getUser().getOffice());
		}
		model.addAttribute("user", user);
		model.addAttribute("allRoles", systemService.findAllRole());
		return "modules/sys/empForm";
	}
	/**
	 * 人员授权设备
	 * @param device
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "authorization")
	public String authorization(User user, Model model,HttpServletRequest request,HttpServletResponse response) {
		model.addAttribute("user", user);
		Page<Device> page = deviceService.findPage(new Page<Device>(request, response), new Device()); 
		model.addAttribute("page", page);
		return "modules/brf/userDevList";
	}
	@ResponseBody
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = {"listData"})
	public Page<User> listData(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<User> page = systemService.findUser(new Page<User>(request, response), user);
		return page;
	}

	@SuppressWarnings("unused")
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "empsave")
	public String empsave(User user, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/emp/list?repage";
		}
		// 修正引用赋值问题，不知道为何，Company和Office引用的一个实例地址，修改了一个，另外一个跟着修改。
		user.setCompany(new Office(request.getParameter("company.id")));
		user.setOffice(new Office(request.getParameter("office.id")));
		if (!beanValidator(model, user)){
			return empform(user, model);
		}
		// 角色数据有效性验证，过滤不在授权内的角色
		List<Role> roleList = Lists.newArrayList();
		List<String> roleIdList = user.getRoleIdList();
		roleIdList.add("0813e54621b24f9f9e56df309f424088");  //设置默认角色为员工
		Role role = UserUtils.getRole("0813e54621b24f9f9e56df309f424088");
		roleList.add(role);
		for (Role r : systemService.findAllRole()){
			if (roleIdList.contains(r.getId())){
				roleList.add(r);
			}
		}
		//先到云端查询是否已存在这个员工，有则修改，无则添加
		ResultData select = interfaceService.selectEmp(user);
		if (select != null && select.getSuccess().equals("true")) {
			//在云端上修改信息
			ResultData update = interfaceService.updateEmp(user);
			if (update.getSuccess().equals("true")) {
				ResultData photo = new ResultData();
				//从数据库找到员工数据
				User bUser = systemService.findUser(user).get(0);
				if (update.getSuccess().equals("true")&&StringUtils.isNotEmpty(user.getPhoto())&& !bUser.getPhoto().equals(user.getPhoto())) {//照片是否存在,是否更新，存在的话删除重新上传
					interfaceService.deletePhoto(user);
					photo = interfaceService.empimageUrl(user,request);
					user.setAuthPhone("1");
				}
				Map<String, String> dataMap = InterfaceUtils.getStringToMap(photo.getData().toString());
				user.setPhotoId(dataMap.get("guid"));
				systemService.saveUser(user);
				addMessage(redirectAttributes, "保存员工'" + user.getName() + "'成功");
			}else {
				addMessage(redirectAttributes, "保存员工'" + user.getName() + "'失败");
			}
		}else {
			user.setLoginFlag("0");
			user.setStatus("0");
			user.setAuthPhone("1");
			user.setRoleList(roleList);
			//在云端上保存员工
			ResultData data = interfaceService.createEmp(user);
			// 保存员工信息
			if(data.getSuccess().equals("true")){
				String json = GsonUtils.getJsonFromObject(data.getData());
				Map<String, String> map = InterfaceUtils.getStringToMap(json);
				user.setIsNewRecord(true);
				user.setId(map.get("guid"));
				//通过照片url判断是否需要上传照片
				if (data.getSuccess().equals("true")&&StringUtils.isNotEmpty(user.getPhoto())) {
					ResultData photo = interfaceService.empimageUrl(user,request);
					Map<String, String> dataMap = InterfaceUtils.getStringToMap(photo.getData().toString());
					user.setPhotoId(dataMap.get("guid"));
				}
				systemService.saveUser(user);
				addMessage(redirectAttributes, "保存员工'" + user.getName() + "'成功");
			}else {
				addMessage(redirectAttributes, "保存员工'" + user.getName() + "'失败");
			}
		}
		return "redirect:" + adminPath + "/sys/emp/list?repage";
	}
	
	@RequestMapping(value = "audit")
	public String audit(User user,RedirectAttributes redirectAttributes){
		if (user.getPhoto()==null) {
			addMessage(redirectAttributes, "审核员工" + user.getName() + "'失败,该员工头像不符合");
			return "redirect:" + adminPath + "/sys/emp/list?repage";
		}
		if (user.getAuthPhone().equals("1")) {
			addMessage(redirectAttributes, "审核员工" + user.getName() + "'失败，该员工未授权");
			return "redirect:" + adminPath + "/sys/emp/list?repage";
		}
		//根据有效期判断用户状态
		long now = new Date().getTime();
		if (user.getStartDate().getTime() < now && user.getEndDate().getTime() > now) {
			user.setStatus("1");
		} else if (user.getStartDate().getTime() > now) {
			user.setStatus("2");
		}else {
			user.setStatus("3");
		}
		addMessage(redirectAttributes, "审核员工" + user.getName() + "'成功");
		return "redirect:" + adminPath + "/sys/emp/list?repage";
	}
	
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "empdelete")
	public String delete(User user, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/emp/list?repage";
		}
		if (UserUtils.getUser().getId().equals(user.getId())){
			addMessage(redirectAttributes, "删除员工失败, 不允许删除当前员工");
		}else if (User.isAdmin(user.getId())){
			addMessage(redirectAttributes, "删除员工失败, 不允许删除超级管理员员工");
		}else{
			ResultData resultData = interfaceService.deleteEmp(user);
			if (resultData.getSuccess().equals("true")) {
				systemService.deleteUser(user);
				addMessage(redirectAttributes, "删除员工成功");
			}
		}
		return "redirect:" + adminPath + "/sys/emp/list?repage";
	}
	
	/**
	 * 导出员工数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
    @RequestMapping(value = "empexport", method=RequestMethod.POST)
    public String exportFile(User user, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "员工数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<User> page = systemService.findUser(new Page<User>(request, response, -1), user);
    		new ExportExcel("员工数据", User.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出员工失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/emp/list?repage";
    }

	/**
	 * 导入员工数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:edit")
    @RequestMapping(value = "empimport", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/emp/list?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<User> list = ei.getDataList(User.class);
			for (User user : list){
				try{
					if (true){
						user.setPassword(SystemService.entryptPassword("123456"));
						BeanValidators.validateWithException(validator, user);
						systemService.saveUser(user);
						successNum++;
					}else{
						failureMsg.append("<br/>登录名 "+user.getLoginName()+" 已存在; ");
						failureNum++;
					}
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>登录名 "+user.getLoginName()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>登录名 "+user.getLoginName()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条员工，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条员工"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入员工失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/emp/list?repage";
    }
	

	/**
	 * 员工信息显示及保存
	 * @param user
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "info")
	public String info(User user, HttpServletResponse response, Model model) {
		User currentUser = UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getName())){
			if(Global.isDemoMode()){
				model.addAttribute("message", "演示模式，不允许操作！");
				return "modules/sys/userInfo";
			}
			currentUser.setEmail(user.getEmail());
			currentUser.setPhone(user.getPhone());
			currentUser.setMobile(user.getMobile());
			currentUser.setRemarks(user.getRemarks());
			currentUser.setPhoto(user.getPhoto());
			systemService.updateUserInfo(currentUser);
			model.addAttribute("message", "保存员工信息成功");
		}
		model.addAttribute("user", currentUser);
		//修改Global 没有私有构造函数，实现懒汉式单例模式.在第一次调用的时候实例化自己！
		model.addAttribute("Global", Global.getInstance());
		return "modules/sys/userInfo";
	}

	/**
	 * 返回员工信息
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "infoData")
	public User infoData() {
		return UserUtils.getUser();
	}
	
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String officeId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<User> list = systemService.findUserByOfficeId(officeId);
		for (int i=0; i<list.size(); i++){
			User e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", "u_"+e.getId());
			map.put("pId", officeId);
			map.put("name", StringUtils.replace(e.getName(), " ", ""));
			mapList.add(map);
		}
		return mapList;
	}
    
}
