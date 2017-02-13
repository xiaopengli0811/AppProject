package UserLogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.User;

public class UserDaoImplement implements UserDao {

	@Override
	public void save(Connection connection, User user) throws SQLException {
		
		String sql = "insert into user(name,password)values(?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, user.getUsername());
		statement.setString(2, user.getPassword());
		statement.execute();

	}

	@Override
	public int queryUserName(Connection connection, String username) throws SQLException {
		String sql = "Select * from user where name = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, username);
		ResultSet set = statement.executeQuery();
		//�����һ�в�Ϊ�գ������ݿ⺬��username���򷵻����ݵ�ID
		if (set.next()) {
			return set.getInt("id");
		} else {
			return 0;
		}
		
	}

	@Override
	public int queryPassWord(Connection connection, int id, String password) throws SQLException {
		
		String sql = "Select * from user where id = ? and password = ?";
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, id);
		statement.setString(2, password);

		ResultSet set = statement.executeQuery();
		// ��һ����Ϊ�գ�˵�����ݿ��а����д��ֶ�,�򷵻ش������ݵ�id
		if (set.next()) {
			return set.getInt("id");
		} else {
			return 0;
		}
	}

	@Override
	public void updateToken(Connection connection, int userId, String token) throws SQLException {
		String sql = "update user set token =  ? where id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, token);
		statement.setInt(2, userId);

		statement.execute();

	}

	@Override
	public int getUserId(Connection connection, String token) throws SQLException {
		String sql = "select * from user where token = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, token);
		statement.executeQuery();
		
		ResultSet set = statement.executeQuery();
		if (set.next()) {
			return set.getInt("id");
		} else {
			return 0;
		}
	}

}




