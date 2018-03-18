package ustbTrans.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ustbTrans.DAO.TransDao;
import ustbTrans.DAO.impl.TransDaoimpl;
import ustbTrans.entity.MARK;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class Mark
 */
public class Mark extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�����ʽ����
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/javascript;charset=utf-8");
		//����Ŀ�����弰���Ӧ���ʺͷ���
        float score = Float.parseFloat(request.getParameter("score"));
        String word = request.getParameter("word");
        String trans = request.getParameter("trans");	
        //System.out.println(word+trans+score);
        MARK mark = new MARK();
        mark.setW_word(word);
        mark.setTranslation(trans);
        mark.setScore(score);
        System.out.println(mark);
        //����������
		TransDao dao = new TransDaoimpl();
		float x = dao.cal_mark(score,mark);
		 System.out.println("�·���="+x);
		//�������ݿ�
		int y = dao.update_mark(x, word, trans);
        Cookie[] cookie = request.getCookies();
        cookie[0].setMaxAge(30*60);
        //����cookie
        response.addCookie(cookie[0]);
		//����JsonObject
	    JSONObject jsonObject = new JSONObject();
	    if(y==1){
		    jsonObject.put("type",1);
		    jsonObject.put("score",x);	    	
	    }else{jsonObject.put("type",0);}
		//����Json����
		response.getWriter().write(jsonObject.toString());	
		System.out.println(jsonObject.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
