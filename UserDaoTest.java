package test;

import java.sql.Connection;
import java.sql.SQLException;

import UserLogin.UserDao;
import UserLogin.UserDaoImplement;
import Util.ConnectionFactory;
import bean.User;

public class UserDaoTest {

	public static void main(String[] args) throws SQLException {
		// testSave();
//		testQueryUserName();
		//testQueryPassword();
		//testUpdateToken();
		testGetUserId();
	}

//	public static void testSave() throws SQLException {
//		Connection connection = ConnectionFactory.getInstance().open();  
//		UserDao dao = new UserDaoImplement();
//
//		User user = new User();
//		user.setUsername("lixiaopeng123");
//		user.setPassword("canpeng17");
//	
//
//		dao.save(connection,user);
//		ConnectionFactory.close(connection);
//	}

//	public static void testQueryUserName() throws SQLException {
//		Connection connection = ConnectionFactory.getInstance().open();  
//		UserDao dao = new UserDaoImplement();
//
//		Integer id = dao.queryUserName(connection, "lixiaopeng123");
//		if (id != 0) {
//			System.out.println("查询的id为" + id);
//		} else {
//			System.out.println("用户不存在");
//		}
//		ConnectionFactory.close(connection);
//	}
//	
//	public static void testQueryPassword() throws SQLException {
//		Connection connection = ConnectionFactory.getInstance().open();  
//		UserDao dao = new UserDaoImplement();
//		
//		Integer id = dao.queryPassWord(connection, 2, "canpeng17");
//		if (id != 0) {
//			System.out.println("查询的id为" + id);
//		} else {
//			System.out.println("密码错误");
//		}
//		ConnectionFactory.close(connection);
//	}
//	
//	public static void testUpdateToken() throws SQLException {
//		Connection connection = ConnectionFactory.getInstance().open();  
//		UserDao dao = new UserDaoImplement();
//		
//		dao.updateToken(connection,2, "2_1470576360275");
//		ConnectionFactory.close(connection);
//		
//	}
	
	public static void testGetUserId() throws SQLException {
		Connection connection = ConnectionFactory.getInstance().open();  
		UserDao dao = new UserDaoImplement();
		
		Integer id = dao.getUserId(connection,"2_1470576360275");
		if (id != 0) {
			System.out.println("查询的结果为："+id);
		} else {
			System.out.println("用户不存在");
		}
		ConnectionFactory.close(connection);
	}

}
