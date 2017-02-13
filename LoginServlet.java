package UserLogin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

/*
 * �û���¼��servlet
 * 
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends BaseServlet {
	private static final long serialVersionUID = 7245361420327420429L;  


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 	String userName = requestJson.getString("username");  
	        String passWord = requestJson.getString("password");  
	  
	        System.out.println("�����userNameΪ" + userName + "\n�����passWordΪ" + passWord);  
	  
	        LoginService service = new LoginService();  
	        LoginResult loginResult = service.login(userName, passWord);  
	  
	        Map<String, Object> map = new HashMap<>();  
	        map.put("result", loginResult.getCode());  
	        //����ɹ�������Ҫ����token  
	        if (loginResult.getCode() == 0) {  
	            Map<String, Object> dataMap = new HashMap<>();  
	            dataMap.put("token", loginResult.getToken());  
	              
	            map.put("data", dataMap);  
	        }  
	          
	        String result = JSON.toJSONString(map);  
	        System.out.println("���Ϊ"+result);  
	          
	        PrintWriter printWriter = resp.getWriter();  
	        printWriter.write(result);  
	        printWriter.close();  
	      
	   

		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

}




