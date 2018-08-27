package com.thinkgem.jeesite.test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.h2.util.New;

import sun.misc.BASE64Encoder;

public class Test {
	public static  String getImageBinary(File f){     
		BASE64Encoder encoder = new BASE64Encoder();   //加密
		BufferedImage bi;      
		try {      
			bi = ImageIO.read(f);      
			ByteArrayOutputStream baos = new ByteArrayOutputStream();      
			ImageIO.write(bi, "jpg", baos);      
			byte[] bytes = baos.toByteArray();      
			
			return encoder.encodeBuffer(bytes).trim();      
		} catch (IOException e) {      
			e.printStackTrace();      
		}      
		return null;      
	}

	public static void main(String[] args) {
		File f = new File("C:\\ruanjian\\tomcat7\\webapps\\brushface\\userfiles\\1\\images\\photo\\2018\\08\\timg.jpg");
		System.out.println(getImageBinary(f));
		
	}
}
