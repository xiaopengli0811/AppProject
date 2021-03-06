package UserLogin;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
/** 
 * 所有HttpServlet的基类，会把请求中的加密字符串取出来并进行解密 (当前还尚未解密) 
 *  
 * 
 */ 
public class BaseServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	//请求的Json
	protected JSONObject requestJson;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String securityString = req.getParameter("s");
		System.out.println("获取的加密String为" + securityString);
		
		requestJson = JSON.parseObject(URLDecoder.decode(securityString, "UTF8"));
		
		super.service(req, resp);
	}

}



