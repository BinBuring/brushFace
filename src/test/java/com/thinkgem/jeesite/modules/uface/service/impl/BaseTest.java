package com.thinkgem.jeesite.modules.uface.service.impl;


import com.alibaba.druid.pool.DruidDataSource;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.naming.NamingException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:*.xml"})
public abstract class BaseTest {
    @BeforeClass
    public static void loadJndiDataSource() throws NamingException {

        final SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
        final DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://192.168.1.122:3306/elikedb?characterEncoding=utf-8");
        ds.setUsername("root");
        ds.setPassword("123456");

        ds.setInitialSize(1);
        ds.setMaxActive(20);
        ds.setMaxWait(60000);
        ds.setMinEvictableIdleTimeMillis(300000);

        builder.bind("java:comp/env/jdbc/PROFILEDS", ds);
        builder.activate();
    }
}
