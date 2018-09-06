package com.thinkgem.jeesite.modules.sys.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "${adminPath}/sys/upload")
public class upload {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@ResponseBody
	@RequestMapping(value = "upload",method = RequestMethod.POST)
	public String form(MultipartFile file,String type,Long user,HttpServletRequest request) {
		String taskType = "F";
        String featureType = type;
        Long userOpt = user;
        
        if (file.isEmpty()) {
            return "上传文件失败,请检查上传的文件";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        LOG.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        LOG.info("上传的后缀名为：" + suffixName);

        // 文件上传后的路径
        String path = request.getSession().getServletContext().getRealPath("/");
        String filePath = path + "upload/img/";

        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            
            Long res = 0L;

            if (res != 0) {
                return "任务失败！";
            } else {
            	LOG.error("TaskId插入失败");
                return "TaskId插入失败,请联系管理员！";
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
            LOG.error(e.toString(),e);
            return "上传文件失败,请检查上传的文件,IllegalStateException";
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error(e.toString(),e);
            return "上传文件失败,请检查上传的文件,IOException";
        }
	}
	   /* public String upload(@RequestParam("file") ) {

	        
	    }*/
}
