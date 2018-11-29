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
	                   // �����ļ��ϴ�����  
	                   SmartUpload smartUpload = new SmartUpload();  	          
	                   // �����ļ��ϴ����� 
	                   // ��ʼ���ļ��ϴ�����  
	                   smartUpload.initialize(this.getServletConfig(),request, response);  
	                   // ���õ����ļ������ֽ��� 	          
	                  smartUpload.setMaxFileSize(1000*100*1000);  
	                   // �������ļ�������ֽ���  
	                  smartUpload.setTotalMaxFileSize(1000*1000*100);  
	                  // ����������ļ�����չ��  
	                   smartUpload.setAllowedFilesList("jpg,doc,rar,txt,java");  
	                   try {  
	                           // ���ò�������ļ�����չ��  
	                            smartUpload.setDeniedFilesList("exe,php");  
	                           // �ϴ��ļ�  
	                            smartUpload.upload();  
	                            String path=smartUpload.getRequest().getParameter("path");
	                            System.out.println("uppath: "+path);
	                            // �����ļ�,ָ��·������  
	                            //smartUpload.save("F://ftpserver/test");  
	                            smartUpload.save(path);  
	                  } catch (SQLException e) {  
	                            e.printStackTrace();  
	                 } catch (SmartUploadException e) {  
	                           e.printStackTrace();  
	                 }  	
         }
}
