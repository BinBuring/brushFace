/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import com.thinkgem.jeesite.modules.sys.entity.User;
 
/**
 * 在 TimerManager 这个类里面，大家一定要注意 时间点的问题。如果你设定在凌晨2点执行任务。但你是在2点以后
 *发布的程序或是重启过服务，那这样的情况下，任务会立即执行，而不是等到第二天的凌晨2点执行。为了，避免这种情况
 *发生，只能判断一下，如果发布或重启服务的时间晚于定时执行任务的时间，就在此基础上加一天。
 * @author wls
 *
 */
public class NFDFlightDataTimerTask extends TimerTask {
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public void run() {
        try {
             //在这里写你要执行的内容
        	//查出所有过期的员工，变更状态为3，已过期
        	List<User> list = UserUtils.findYQUser(new Date());
        	for (int i = 0; i < list.size(); i++) {
				User user = list.get(i);
				user.setStatus("3");
				UserUtils.updateUser(user);
			}
        	//查出所有状态为2未激活并且可以激活的员工，变更状态为1正常
        	list = UserUtils.findJHUser(new Date());
        	for (int i = 0; i < list.size(); i++) {
				User user = list.get(i);
				user.setStatus("1");
				UserUtils.updateUser(user);
			}
            System.out.println("执行当前时间"+formatter.format(Calendar.getInstance().getTime()));
        } catch (Exception e) {
            System.out.println("-------------解析信息发生异常--------------");
        }
    }
     
}