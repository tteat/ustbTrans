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
import ustbTrans.entity.User;

public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//编码格式处理
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/javascript;charset=utf-8");
		//获取用户提交的用户名、密码及邮箱
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String mailbox = request.getParameter("mailbox");
        //请求数据库添加账户（包括核验用户名是否唯一）
		User user = new User();
		user.setusername(username);
		user.setpassword(password);
		user.setmailbox(mailbox);
		System.out.println(user.toString());
		//调用DAO类的添加用户方法
		TransDao dao = new TransDaoimpl();
		int x = dao.add_user(user);
		//创建JsonObject
	    JSONObject jsonObject = new JSONObject();
		if(x==1){
	        jsonObject.put("type",1);
		}else{jsonObject.put("type",0);}
		//发送Json对象
		response.getWriter().write(jsonObject.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
