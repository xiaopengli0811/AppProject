package test;

import java.sql.Connection;
import java.sql.SQLException;

import UserLogin.UserDao;
import UserLogin.UserDaoImplement;
import Util.ConnectionFactory;
import bean.User;

public class ConnectionTest {

	public static void main(String[] args) throws SQLException {
		
		
		
		
			ConnectionFactory factory = ConnectionFactory.getInstance();
			Connection connection = factory.open();
			
			User user = new User();
			user.setUsername("xiaopengli");;
			user.setPassword("123456");
			
			UserDao userDao = new UserDaoImplement();
			userDao.save(connection, user);
			
			
		}

	

}
