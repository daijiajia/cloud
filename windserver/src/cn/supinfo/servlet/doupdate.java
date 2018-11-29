package cn.supinfo.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
@WebServlet(urlPatterns="/doupdate")
//@MultipartConfig
public class doupdate extends HttpServlet{
	private static final long serialVersionUID = 1L;    
         public void doPost(HttpServletRequest request, HttpServletResponse response)  
	                            throws ServletException, IOException{  	                  
	                   // 创建文件上传对象  
	                   SmartUpload smartUpload = new SmartUpload();  	          
	                   // 创建文件上传对象 
	                   // 初始化文件上传对象  
	                   smartUpload.initialize(this.getServletConfig(),request, response);  
	                   // 设置单个文件最大的字节数 	          
	                  smartUpload.setMaxFileSize(1000*100*1000);  
	                   // 设置总文件的最大字节数  
	                  smartUpload.setTotalMaxFileSize(1000*1000*100);  
	                  // 设置允许的文件的扩展名  
	                   smartUpload.setAllowedFilesList("jpg,doc,rar,txt,java");  
	                   try {  
	                           // 设置不允许的文件的扩展名  
	                            smartUpload.setDeniedFilesList("exe,php");  
	                           // 上传文件  
	                            smartUpload.upload();  
	                            String path=smartUpload.getRequest().getParameter("path");
	                            System.out.println("uppath: "+path);
	                            // 保存文件,指定路径即可  
	                            //smartUpload.save("F://ftpserver/test");  
	                            smartUpload.save(path);  
	                  } catch (SQLException e) {  
	                            e.printStackTrace();  
	                 } catch (SmartUploadException e) {  
	                           e.printStackTrace();  
	                 }  	
         }
}
