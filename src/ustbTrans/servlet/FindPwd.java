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
 * �����˻�����
 * Servlet implementation class FindPwd
 */
public class FindPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�����ʽ����
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/javascript;charset=utf-8");
		//��ȡ�û��ύ������
        String mailbox = request.getParameter("mailbox");
        //System.out.println(mailbox);
        //��ȡ�û�����
		TransDao dao = new TransDaoimpl();
		String x = dao.find_pwd(mailbox);
		//System.out.println(x);
		//����JsonObject
	    JSONObject jsonObject = new JSONObject();
		//���ʹ����˻���Ϣ������
		if(x != null){
		    MailSenderInfo mailInfo = new MailSenderInfo();      
		    mailInfo.setMailServerHost("smtp.163.com");      
		    mailInfo.setMailServerPort("25");      
		    mailInfo.setValidate(true);      
		    mailInfo.setUserName("17801055040@163.com");      
		    mailInfo.setPassword("wmsbkgf");//������������      
		    mailInfo.setFromAddress("17801055040@163.com");      
		    mailInfo.setToAddress(mailbox);      
		    mailInfo.setSubject("���Ļ��ʷ����ʺ�����");      
		    mailInfo.setContent("��������Ϊ"+x+" ����Ū����Ŷ��");      
		    //�������Ҫ�������ʼ�     
		    SimpleMailSender sms = new SimpleMailSender();     
		    sms.sendTextMail(mailInfo);//���������ʽ   
	
	        jsonObject.put("type",1);			
		}else{jsonObject.put("type",0);}
		//����Json����
		response.getWriter().write(jsonObject.toString());	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
