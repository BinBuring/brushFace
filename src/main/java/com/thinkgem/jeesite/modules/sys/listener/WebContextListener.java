package com.thinkgem.jeesite.modules.sys.listener;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;

import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

public class WebContextListener extends org.springframework.web.context.ContextLoaderListener {
	@Override
	public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
		if (!SystemService.printKeyLoadMessage()){
			return null;
		}
		return super.initWebApplicationContext(servletContext);
	}
	public void showTimer() {
	       TimerTask task = new TimerTask() {
	    	   int count = 0;
	          @Override
	           public void run() {
	              ++count;
	              System.out.println("时间=" +new Date() + " 执行了" + count + "次"); // 1次
	              UserUtils.findYQUser(new Date());
	          }
	       };

	       //设置执行时间
	       Calendar calendar =Calendar.getInstance();
	       int year = calendar.get(Calendar.YEAR);
	       int month = calendar.get(Calendar.MONTH);
	       int day =calendar.get(Calendar.DAY_OF_MONTH);//每天
	       //定制每天的21:09:00执行，
	       calendar.set(year, month, day, 16, 48, 00);
	       Date date = calendar.getTime();
	       Timer timer = new Timer();
	       System.out.println(date);
	       
	       //int period = 2 * 1000;
	       //每天的date时刻执行task，每隔2秒重复执行
	      // timer.schedule(task, date, period);
	       //每天的date时刻执行task, 仅执行一次
	       timer.schedule(task, date);
	    }
}
