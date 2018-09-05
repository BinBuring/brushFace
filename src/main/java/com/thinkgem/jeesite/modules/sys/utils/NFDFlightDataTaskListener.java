/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
 
 
public class NFDFlightDataTaskListener implements  ServletContextListener {
 
    public void contextInitialized(ServletContextEvent sce) {
         new TimerManager();
    }
 
    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub
         
    }
 
}