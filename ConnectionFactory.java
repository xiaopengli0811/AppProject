package Util;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/*
 * �������ݿ�Ĺ����ࡣ
 * 
 */
public class ConnectionFactory {
	
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	
	private static ConnectionFactory mConnectionFactory = new ConnectionFactory();
	private ConnectionFactory() {

	}
	
	//ʹ�þ�̬������ʼ���ĸ�������Ϣ��
	static{
		Properties prop = new Properties();
		Reader in;
		try {
			in = new FileReader("src\\DBconfig.properties");
			prop.load(in);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		
		driver = prop.getProperty("driver");
		url = prop.getProperty("url");
		username = prop.getProperty("username");
		password = prop.getProperty("password");
	}
	

	//��ȡConnectionFactory����
	public static ConnectionFactory getInstance() {
		return mConnectionFactory;
	}
	
	private Connection mConnection;
	public  Connection open(){
		
		try {
			Class.forName(driver);
			mConnection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return mConnection;
		
	}
	
	
	//��ȡ
	public static void close(Connection conn){
		
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	

}
