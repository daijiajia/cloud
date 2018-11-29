package cn.supinfo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.supinfo.user.user;

import java.io.File;

@WebServlet(urlPatterns="/login")
public class LoginServlet extends HttpServlet{
	String email;
	String password;
	EntityManagerFactory emf;
	EntityManager em;
	EntityTransaction t;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getParameter("email"));
		System.out.println(req.getParameter("password"));
		email=req.getParameter("email");
		password=req.getParameter("password");
		em=emf.createEntityManager();
		Query query = em.createQuery("SELECT user FROM user AS user WHERE user.email = '"+email+"'");
		user User=(user)query.getSingleResult();
		
		
		
		if(User.getPassword().equals(password)) {
				
			String filepath = "F://ftpserver/"+User.getName()+"/";
			File f1=new File(filepath);
			ArrayList<String> myfiles=new ArrayList<>();
			String[] f=f1.list();
			for(int i=0;i<f.length;i++) {
				myfiles.add(f[i]);
			}
			HttpSession mysesssion=req.getSession();
			
			mysesssion.setAttribute("filepath", filepath);
			mysesssion.setAttribute("filename", myfiles);
			mysesssion.setAttribute("user", User.getName());
			RequestDispatcher n=req.getRequestDispatcher("/uploadfile.jsp");
			n.forward(req, resp);

		}else {
			RequestDispatcher n=req.getRequestDispatcher("/uploadfile.jsp");
			n.forward(req, resp);

		}
		
	}
	@Override
	public void destroy() {
		emf.close();
		em.close();
	}
	@Override
	public void init() throws ServletException {
		emf=Persistence.createEntityManagerFactory("MY_PROJECT");
	}

}
