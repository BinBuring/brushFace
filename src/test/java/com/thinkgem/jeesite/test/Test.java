package com.thinkgem.jeesite.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.h2.util.New;

public class Test {

	public static void main(String[] args) {
		//测试文件
		Date date = new Date();
		long ten = 36000000;
		System.out.println(date.getTime()+ten);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取String类型的时间
        String createdate = sdf.format(date.getTime()+ten);
        System.out.println(createdate);
	}
}
