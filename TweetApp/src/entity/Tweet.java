package entity;

public class Tweet {

	private int id;
	private String tweetName;
	private String username;

	public Tweet() {
		super();
	}

	public Tweet(String username, String tweetname) {
		this.username = username;
		this.tweetName = tweetname;
	}

	public Tweet(int id2, String tw, String username2) {
		this.id = id2;
		this.tweetName = tw;
		this.username = username2;
	}

	public Tweet(String username) {
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTweetName() {
		return tweetName;
	}

	public void setTweetName(String tweetName) {
		this.tweetName = tweetName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Tweet [id=" + id + ", tweetName=" + tweetName + ", username=" + username + "]";
	}

}
