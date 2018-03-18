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
import ustbTrans.entity.Memory;

/**
 * Servlet implementation class LastSelect
 */
public class LastSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�����ʽ����
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/javascript;charset=utf-8");
		//��ȡ�û������ʡ���ַ���Լ�λ����Ϣ
		String username = request.getParameter("username");
        String word = request.getParameter("word");
        String url = request.getParameter("url");
        String[] dom = request.getParameterValues("dom[]");
        String[] sequence = request.getParameterValues("sequence[]");
        String offsetx = request.getParameter("offsetx");
        String offsety = request.getParameter("offsety");
        
        int i=0;
        JSONArray Array1 = new JSONArray();
        for(String s:dom){
        	Array1.add(i, s);
        	i++;
        }
        i=0;
        JSONArray Array2 = new JSONArray();
        for(String s:sequence){
        	Array2.add(i, s);
        	i++;
        }

        //��ѯ�Ƿ���ڼ�¼
        Memory mem = new Memory();
        mem.setUsername(username);
        mem.setM_word(word);        
        mem.setUrl(url);
        mem.setDom(Array1.toString());
        mem.setSequence(Array2.toString());
        mem.setOffsetx(Integer.parseInt(offsetx));
        mem.setOffsety(Integer.parseInt(offsety));   
        System.out.println(mem.toString());
        Cookie[] cookie = request.getCookies();
        cookie[0].setMaxAge(30*60);
        //����cookie
        response.addCookie(cookie[0]);
        //����DAO��Ĳ�ѯ�Ƿ���ڼ�¼����
		TransDao dao = new TransDaoimpl();
		//���������ʼ������ݿ�
        dao.add_word(username, word);
        //�����Ƿ��м�¼
		int x = dao.check_mem(mem);
		System.out.println("x��ֵ��"+x);
		//����JsonObject
	    JSONObject jsonObject = new JSONObject();
		if(x!=0){
	        String result = dao.get_trans(x);
	        jsonObject.put("type",1);
	        jsonObject.put("trans",result);
		}else{jsonObject.put("type",0);}
		//����Json����
		response.getWriter().write(jsonObject.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
