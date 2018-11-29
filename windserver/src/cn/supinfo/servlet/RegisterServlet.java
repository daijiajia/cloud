package cn.supinfo.servlet;

import java.io.File;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.supinfo.user.user;
@WebServlet(urlPatterns="/register")
public class RegisterServlet extends HttpServlet{
	String name;
	String password;
	String email;
	EntityManagerFactory emf;
	EntityManager em;
	EntityTransaction t;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		name=req.getParameter("username");
		password=req.getParameter("password");
		email =req.getParameter("email");
		user NEW_user= new user();
		NEW_user.setName(name);
		NEW_user.setPassword(password);
		NEW_user.setEmail(email);
		em=emf.createEntityManager();
		t= em.getTransaction();			
		try {
			t.begin();
			em.persist(NEW_user);
			File file=new File("F://ftpserver/"+NEW_user.getName());
			file.mkdirs();
			t.commit();
			RequestDispatcher n=req.getRequestDispatcher("/index.html");
			n.forward(req, resp);
		}
		finally {
			if(t.isActive()) {
				t.rollback();
			}
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
