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
 * ����HttpServlet�Ļ��࣬��������еļ����ַ���ȡ���������н��� (��ǰ����δ����) 
 *  
 * 
 */ 
public class BaseServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	//�����Json
	protected JSONObject requestJson;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String securityString = req.getParameter("s");
		System.out.println("��ȡ�ļ���StringΪ" + securityString);
		
		requestJson = JSON.parseObject(URLDecoder.decode(securityString, "UTF8"));
		
		super.service(req, resp);
	}

}



