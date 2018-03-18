package ustbTrans.entity;

public class Custom {
	private String w_word;
	private int wid;
	private int c_no;
	private String translation;
	
	public String getW_word() {
		return w_word;
	}
	public void setW_word(String w_word) {
		this.w_word = w_word;
	}
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public int getC_no() {
		return c_no;
	}
	public void setC_no(int c_no) {
		this.c_no = c_no;
	}
	public String getTranslation() {
		return translation;
	}
	public void setTranslation(String translation) {
		this.translation = translation;
	}
	@Override
	public String toString() {
		return "Custom [w_word=" + w_word + ", wid=" + wid + ", c_no=" + c_no
				+ ", translation=" + translation + "]";
	}
	
}
