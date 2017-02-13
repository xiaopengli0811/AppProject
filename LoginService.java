package UserLogin;

import java.sql.Connection;
import java.sql.SQLException;

import Util.ConnectionFactory;



public class LoginService {
	
	private UserDao mUserDao = new UserDaoImplement();  
    
    private static final int RESULT_NULL_USERNAME = 1,RESULT_WRONG_PASSWORD = 2;  
    
    public LoginResult login(String userName, String passWord) {  
        
        Connection connection = null;  
        
        LoginResult result = new LoginResult();  
          
        connection = ConnectionFactory.getInstance().open();  
        
          
        try {  
            //1�����ж��Ƿ�����Ӧ���û���  
            int id = mUserDao.queryUserName(connection, userName);  
            if (id == 0) {  
                result.setCode(RESULT_NULL_USERNAME);  
                return result;  
            }  
              
            //2�����ж������Ƿ���ȷ  
            int userId = mUserDao.queryPassWord(connection, id, passWord);  
            if (userId == 0) {  
                result.setCode(RESULT_WRONG_PASSWORD);  
                return result;  
            }  
              
            //3��������Ӧ��token  
            long currentTime  = System.currentTimeMillis();  
            String token = userId+"_"+currentTime;  
              
            mUserDao.updateToken(connection, userId, token); 
            
            result.setCode(0);  
            
            result.setToken(token);  
              
            return result;  
        } catch (SQLException e) {  
              
            e.printStackTrace();  
              
            result.setCode(100);  
            return result;  
        }finally{
			ConnectionFactory.close(connection);
        }
    }  
      

}



