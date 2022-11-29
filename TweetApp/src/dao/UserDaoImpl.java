package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import entity.User;
import menu.MainMenu;

public class UserDaoImpl implements UserDao {
	static final String DB_url = "jdbc:mysql://localhost:3306/cogdb";
	static final String USER = "root";
	static final String PASS = "pass@word1";

	Logger logger = Logger.getLogger(UserDaoImpl.class.toString());
	FileHandler fh;

	@Override
	public User Register(User user) {
		try {
			Connection con = DriverManager.getConnection(DB_url, USER, PASS);
			PreparedStatement ps = con.prepareStatement("insert into user values (?,?,?,?,?,?)");
			ps.setInt(1, user.getId());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getFname());
			ps.setString(4, user.getLname());
			ps.setString(5, user.getEmail());
			ps.setString(6, user.getPassword());

			int i = ps.executeUpdate();
			if (i > 0) {
				System.out.println("Registered successfully!!!");
			} else {
				System.out.println("Failed to register...");
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<>();
		try {
			String query = "select username, fname, lname, email from user ";
			Connection con = DriverManager.getConnection(DB_url, USER, PASS);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {

				User u = new User(rs.getString("username"), rs.getString("fname"), rs.getString("lname"),
						rs.getString("email"));
				userList.add(u);
				System.out.println("\nUsername : " + u.getUsername() + "\nFirst name : " + u.getFname()
						+ "\nLast name : " + u.getLname() + "\nEmail : " + u.getEmail());
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public User login(String email, String password) throws IOException {
		User user = new User();
		MainMenu m = new MainMenu();
		try {
			fh = new FileHandler("C:/Users/cogjava4491/Documents/TweetApp/Logs/LogFile.txt");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

			Connection con = DriverManager.getConnection(DB_url, USER, PASS);
			String query = "select * from user where email=? and password=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				logger.info(rs.getString("email") + " logged in user");
				System.out.println("Login success");
			} else {
				System.out.println("Login failed...Please enter correct email and password...");
				m.guestMenu();

			}

			ps.close();
			con.close();
		} catch (

		SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public void forgotPassword(String password, String username, String email) {
		try {
			Connection con = DriverManager.getConnection(DB_url, USER, PASS);
			String query = "update user set password=? where username=? and email=?";
			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, password);
			ps.setString(2, username);
			ps.setString(3, email);

			int i = ps.executeUpdate();
			if (i > 0) {
				System.out.println("Password changed successfully!!");
			} else {
				System.out.println("Failed to reset password...Please enter correct username and emailID");

			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void resetPassword(String username, String password, String newpass) {
		try {

			User u = new User();
			String newpass1 = u.setPassword(newpass);

			Connection con = DriverManager.getConnection(DB_url, USER, PASS);
			String query = "update user set password=? where username=? and password=?";
			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, newpass1);
			ps.setString(2, username);
			ps.setString(3, password);

			int i = ps.executeUpdate();
			if (i > 0) {
				System.out.println("Password changed successfully!!");
			} else {
				System.out.println("Failed to reset password...Please enter correct username and old password");

			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void logout() {
		MainMenu m = new MainMenu();
		try {
			fh = new FileHandler("C:/Users/cogjava4491/Documents/TweetApp/Logs/LogFile.txt");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

			String query = "select email from user ";
			Connection con = DriverManager.getConnection(DB_url, USER, PASS);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			if (rs.next()) {
				logger.info(rs.getString("email") + " logged out successfully");
				m.mainMenu();
			}

		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}
}
