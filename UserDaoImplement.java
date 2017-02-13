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
		//如果下一行不为空，在数据库含有username，则返回数据的ID
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
		// 下一个不为空，说明数据库中包含有此字段,则返回此条数据的id
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




