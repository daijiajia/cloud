package cn.supinfo.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(urlPatterns="/goup")
public class goup extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String up=req.getParameter("filename");
		String username=req.getParameter("user");
		String path="F://ftpserver/"+username+"/";
		System.out.println("mainpath: "+path);
		System.out.println("path: "+up);
		String[] paths=up.split(path);
		System.out.println(paths[1]);
		String[] topath=paths[1].split("/");
		for(int x=0;x<topath.length-1;x++) {
			System.out.println(topath[x]);
			path=path+topath[x]+"/";
		}
		System.out.println("goup "+path);
		File f1=new File(path);
		ArrayList<String> myfiles=new ArrayList<>();
		String[] f=f1.list();
		for(int i=0;i<f.length;i++) {
			String pathx="";
			for(int x=0;x<topath.length-1;x++) {
				pathx=pathx+topath[x]+"/";
			}
			myfiles.add(pathx+f[i]);
		}
		HttpSession mysesssion=req.getSession();
		mysesssion.setAttribute("filename", myfiles);
		mysesssion.setAttribute("filepath", path);
		RequestDispatcher n=req.getRequestDispatcher("/uploadfile.jsp");
		n.forward(req, resp);
	}

}
