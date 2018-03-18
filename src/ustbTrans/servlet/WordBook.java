package ustbTrans.servlet;

import java.io.IOException;

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
 * Servlet implementation class WordBook
 */
public class WordBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�����ʽ����
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/javascript;charset=utf-8");
		//��ȡ�û���
		String username = request.getParameter("username");
		//����DAO���������ʱ����ʷ���
		TransDao dao = new TransDaoimpl();
		String[] wordlist = dao.get_vo(username);
        Cookie[] cookie = request.getCookies();
        cookie[0].setMaxAge(30*60);
        //����cookie
        response.addCookie(cookie[0]);
		//����JsonObject
	    JSONObject jsonObject = new JSONObject();
        JSONArray Array1 = new JSONArray();
       // JSONArray Array2 = new JSONArray();
        int i=0;
		if(wordlist[0]!=null && wordlist.length!=0){
			jsonObject.put("type",1);
			for(String string:wordlist){
				if(string==null) break;
		        Array1.add(i, string);
		        //����API
		        //����API
		        //String a =������ֵ
		        //Array2.add(i, a);
		        i++;
			}
			jsonObject.element("wordlist",Array1);
			//jsonObject.element("translation",Array2);
		}else{jsonObject.put("type",0);}
		//����Json����
		response.getWriter().write(jsonObject.toString());
		System.out.println(jsonObject.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
