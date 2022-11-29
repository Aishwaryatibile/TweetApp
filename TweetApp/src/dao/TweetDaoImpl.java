package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Tweet;
import entity.User;

public class TweetDaoImpl implements TweetDao {
	static final String DB_url = "jdbc:mysql://localhost:3306/cogdb";
	static final String USER = "root";
	static final String PASS = "pass@word1";
	List<Tweet> tweetList = new ArrayList<>();
	User user = new User();
	Tweet tweet = new Tweet();

	@Override
	public Tweet postTweet(Tweet tweet) {
		try {
			Connection con = DriverManager.getConnection(DB_url, USER, PASS);
			PreparedStatement ps = con.prepareStatement("insert into tweet values (?,?,?) ");

			ps.setInt(1, tweet.getId());
			ps.setString(2, tweet.getTweetName());
			ps.setObject(3, tweet.getUsername());

			int i = ps.executeUpdate();
			if (i > 0) {
				System.out.println("Tweet posted successfully!!!");
			} else {
				System.out.println("Failed to post...");
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public List<Tweet> getAllTweets() {
		try {
			String query = "select username,tweetname from Tweet ";
			Connection con = DriverManager.getConnection(DB_url, USER, PASS);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				Tweet t = new Tweet(rs.getString("username"), rs.getString("tweetname"));
				tweetList.add(t);
				System.out.println("\nUsername : " + t.getUsername() + "\nTweet : " + t.getTweetName());

				System.out.println("\n");

			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tweetList;
	}

	@Override
	public List<Tweet> getAllTweetsByUsername(String username) {
		Tweet tweet = null;

		try {
			Connection con = DriverManager.getConnection(DB_url, USER, PASS);

			PreparedStatement ps = con.prepareStatement("select username,tweetname from Tweet where username=?");
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				tweet = new Tweet(rs.getString("username"), rs.getString("tweetname"));
				tweetList.add(tweet);
				System.out.println("\nUsername : " + tweet.getUsername() + "\nTweet : " + tweet.getTweetName());

				System.out.println("\n");

			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tweetList;
	}

}
