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
import ustbTrans.entity.Custom;

/**
 * Servlet implementation class AddCus
 */
public class AddCus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�����ʽ����
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/javascript;charset=utf-8");
		//����Ŀ�����弰���Ӧ���ʺͷ���
        String word = request.getParameter("word");
        String trans = request.getParameter("trans");		
        Custom cus = new Custom();
        cus.setW_word(word);
        cus.setTranslation(trans);
        //System.out.println(cus.toString());
        
		//����DAO��ķ���
		TransDao dao = new TransDaoimpl();
		int x = dao.add_cus(cus);
        Cookie[] cookie = request.getCookies();
        cookie[0].setMaxAge(30*60);
        //����cookie
        response.addCookie(cookie[0]);
		//����JsonObject
	    JSONObject jsonObject = new JSONObject();
		if(x==1){
	        jsonObject.put("type",1);
		}else{jsonObject.put("type",0);}
		//����Json����
		response.getWriter().write(jsonObject.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
