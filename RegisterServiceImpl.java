package UserLogin;

import java.sql.Connection;
import java.sql.SQLException;

import Util.ConnectionFactory;
import bean.User;

/**
 * 负责注册插入数据库操作的逻辑类，包括错误判断，出错时数据库回滚等操作
 * 
 * 
 *
 */
public class RegisterServiceImpl implements RegisterService{
	
	private UserDao userDao = new UserDaoImplement();

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public int register(String userName, String passWord) {
		Connection connection =  null;
		try {
			connection = ConnectionFactory.getInstance().open();
			//使用事务，先禁止其自动提交
			connection.setAutoCommit(false);
			
			User user = new User();
			user.setUsername(userName);
			user.setPassword(passWord);
			
			userDao.save(connection,user);
			
			connection.commit();
			
			return 0;

		} catch (SQLException e) {
			System.out.println("插入失败" + "\n错误码为" + e.getErrorCode() + "\n错误信息为" + e.getMessage());
			
			//插入失败，需要回滚
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// mysql重复插入的错误码为1062，如果是重复插入，Message会为Duplicate entry 'xxx' for key
			// 'name'
			// 即最后一个为列名，我们可以根据此来得到重复插入的列的名称
			if (e.getErrorCode() == BaseDaoImplement.SQL_ERROR_CODE_DUPLICATE) {
				String errorMessage = e.getMessage();
				// 说明用户名重复了，根据接口，应该返回1
				if (errorMessage.endsWith("'name'")) {
					return 1;
				}
				
			} else {
				return 100;
			}
		}
		
		return 0;
	}

}
