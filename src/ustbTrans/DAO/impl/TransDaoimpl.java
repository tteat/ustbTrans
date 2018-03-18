package ustbTrans.DAO.impl;

import ustbTrans.entity.Custom;
import ustbTrans.entity.MARK;
import ustbTrans.entity.Memory;
import ustbTrans.entity.User;
import ustbTrans.DAO.TransDao;
import ustbTrans.util.MysqlUtil;

import java.sql.Connection;
import java.sql.ResultSet;

import net.sf.json.JSONObject;


public class TransDaoimpl implements TransDao{
	
	/*
	 * 	(non-Javadoc)
	 * @see ustbTrans.DAO.TransDao#add_user(ustbTrans.entity.User)
	 * 功能：添加用户
	 * 返回值：添加成功，返回1；添加失败，返回0；
	 */
	public int add_user(User user){
		Connection conn = null;
		java.sql.PreparedStatement stmt1 = null;
		java.sql.PreparedStatement stmt2 = null;
		java.sql.PreparedStatement stmt3 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		try{
			conn = MysqlUtil.getConnection();
			String sql1;
			String sql2;
			String sql3;
			sql1 = "SELECT * FROM `user` WHERE Username=?";
			stmt1 = conn.prepareStatement(sql1);
			stmt1.setString(1, user.getusername());
			rs1 = stmt1.executeQuery();
			sql2 = "SELECT * FROM `user` WHERE Mailbox=?";
			stmt2 = conn.prepareStatement(sql2);
			stmt2.setString(1, user.getmailbox());
			rs2 = stmt2.executeQuery();
			if(rs1.next() || rs2.next()){
				return 0;
			}
			sql3 = "INSERT INTO `user` (Username,Password,Mailbox)VALUES(?,?,?)";
			stmt3 = conn.prepareStatement(sql3);
			stmt3.setString(1, user.getusername());
			stmt3.setString(2, user.getpassword());
			stmt3.setString(3, user.getmailbox());
			
			int flag = stmt3.executeUpdate();
			if(flag == 0) return 0;
			else return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			MysqlUtil.close(conn, stmt1,rs1);
			MysqlUtil.close(conn, stmt2,rs2);
			MysqlUtil.close(conn, stmt3);
		}
	}
	/*
	 * (non-Javadoc)
	 * @see ustbTrans.DAO.TransDao#del_user(java.lang.String)
	 * 功能：删除用户
	 * 返回值：删除成功，返回 ；删除失败，返回0
	 */
	public int del_user(String username){
		Connection conn = null;
		java.sql.PreparedStatement stmt = null;
		try{
			conn = MysqlUtil.getConnection();
			System.out.print(conn);
			String sql;
			sql = "DELETE FROM `user` WHERE Username=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			
			int flag = stmt.executeUpdate();
			if(flag == 0) return 0;
			else return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			MysqlUtil.close(conn, stmt);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see ustbTrans.DAO.TransDao#check_login(ustbTrans.entity.User)
	 * 功能：验证用户登录信息
	 * 返回值：验证成功，返回1；验证失败，返回0
	 */
	public int check_login(User user){
		Connection conn = null;
		java.sql.PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = MysqlUtil.getConnection();
			String sql;
			sql = "SELECT * FROM `user`WHERE Username=?";				
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,user.getusername());
			rs = stmt.executeQuery();
			if(rs.next()){
				if(rs.getString("Password").equals(user.getpassword())){
					return 1;
				}
				else return 0;
			}
			else return 0;
						
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			MysqlUtil.close(conn, stmt, rs);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see ustbTrans.DAO.TransDao#modify_pwd(java.lang.String, java.lang.String)
	 * 功能：修改密码
	 * 参数：username-用户名  password-新密码
	 * 返回值：修改成功，返回1；修改失败，返回0
	 */
	public int modify_pwd(String username,String password,String oldpwd){
		Connection conn = null;
		java.sql.PreparedStatement stmt1 = null;
		java.sql.PreparedStatement stmt2 = null;
		ResultSet rs = null;
		try{
			conn = MysqlUtil.getConnection();
			String sql1;
			String sql2;
			sql1 = "SELECT * FROM `user` WHERE Username=?";
			stmt1 = conn.prepareStatement(sql1);
			stmt1.setString(1, username);
			rs = stmt1.executeQuery();
			if(rs.next()){
				if(rs.getString("Password").equals(oldpwd)){
					sql2 = "UPDATE `user` SET `Password`=? WHERE Username=?";
					stmt2 = conn.prepareStatement(sql2);
					stmt2.setString(1, password);
					stmt2.setString(2, username);			
					int flag = stmt2.executeUpdate();
					if(flag == 0) return 0;
					else return 1;
				}
			}
			return 0;			
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			MysqlUtil.close(conn, stmt1,rs);
			MysqlUtil.close(conn, stmt2);
		}		
	}
	
	/*
	 * (non-Javadoc)
	 * @see ustbTrans.DAO.TransDao#find_pwd(java.lang.String)
	 * 功能：根据邮箱返回用户密码
	 * 返回值：获取密码成功，返回密码字符串；获取失败，返回空
	 */
	public String find_pwd(String mailbox){
		Connection conn = null;
		java.sql.PreparedStatement stmt = null;
		ResultSet rs = null;
		String pwd = null;
		try{
			conn = MysqlUtil.getConnection();
			String sql;
			sql = "SELECT * FROM `user`WHERE Mailbox=?";				
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,mailbox);
			rs = stmt.executeQuery();
			if(rs.next()){
				pwd = rs.getString("Password");
			}		
			return pwd;				
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			MysqlUtil.close(conn, stmt, rs);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see ustbTrans.DAO.TransDao#add_mem(ustbTrans.entity.Memory)
	 * 功能：添加一条历史记录
	 * 返回值：添加成功，返回1；添加失败，返回0
	 */
	public int add_mem(Memory mem){
		Connection conn = null;
		java.sql.PreparedStatement stmt = null;
		try{
			conn = MysqlUtil.getConnection();
			String sql;
			sql = "INSERT INTO memory (Url,DOM,Sequence,OffsetX,OffsetY,M_word,Trans,Username) VALUES(?,?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, mem.getUrl());
			stmt.setString(2, mem.getDom());
			stmt.setString(3, mem.getSequence());
			stmt.setInt(4, mem.getOffsetx());
			stmt.setInt(5, mem.getOffsety());
			stmt.setString(6, mem.getM_word());
			stmt.setString(7, mem.getTrans());
			stmt.setString(8, mem.getUsername());
			
			int flag = stmt.executeUpdate();
			if(flag == 0) return 0;
			else return 1;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			MysqlUtil.close(conn, stmt);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see ustbTrans.DAO.TransDao#check_mem(ustbTrans.entity.Memory)
	 * 功能：检查是否存在历史记录
	 * 返回值：存在返回M_no，不存在返回0
	 */
	public int check_mem(Memory mem){
		Connection conn = null;
		java.sql.PreparedStatement stmt = null;
		ResultSet rs = null;
		int i = 0;
		try{
			conn = MysqlUtil.getConnection();
			String sql;
			sql = "SELECT * FROM memory WHERE Url=? AND DOM=? AND Sequence=? AND OffsetX=? AND OffsetY=?";				
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,mem.getUrl());
			stmt.setString(2,mem.getDom());
			stmt.setString(3,mem.getSequence());
			stmt.setInt(4,mem.getOffsetx());
			stmt.setInt(5,mem.getOffsety());
			
			rs = stmt.executeQuery();
			if(rs.next()){
				i = rs.getInt("M_no");
			}
			return i;						
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			MysqlUtil.close(conn, stmt, rs);
		}		
	}
	
	/*
	 * (non-Javadoc)
	 * @see ustbTrans.DAO.TransDao#get_trans(int)
	 * 功能：获取用户选择过的释义
	 * 返回值：获取成功，返回译文字符串；获取失败，返回空
	 */
	public String get_trans(int M_no){
		Connection conn = null;
		java.sql.PreparedStatement stmt = null;
		ResultSet rs = null;
		String trans = null;
		try{
			conn = MysqlUtil.getConnection();
			String sql;
			sql = "SELECT * FROM memory WHERE M_no=?";		
			stmt = conn.prepareStatement(sql);			
			stmt.setInt(1, M_no);
			
			rs = stmt.executeQuery();
			if(rs.next()){
				trans = rs.getString("Trans");
			}
			return trans;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			MysqlUtil.close(conn, stmt, rs);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see ustbTrans.DAO.TransDao#get_word(ustbTrans.entity.Memory)
	 * 功能：获取英文单词，以实现中文英文互相切换的功能
	 * 返回值：获取成功，返回单词字符串；获取失败，返回空
	 */
	public String get_word(Memory mem){
		Connection conn = null;
		java.sql.PreparedStatement stmt = null;
		ResultSet rs = null;
		String word = null;
		try{
			conn = MysqlUtil.getConnection();
			String sql;
			sql = "SELECT * FROM memory WHERE Url=? AND DOM=? AND Sequence=? AND OffsetX=?";				
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,mem.getUrl());
			stmt.setString(2,mem.getDom());
			stmt.setString(3,mem.getSequence());
			stmt.setInt(4,mem.getOffsetx());
			
			rs = stmt.executeQuery();
			if(rs.next()){
				word = rs.getString("M_word");
			}
			return word;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			MysqlUtil.close(conn, stmt, rs);
		}	
	}
	
	/*
	 * (non-Javadoc)
	 * @see ustbTrans.DAO.TransDao#modify_mem(ustbTrans.entity.Memory)
	 * 功能：修改历史记录
	 * 返回值：修改成功，返回1；修改失败，返回0
	 */
	public int modify_mem(Memory mem){
		Connection conn = null;
		java.sql.PreparedStatement stmt = null;
		try{
			conn = MysqlUtil.getConnection();
			String sql;
			sql = "UPDATE memory SET trans=?,M_word=? WHERE M_no=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, mem.getTrans());
			stmt.setString(2, mem.getM_word());
			stmt.setInt(3,mem.getM_no());
			
			int flag = stmt.executeUpdate();
			if(flag == 0) return 0;
			else return 1;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			MysqlUtil.close(conn, stmt);
		}		
	}
	
	/*
	 * (non-Javadoc)
	 * @see ustbTrans.DAO.TransDao#add_vo(java.lang.String, java.lang.String)
	 * 功能：向生词本中添加单词
	 * 返回值：添加成功，返回1；已添加过，返回2；添加失败，返回0
	 */
	@SuppressWarnings("resource")
	public int add_vo(String username,String v_word){
		Connection conn = null;
		java.sql.PreparedStatement stmt1 = null;
		java.sql.PreparedStatement stmt2 = null;
		java.sql.PreparedStatement stmt3 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		int Id;
		try{
			conn = MysqlUtil.getConnection();
			String sql1;
			String sql2;
			String sql3;
			sql1 = "SELECT * FROM user WHERE Username=?";
			stmt1 = conn.prepareStatement(sql1);
			stmt1.setString(1, username);
			rs1 = stmt1.executeQuery();
			
			if(rs1.next()){
				Id = rs1.getInt("Id");
				sql2 = "SELECT * FROM vocabulary WHERE Id=?";
				stmt2 = conn.prepareStatement(sql2);
				stmt2.setInt(1, Id);
				rs2 = stmt2.executeQuery();
				while(rs2.next()){
					if(rs2.getString("V_word").equals(v_word)){
						return 2;
					}
				}				
				sql3 = "INSERT INTO vocabulary (V_word,Id)VALUES(?,?)";
				stmt3 = conn.prepareStatement(sql3);
				stmt3.setString(1, v_word);
				stmt3.setInt(2, Id);						
				int flag = stmt3.executeUpdate();
				if(flag == 0) return 0;
				else return 1;
			}
			else return 0;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			MysqlUtil.close(conn, stmt1,rs1);
			MysqlUtil.close(conn, stmt2,rs2);
			MysqlUtil.close(conn, stmt3);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see ustbTrans.DAO.TransDao#del_vo(java.lang.String, java.lang.String)
	 * 功能：删除生词本中的单词
	 * 返回值：删除成功，返回1；删除失败，返回0
	 */
	public int del_vo(String username,String v_word){
		Connection conn = null;
		java.sql.PreparedStatement stmt1 = null;
		java.sql.PreparedStatement stmt2 = null;
		ResultSet rs = null;
		int Id;
		try{
			conn = MysqlUtil.getConnection();
			String sql1;
			String sql2;
			sql1 = "SELECT * FROM user WHERE Username=?";
			stmt1 = conn.prepareStatement(sql1);
			stmt1.setString(1, username);
			rs = stmt1.executeQuery();
			
			if(rs.next()){
				Id = rs.getInt("Id");			
				sql2 = "DELETE FROM vocabulary WHERE Id=? AND V_word=?";
				stmt2 = conn.prepareStatement(sql2);
				stmt2.setInt(1, Id);
				stmt2.setString(2, v_word);				
						
				int flag = stmt2.executeUpdate();
				if(flag == 0) return 0;
				else return 1;
			}
			else return 0;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			MysqlUtil.close(conn, stmt1,rs);
			MysqlUtil.close(conn, stmt2);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see ustbTrans.DAO.TransDao#get_vo(java.lang.String)
	 * 功能：获取用户所有生词
	 * 返回值：获取成功，返回包含生词本中所有单词的字符串；获取失败，返回空
	 */
	public String[] get_vo(String username){
		Connection conn = null;
		java.sql.PreparedStatement stmt1 = null;
		java.sql.PreparedStatement stmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		int Id;
		int i=0;
		String[] words;
		words = new String[512];
		try{
			conn = MysqlUtil.getConnection();
			String sql1;
			String sql2;
			sql1 = "SELECT * FROM user WHERE Username=?";
			stmt1 = conn.prepareStatement(sql1);
			stmt1.setString(1, username);
			rs1 = stmt1.executeQuery();
			
			if(rs1.next()){
				Id = rs1.getInt("Id");			
				sql2 = "SELECT * FROM vocabulary WHERE Id=?";
				stmt2 = conn.prepareStatement(sql2);
				stmt2.setInt(1, Id);	
				rs2 = stmt2.executeQuery();
				while(rs2.next()){
					words[i] = rs2.getString("V_word");
					i++;
				}
			}
			return words;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			MysqlUtil.close(conn, stmt1,rs1);
			MysqlUtil.close(conn, stmt2,rs2);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see ustbTrans.DAO.TransDao#add_word(java.lang.String, java.lang.String)
	 * 功能：向数据库添加用户划的单词
	 * 返回值:用户以前没有划过该单词，添加成功，返回1；用户划过该单词，添加失败，返回0
	 */
	@SuppressWarnings("resource")
	public int add_word(String username,String w_word){
		Connection conn = null;
		java.sql.PreparedStatement stmt1 = null;
		java.sql.PreparedStatement stmt2 = null;
		java.sql.PreparedStatement stmt3 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		int Id;
		try{
			conn = MysqlUtil.getConnection();
			String sql1;
			String sql2;
			String sql3;
			sql1 = "SELECT * FROM user WHERE Username=?";
			stmt1 = conn.prepareStatement(sql1);
			stmt1.setString(1, username);
			rs1 = stmt1.executeQuery();
			
			if(rs1.next()){
				Id = rs1.getInt("Id");
				System.out.print(Id);
				sql2 = "SELECT * FROM use_wor WHERE Id=?";
				stmt2 = conn.prepareStatement(sql2);
				stmt2.setInt(1, Id);	
				rs2 = stmt2.executeQuery();
				while(rs2.next()){
					if(rs2.getString("W_word").equals(w_word)){
						return 0;
					}
				}
				sql3 = "INSERT INTO use_wor (W_word,Id) VALUES(?,?)";
				stmt3 = conn.prepareStatement(sql3);
				stmt3.setString(1, w_word);
				stmt3.setInt(2, Id);
				int flag = stmt3.executeUpdate();
				if(flag!=0) return 1;
			}
			return 0;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			MysqlUtil.close(conn, stmt1,rs1);
			MysqlUtil.close(conn, stmt2,rs2);
			MysqlUtil.close(conn, stmt3);
		}		
	}
	
	/*
	 * (non-Javadoc)
	 * @see ustbTrans.DAO.TransDao#cal_mark(float, ustbTrans.entity.Mark)
	 * 功能：计算某一条自定义译文的得分
	 * 参数：score-当前用户打分  mark-mark对象
	 * 返回值：计算成功，返回得分；数据库连接失败，计算失败，返回-1
	 */
	public float cal_mark(float score,MARK mark){
		Connection conn = null;
		java.sql.PreparedStatement stmt1 = null;
		java.sql.PreparedStatement stmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		int w_no;
		int times;//从custom表中获取的打分次数
		float exscore;//从custom表中获取的之前的分数
		float result = -1;//计算的最终得分
		try{
			conn = MysqlUtil.getConnection();
			String sql1;
			String sql2;
			sql1 = "SELECT * FROM use_wor WHERE W_word=?";
			stmt1 = conn.prepareStatement(sql1);
			stmt1.setString(1, mark.getW_word());	
			rs1 = stmt1.executeQuery();
			if(rs1.next()){
				w_no = rs1.getInt("W_no");
				sql2 = " SELECT * FROM custom WHERE W_no=? AND Translation=?";
				stmt2 = conn.prepareStatement(sql2);
				stmt2.setInt(1, w_no);
				stmt2.setString(2, mark.getTranslation());
				rs2 = stmt2.executeQuery();
				if(rs2.next()){
					times = rs2.getInt("Times");
					exscore = rs2.getFloat("Score");
					result = (times*exscore + score)/(times+1);
				}				
			}
			return result;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			MysqlUtil.close(conn, stmt1,rs1);
			MysqlUtil.close(conn, stmt2,rs2);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see ustbTrans.DAO.TransDao#update_mark(float, java.lang.String, java.lang.String)
	 * 功能：更新数据库中译文的得分
	 * 参数：score-当前得分  w_word-用户所划单词  trans-用户打分的译文
	 * 返回值：更新成功，返回1；更新失败，返回0
	 */
	public int update_mark(float score,String w_word,String trans){
		Connection conn = null;
		java.sql.PreparedStatement stmt1 = null;
		java.sql.PreparedStatement stmt2 = null;
		java.sql.PreparedStatement stmt3 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		int w_no;
		int times;
		try{
			conn = MysqlUtil.getConnection();
			String sql1;
			String sql2;
			String sql3;
			sql1 = "SELECT * FROM use_wor WHERE W_word=?";
			stmt1 = conn.prepareStatement(sql1);
			stmt1.setString(1, w_word);	
			rs1 = stmt1.executeQuery();
			if(rs1.next()){
				w_no = rs1.getInt("W_no");
				sql2 = "SELECT * FROM custom WHERE W_no=? AND Translation=?";
				stmt2 = conn.prepareStatement(sql2);
				stmt2.setInt(1, w_no);
				stmt2.setString(2, trans);
				rs2 = stmt2.executeQuery();
				if(rs2.next()){
					times = rs2.getInt("Times") + 1;
					sql3 = "UPDATE custom SET Score=?,Times=? WHERE W_no=? AND Translation=?";
					stmt3 = conn.prepareStatement(sql3);
					stmt3.setFloat(1, score);
					stmt3.setInt(3, w_no);
					stmt3.setInt(2, times);
					stmt3.setString(4, trans);
					int flag = stmt3.executeUpdate();
					if(flag!=0){
						return 1;
					}
				}
			}
			return 0;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			MysqlUtil.close(conn, stmt1,rs1);
			MysqlUtil.close(conn, stmt2,rs2);
			MysqlUtil.close(conn, stmt3);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see ustbTrans.DAO.TransDao#add_cus(ustbTrans.entity.Custom)
	 * 功能：添加自定义译文
	 * 返回值：添加成功或该译文已经存在，返回1；添加失败，返回0
	 */
	@SuppressWarnings("resource")
	public int add_cus(Custom cus){
		Connection conn = null;
		java.sql.PreparedStatement stmt1 = null;
		java.sql.PreparedStatement stmt2 = null;
		java.sql.PreparedStatement stmt3 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		int w_no;
		try{
			conn = MysqlUtil.getConnection();
			String sql1;
			String sql2;
			String sql3;
			sql1 = "SELECT * FROM use_wor WHERE W_word=?";
			stmt1 = conn.prepareStatement(sql1);
			stmt1.setString(1, cus.getW_word());
			rs1 = stmt1.executeQuery();
			if(rs1.next()){
				w_no = rs1.getInt("W_no");
				sql2 = "SELECT * FROM custom WHERE W_no=?";
				stmt2 = conn.prepareStatement(sql2);
				stmt2.setInt(1, w_no);
				rs2 = stmt2.executeQuery();
				while(rs2.next()){
					if(rs2.getString("Translation").equals(cus.getTranslation())){
						return 1;
					}
				}				
				sql3 = "INSERT INTO custom (Translation,W_no) VALUES(?,?)";
				stmt3 = conn.prepareStatement(sql3);
				stmt3.setString(1, cus.getTranslation());
				stmt3.setInt(2, w_no);
				int flag = stmt3.executeUpdate();
				if(flag!=0) return 1;
				return 0;
			}
			return 0;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			MysqlUtil.close(conn, stmt1,rs1);
			MysqlUtil.close(conn, stmt2,rs2);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see ustbTrans.DAO.TransDao#get_cus(java.lang.String)
	 * 功能：获取自定义译文以及译文得分
	 * 返回值：获取成功，返回jason数组格式的字符串；获取失败，返回空
	 */
	public String get_cus(String w_word){
		Connection conn = null;
		java.sql.PreparedStatement stmt1 = null;
		java.sql.PreparedStatement stmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		int w_no;
		JSONObject obj = new JSONObject();
		String result = null;
		try{
			conn = MysqlUtil.getConnection();
			String sql1;
			String sql2;
			sql1 = "SELECT * FROM use_wor WHERE W_word=?";
			stmt1 = conn.prepareStatement(sql1);
			stmt1.setString(1, w_word);
			rs1 = stmt1.executeQuery();
			if(rs1.next()){
				w_no = rs1.getInt("W_no");
				sql2 = "SELECT * FROM custom WHERE W_no=? ORDER BY Score DESC";
				stmt2 = conn.prepareStatement(sql2);
				stmt2.setInt(1, w_no);
				rs2 = stmt2.executeQuery();
				while(rs2.next()){
					obj.put(rs2.getString("Translation"), rs2.getFloat("Score"));
				}
				result = obj.toString();
			}
		return result;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			MysqlUtil.close(conn, stmt1,rs1);
			MysqlUtil.close(conn, stmt2,rs2);
		}
	}
	
	
	
	/********************测试用main()函数**********************/

	public static void main(String[] args) {
		Connection conn = null;
		java.sql.PreparedStatement stmt1 = null;
		java.sql.PreparedStatement stmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		int w_no;
		int times;//从custom表中获取的打分次数
		float exscore;//从custom表中获取的之前的分数
		float result = -1;//计算的最终得分
		String word = "Email";
		String trans = "电邮5";
		float score = 3;
		try{
			conn = MysqlUtil.getConnection();
			String sql1;
			String sql2;
			sql1 = "SELECT * FROM use_wor WHERE W_word=?";
			stmt1 = conn.prepareStatement(sql1);
			stmt1.setString(1, word);	
			rs1 = stmt1.executeQuery();
			if(rs1.next()){
				w_no = rs1.getInt("W_no");
				sql2 = " SELECT * FROM custom WHERE W_no=? AND Translation=?";
				stmt2 = conn.prepareStatement(sql2);
				stmt2.setInt(1, w_no);
				stmt2.setString(2, trans);
				rs2 = stmt2.executeQuery();
				if(rs2.next()){
					times = rs2.getInt("Times");
					exscore = rs2.getFloat("Score");
					result = (times*exscore + score)/(times+1);
				}				
			}
			System.out.print(result);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			MysqlUtil.close(conn, stmt1,rs1);
			MysqlUtil.close(conn, stmt2,rs2);
		}
	
	}

}
