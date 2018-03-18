package ustbTrans.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Logout
 */
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//编码格式处理
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/javascript;charset=utf-8");
		//获取目标用户名
        String username = request.getParameter("username");
        //删除cookie
        Cookie cookie = new Cookie("username",username);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
