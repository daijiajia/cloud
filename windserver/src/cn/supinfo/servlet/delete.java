package cn.supinfo.servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Spliterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
@WebServlet(urlPatterns="/delete")
public class delete extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		String delete=req.getParameter("filename");
		String username=req.getParameter("user");
		String path="F://ftpserver/"+username+"/";
		File file=new File(path+delete);
		 file.delete();
		System.out.println("delete file : "+path+delete);
		
		String[] paths=delete.split("/");
		for(int x=0;x<paths.length-1;x++) {
			path=path+paths[x]+"/";
		}
		File f1=new File(path);
		ArrayList<String> myfiles=new ArrayList<>();
		String[] f=f1.list();
		for(int i=0;i<f.length;i++) {
			String pathx="";
			for(int x=0;x<paths.length-1;x++) {
				pathx=pathx+paths[x]+"/";
			}
			myfiles.add(pathx+f[i]);
		}
		HttpSession mysesssion=req.getSession();
		mysesssion.setAttribute("user", username);
		mysesssion.setAttribute("filename", myfiles);
		mysesssion.setAttribute("filepath", path);
		RequestDispatcher n=req.getRequestDispatcher("/uploadfile.jsp");
		n.forward(req, resp);
	}

}
