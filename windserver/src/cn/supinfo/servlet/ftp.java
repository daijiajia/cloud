package cn.supinfo.servlet;

import java.nio.charset.Charset;
import java.util.ArrayList;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
//链接ftp

import com.sun.org.apache.bcel.internal.generic.RETURN;
public class ftp {
	private FTPClient ftp;//ftp对象
	private String ip;//ftp 的ip
	private int port;//ftp端口号
	private String name;//ftp的用户名
	private String pwd;//ftp的密码
	public ftp(String ip,int port,String name,String pwd) {
		ftp=new FTPClient();
		this.ip=ip;
		this.port=port;
		this.name=name;
		this.pwd=pwd;
		//验证登陆
		try {
			ftp.connect(ip,port);
			System.out.println(ftp.login(name, pwd));
			ftp.setCharset(Charset.forName("UTF-8"));
			 ftp.setControlEncoding("UTF-8");

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	public ArrayList<String> getFilesName() {
		ArrayList<String> a=new ArrayList<>();
		try {
			FTPFile[] files=ftp.listFiles("/test");
			
			for(int i=0;i<files.length;i++) {
				System.out.println(files[i].getName());
				a.add(files[i].getName());
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return a;
	}
	

}
