package dao;
import java.util.List;
import entity.Tweet;

public interface TweetDao {
	
	public Tweet postTweet(Tweet tw); // NOPMD by cogjava4491 on 29/11/22, 7:43 AM
	public List<Tweet> getAllTweets();
 	public List<Tweet> getAllTweetsByUsername(String username); // NOPMD by cogjava4491 on 29/11/22, 7:45 AM

}
