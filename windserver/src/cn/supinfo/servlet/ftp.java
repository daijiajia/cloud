package cn.supinfo.servlet;

import java.nio.charset.Charset;
import java.util.ArrayList;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
//����ftp

import com.sun.org.apache.bcel.internal.generic.RETURN;
public class ftp {
	private FTPClient ftp;//ftp����
	private String ip;//ftp ��ip
	private int port;//ftp�˿ں�
	private String name;//ftp���û���
	private String pwd;//ftp������
	public ftp(String ip,int port,String name,String pwd) {
		ftp=new FTPClient();
		this.ip=ip;
		this.port=port;
		this.name=name;
		this.pwd=pwd;
		//��֤��½
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
