package ustbTrans.entity;

/*
 * Memory实体
 */

public class Memory {
	private String username;
	private int offsetx;
	private int offsety;
	private String url;
	private String dom;
	private String sequence;
	private String m_word;
	private String trans;
	private int M_no;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getOffsetx() {
		return offsetx;
	}
	public void setOffsetx(int offsetx) {
		this.offsetx = offsetx;
	}
	public int getOffsety() {
		return offsety;
	}
	public void setOffsety(int offsety) {
		this.offsety = offsety;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDom() {
		return dom;
	}
	public void setDom(String dom) {
		this.dom = dom;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getM_word() {
		return m_word;
	}
	public void setM_word(String w_word) {
		this.m_word = w_word;
	}
	public String getTrans() {
		return trans;
	}
	public void setTrans(String trans) {
		this.trans = trans;
	}
	public int getM_no() {
		return M_no;
	}
	public void setM_no(int m_no) {
		M_no = m_no;
	}
	@Override
	public String toString() {
		return "Memory [username=" + username + ", offsetx=" + offsetx
				+ ", offsety=" + offsety + ", url=" + url + ", dom=" + dom
				+ ", sequence=" + sequence + ", m_word=" + m_word + ", trans="
				+ trans + ", M_no=" + M_no + "]";
	}
	
}
