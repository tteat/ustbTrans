package ustbTrans.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import ustbTrans.DAO.TransDao;
import ustbTrans.DAO.impl.TransDaoimpl;
import ustbTrans.entity.Memory;

/**
 * Servlet implementation class Choose
 */
public class Choose extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//编码格式处理
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/javascript;charset=utf-8");
		//获取用户、单词、网址、以及位置信息
		String username = request.getParameter("username");
        String url = request.getParameter("url");
        String[] dom = request.getParameterValues("dom[]");
        String[] sequence = request.getParameterValues("sequence[]");
        String offsetx = request.getParameter("offsetx");
        String offsety = request.getParameter("offsety");
        String word = request.getParameter("word");
        String trans = request.getParameter("trans");
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
        
        //将信息封装进Memory对象
        Memory mem = new Memory();
        mem.setUsername(username);
        mem.setM_word(word);
        mem.setTrans(trans);
        mem.setUrl(url);
        mem.setDom(Array1.toString());
        mem.setSequence(Array2.toString());
        mem.setOffsetx(Integer.parseInt(offsetx));
        mem.setOffsety(Integer.parseInt(offsety));  
        //System.out.println(mem.toString());
        Cookie[] cookie = request.getCookies();
        cookie[0].setMaxAge(30*60);
        //发送cookie
        response.addCookie(cookie[0]);
        //调用DAO中添加历史记录方法
		TransDao dao = new TransDaoimpl();
		int x = dao.check_mem(mem);
		if(x!=0){
			mem.setM_no(x);
			//System.out.println(mem.toString());
			dao.modify_mem(mem);
		}
		else{
			dao.add_mem(mem);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
