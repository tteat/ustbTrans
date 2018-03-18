package ustbTrans.entity;
/**
 * ����
 * @author Administrator
 */
public class Word {
	private int w_no;
	private int id;
	private String w_word;
	
	public int getW_no() {
		return w_no;
	}
	public void setW_no(int wid) {
		this.w_no = wid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getW_word() {
		return w_word;
	}
	public void setW_word(String w_word) {
		this.w_word = w_word;
	}
	@Override
	public String toString() {
		return "Word [wid=" + w_no + ", id=" + id + ", w_word=" + w_word + "]";
	}
	
}
