package ustbTrans.servlet;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import ustbTrans.DAO.TransDao;
import ustbTrans.DAO.impl.TransDaoimpl;

/**
 * Servlet implementation class ShowCus
 */
public class ShowCus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�����ʽ����
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/javascript;charset=utf-8");
		//��ȡĿ�굥��
        String word = request.getParameter("word");
		//����DAO��Ļ�ȡ�Զ������ķ���
		TransDao dao = new TransDaoimpl();
		String x= dao.get_cus(word);
        Cookie[] cookie = request.getCookies();
        cookie[0].setMaxAge(30*60);
        //����cookie
        response.addCookie(cookie[0]);
		//����JsonObject
	    JSONObject jsonObject = new JSONObject();
        JSONArray array1 = new JSONArray();
        JSONArray array2 = new JSONArray();
        int i=0;
        String s;
		if(x!=null && x!=""){
	        jsonObject.put("type",1);
	        JSONObject obj = JSONObject.fromObject(x);
	        //����DAO��Ļ�ȡ�Զ�������
	        @SuppressWarnings("rawtypes")
			Iterator iterator = obj.keys();
	        while(iterator.hasNext()){
	        	s=(String)iterator.next();
	        	array1.add(i, s);
	        	array2.add(i,obj.getString(s));
	        	i++;
	        }
	        jsonObject.element("trans",array1);
	        jsonObject.element("score",array2);
		}else{jsonObject.put("type",0);}
		//����Json����
		response.getWriter().write(jsonObject.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
