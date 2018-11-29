 package cn.supinfo.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
@WebServlet(urlPatterns="/download")

public class dodownload extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String filename=req.getParameter("filename");
		String newfolder=req.getParameter("new");
		

		SmartUpload smartUpload=new SmartUpload();
		smartUpload.initialize(this.getServletConfig(), req, resp);
		smartUpload.setContentDisposition(null);

		String username=req.getParameter("user");
		String path="F://ftpserver/"+username+"/";
		System.out.println( "filename: "+filename);
		System.out.println( "path: "+path);

		System.out.println("foldername: "+newfolder);
		try {
			
			if(!newfolder.equals("no")) {
				String[] pa_name=filename.split(path);
				System.out.println("long: "+pa_name.length);
				
				if(pa_name.length != 0) {
					System.out.println("path: "+pa_name[1]);
					String[] all_path=pa_name[1].split("/");
					File file2 = new File(filename+"/"+newfolder+"/");
					file2.mkdirs();
				
				//	String filepath = "F://ftpserver/test/"+filename+"/";
					File f1=new File(filename);
					ArrayList<String> myfiles=new ArrayList<>();
					String[] f=f1.list();
					for(int i=0;i<f.length;i++) {
						String beh="";
						for(int x=0;x<all_path.length;x++) {
							beh=beh+all_path[x]+"/";
						}
						myfiles.add(beh+f[i]);
					}
					HttpSession mysesssion=req.getSession();
					mysesssion.setAttribute("user", username);
					mysesssion.setAttribute("filename", myfiles);
					mysesssion.setAttribute("filepath", filename);
					RequestDispatcher n=req.getRequestDispatcher("/uploadfile.jsp");
					n.forward(req, resp);
				}else {
					File file2 = new File(filename+"/"+newfolder+"/");
					file2.mkdirs();
					File f1=new File(filename);
					ArrayList<String> myfiles=new ArrayList<>();
					String[] f=f1.list();
					for(int i=0;i<f.length;i++) {				
						myfiles.add(f[i]);
					}
					HttpSession mysesssion=req.getSession();
					mysesssion.setAttribute("user", username);
					mysesssion.setAttribute("filename", myfiles);
					mysesssion.setAttribute("filepath", filename);
					RequestDispatcher n=req.getRequestDispatcher("/uploadfile.jsp");
					n.forward(req, resp);
				}
			}
			else {
			//File file=new File("F://ftpserver/test/"+filename+"/");	
			File file=new File("F://ftpserver/"+username+"/"+filename+"/");	
			if(file.isDirectory()) {
				
			//	String filepath = "F://ftpserver/test/"+filename;
				String filepath = "F://ftpserver/"+username+"/"+filename;
				File f1=new File(filepath);
				ArrayList<String> myfiles=new ArrayList<>();
				String[] f=f1.list();
				for(int i=0;i<f.length;i++) {
					myfiles.add(filename+"/"+f[i]);
				}
				HttpSession mysesssion=req.getSession();
				mysesssion.setAttribute("user", username);
				mysesssion.setAttribute("filename", myfiles);
				mysesssion.setAttribute("filepath", filepath);
				RequestDispatcher n=req.getRequestDispatcher("/uploadfile.jsp");
				n.forward(req, resp);
			}else {
			smartUpload.downloadFile("F://ftpserver/"+username+"/"+filename);
			
		}}
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	
}
