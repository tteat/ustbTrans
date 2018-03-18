package ustbTrans.entity;


public class User {
	private int id;
	private String password;
	private String username;
	private String mailbox;
	
	public int getid(){
		return id;
	}
	public void setid(int id){
		this.id = id;
	}
	
	public String getusername(){
		return username;
	}
	public void setusername(String username){
		this.username = username;
	}
	
	public String getpassword(){
		return password;
	}
	public void setpassword(String password){
		this.password = password;
	}
	
	public String getmailbox(){
		return mailbox;
	}
	public void setmailbox(String mailbox){
		this.mailbox = mailbox;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", username="
				+ username + ", mailbox=" + mailbox + "]";
	}

}
