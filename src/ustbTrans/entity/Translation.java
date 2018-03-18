package ustbTrans.entity;

public class Translation {
	private String w_word;
	private String API_trans;
	public String getW_word() {
		return w_word;
	}
	public void setW_word(String w_word) {
		this.w_word = w_word;
	}
	public String getAPI_trans() {
		return API_trans;
	}
	public void setAPI_trans(String aPI_trans) {
		API_trans = aPI_trans;
	}
	@Override
	public String toString() {
		return "Translation [w_word=" + w_word + ", API_trans=" + API_trans
				+ "]";
	}
	
}
