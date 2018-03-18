package ustbTrans.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import ustbTrans.DAO.TransDao;
import ustbTrans.DAO.impl.TransDaoimpl;
import ustbTrans.util.MailSenderInfo;
import ustbTrans.util.SimpleMailSender;

/**
 * 发送账户密码
 * Servlet implementation class FindPwd
 */
public class FindPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//编码格式处理
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/javascript;charset=utf-8");
		//获取用户提交的邮箱
        String mailbox = request.getParameter("mailbox");
        //System.out.println(mailbox);
        //获取用户密码
		TransDao dao = new TransDaoimpl();
		String x = dao.find_pwd(mailbox);
		//System.out.println(x);
		//创建JsonObject
	    JSONObject jsonObject = new JSONObject();
		//发送带有账户信息的邮箱
		if(x != null){
		    MailSenderInfo mailInfo = new MailSenderInfo();      
		    mailInfo.setMailServerHost("smtp.163.com");      
		    mailInfo.setMailServerPort("25");      
		    mailInfo.setValidate(true);      
		    mailInfo.setUserName("17801055040@163.com");      
		    mailInfo.setPassword("wmsbkgf");//您的邮箱密码      
		    mailInfo.setFromAddress("17801055040@163.com");      
		    mailInfo.setToAddress(mailbox);      
		    mailInfo.setSubject("您的划词翻译帐号密码");      
		    mailInfo.setContent("您的密码为"+x+" 别再弄丢了哦！");      
		    //这个类主要来发送邮件     
		    SimpleMailSender sms = new SimpleMailSender();     
		    sms.sendTextMail(mailInfo);//发送文体格式   
	
	        jsonObject.put("type",1);			
		}else{jsonObject.put("type",0);}
		//发送Json对象
		response.getWriter().write(jsonObject.toString());	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
