package ustbTrans.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import ustbTrans.DAO.TransDao;
import ustbTrans.DAO.impl.TransDaoimpl;

/**
 * Servlet implementation class ChangePwd
 */
public class ChangePwd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//编码格式处理
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/javascript;charset=utf-8");
		//获取用户提交的用户名、密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String oldpwd = request.getParameter("oldpassword");
        //System.out.println(password+username+oldpassword);
        Cookie[] cookie = request.getCookies();
        cookie[0].setMaxAge(30*60);
        //发送cookie
        response.addCookie(cookie[0]);
        //调用DAO类的更改密码方法
		TransDao dao = new TransDaoimpl();
		int x = dao.modify_pwd(username,password,oldpwd);
		//创建JsonObject
	    JSONObject jsonObject = new JSONObject();
		if(x==1){
	        jsonObject.put("type",1);
		}else{jsonObject.put("type",0);}
		//发送Json对象
		response.getWriter().write(jsonObject.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
