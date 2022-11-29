package entity;

public class User {

	private int id;
	private String username;
	private String fname;
	private String lname;
	private String email;
	private String password;

	public User() {
		super();
	}

	
	public User(int id,String username, String fname, String lname, String email, String password) {
		super();
		this.id=id;
		this.username = username;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public User(String email2, String password2) { // NOPMD by cogjava4491 on 29/11/22, 8:06 AM
		this.email=email2;
		this.password=password2;
	}


	public User(String uname, String fname, String lname, String email) {
		this.username =uname;
		this.fname=fname;
		this.lname=lname;
		this.email=email;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public String setPassword(String password) {
		return this.password = password;
	}


	@Override
	public String toString() {
		return "User [username=" + username + ", fname=" + fname + ", lname=" + lname + ", email=" + email
				+ ", password=" + password + "]";
	}

	
}
