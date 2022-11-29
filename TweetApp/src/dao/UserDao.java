package dao;
import java.io.IOException;
import java.util.List;

import entity.User;

public interface UserDao {
		
	public User Register (User user); // NOPMD by cogjava4491 on 29/11/22, 8:14 AM
	public List<User> getAllUsers();
	public User login(String email, String password) throws IOException;
	public void forgotPassword(String password,String username, String email);
	void resetPassword(String username, String password, String newpass);
	public void logout();

}
