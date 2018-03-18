package ustbTrans.entity;

import java.util.Arrays;

public class Wordbook {
	private int id;
	private String[] wordlist;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String[] getWordlist() {
		return wordlist;
	}
	public void setWordlist(String[] wordlist) {
		this.wordlist = wordlist;
	}
	@Override
	public String toString() {
		return "Wordbook [id=" + id + ", wordlist=" + Arrays.toString(wordlist)
				+ "]";
	}
	

}
