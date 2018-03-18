package ustbTrans.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class Check
 */
public class Check extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//编码格式处理
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/javascript;charset=utf-8");
		Cookie[] cookie = request.getCookies();
		JSONObject jsonObject = new JSONObject();
		if(cookie!=null)
		{
		    jsonObject.put("type",1);
		    jsonObject.put("username",cookie[0].getValue());
		}else{ jsonObject.put("type",0);}
		//发送Json对象
		response.getWriter().write(jsonObject.toString());
		System.out.println(jsonObject.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
