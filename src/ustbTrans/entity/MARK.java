package ustbTrans.entity;


public class MARK {
	private int id;
	private String w_word;
	private String translation;
	private float score;
	private int times;
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
	public String getTranslation() {
		return translation;
	}
	public void setTranslation(String translation) {
		this.translation = translation;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	@Override
	public String toString() {
		return "Mark [id=" + id + ", w_word=" + w_word + ", translation="
				+ translation + ", score=" + score + ", times=" + times + "]";
	}
	
}
