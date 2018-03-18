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
 * Servlet implementation class Switch
 */
public class Switch extends HttpServlet {
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
        int state = Integer.parseInt(request.getParameter("state"));
        
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

        Memory mem = new Memory();
        mem.setUsername(username);
        mem.setUrl(url);
        mem.setDom(Array1.toString());
        mem.setSequence(Array2.toString());
        mem.setOffsetx(Integer.parseInt(offsetx));
        mem.setOffsety(Integer.parseInt(offsety));   
        //System.out.println(mem.toString());
		TransDao dao = new TransDaoimpl();
        Cookie[] cookie = request.getCookies();
        cookie[0].setMaxAge(30*60);
        //发送cookie
        response.addCookie(cookie[0]);
		//创建JsonObject
	    JSONObject jsonObject = new JSONObject();
	    
        if(state==1){
    		String x = dao.get_word(mem);
    		System.out.println(x);
    		if(x==null || x=="")
    		{
    			jsonObject.put("type",0);
    		}else{	
    			jsonObject.put("trans",x);
    			jsonObject.put("type",1);
    		}
        }
        else {
        	String x = dao.get_trans(dao.check_mem(mem));
    		if(x==null || x=="")
    		{
    			jsonObject.put("type",0);
    		}else{	
    			jsonObject.put("trans",x);
    			jsonObject.put("type",1);
    		}
        }
		//发送Json对象
		response.getWriter().write(jsonObject.toString());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
