package UserLogin;

import java.sql.Connection;
import java.sql.SQLException;

import Util.ConnectionFactory;
import bean.User;

/**
 * ����ע��������ݿ�������߼��࣬���������жϣ�����ʱ���ݿ�ع��Ȳ���
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
			//ʹ�������Ƚ�ֹ���Զ��ύ
			connection.setAutoCommit(false);
			
			User user = new User();
			user.setUsername(userName);
			user.setPassword(passWord);
			
			userDao.save(connection,user);
			
			connection.commit();
			
			return 0;

		} catch (SQLException e) {
			System.out.println("����ʧ��" + "\n������Ϊ" + e.getErrorCode() + "\n������ϢΪ" + e.getMessage());
			
			//����ʧ�ܣ���Ҫ�ع�
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// mysql�ظ�����Ĵ�����Ϊ1062��������ظ����룬Message��ΪDuplicate entry 'xxx' for key
			// 'name'
			// �����һ��Ϊ���������ǿ��Ը��ݴ����õ��ظ�������е�����
			if (e.getErrorCode() == BaseDaoImplement.SQL_ERROR_CODE_DUPLICATE) {
				String errorMessage = e.getMessage();
				// ˵���û����ظ��ˣ����ݽӿڣ�Ӧ�÷���1
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
