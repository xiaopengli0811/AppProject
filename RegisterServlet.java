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
 * 用户注册的servlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends BaseServlet {
	
	private static final long serialVersionUID = 7245361420327420429L;  


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userName = requestJson.getString("username");
		String passWord = requestJson.getString("password"); 
		
		System.out.println("注册的userName为" + userName + "\n请求的passWord为" + passWord);  
		
		RegisterServiceImpl service = new RegisterServiceImpl();
		
		 if(userName==null||"".equals(userName.trim())||passWord==null||"".equals(passWord.trim())){
			 
			 System.out.println("用户名或密码不能为空！");
			 return;
		}
		 Integer regresult = (Integer)service.register(userName, passWord);
		 
		 Map<String, Object> map = new HashMap<>();  
	     map.put("result", regresult);  
	     
	     //注册成功还需加上token
	     if (regresult == 0) {  
	            long currentTime  = System.currentTimeMillis();  
	            String token = "_"+currentTime;  
	            Map<String, Object> dataMap = new HashMap<>();  
	            dataMap.put("token", token);  
	              
	            map.put("data", dataMap);  
	        }  
	     
	     	String result = JSON.toJSONString(map);  
	        System.out.println("结果为"+result);  
	          
	        PrintWriter printWriter = resp.getWriter();  
	        printWriter.write(result);  
	        printWriter.close();  
	     
		
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

}





