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
		//编码格式处理
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/javascript;charset=utf-8");
		//接收目标释义及其对应单词和分数
        float score = Float.parseFloat(request.getParameter("score"));
        String word = request.getParameter("word");
        String trans = request.getParameter("trans");	
        //System.out.println(word+trans+score);
        MARK mark = new MARK();
        mark.setW_word(word);
        mark.setTranslation(trans);
        mark.setScore(score);
        System.out.println(mark);
        //计算新评分
		TransDao dao = new TransDaoimpl();
		float x = dao.cal_mark(score,mark);
		 System.out.println("新分数="+x);
		//更新数据库
		int y = dao.update_mark(x, word, trans);
        Cookie[] cookie = request.getCookies();
        cookie[0].setMaxAge(30*60);
        //发送cookie
        response.addCookie(cookie[0]);
		//创建JsonObject
	    JSONObject jsonObject = new JSONObject();
	    if(y==1){
		    jsonObject.put("type",1);
		    jsonObject.put("score",x);	    	
	    }else{jsonObject.put("type",0);}
		//发送Json对象
		response.getWriter().write(jsonObject.toString());	
		System.out.println(jsonObject.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
