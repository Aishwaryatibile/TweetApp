package menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import dao.TweetDao;
import dao.TweetDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import entity.Tweet;
import entity.User;

public class MainMenu {

	static UserDao dao = new UserDaoImpl();
	static TweetDao tdao = new TweetDaoImpl();

	public static void mainMenu() throws IOException {
		Scanner scan = new Scanner(System.in);
		System.out.println("************************************");
		System.out.println("         Welcome to Tweet App       ");
		System.out.println("************************************");
		int selectedOption = 0;
		System.out.println("Please login to the Application first");
		System.out.println("");
		System.out.println("--------------------------------------");
		System.out.println("          1. Main Menu                ");
		System.out.println("---------------------------------------");
		System.out.print("\nEnter Option: ");

		selectedOption = scan.nextInt();
		switch (selectedOption) {
		case 1:
			System.out.println("\n");
			guestMenu();
			break;

		case 2:
			System.out.println("Exiting the application...");
			System.exit(0);

		default:
			System.out.println("Error! Please input only the number options available above!!!");
			System.out.println("\n\n");
			mainMenu();
		}
	}

	public static void guestMenu() throws IOException {
		Scanner scan = new Scanner(System.in);
		System.out.println("************************************");
		System.out.println("         Guest User Menu            ");
		System.out.println("************************************");
		int selectedOption = 0;
		System.out.println();
		System.out.println("Already registered guest user?");
		System.out.println("--------------------------------------");
		System.out.println("1. Yes");
		System.out.println("2. No");
		System.out.println("3. Forgot password");
		System.out.println("4. Return to Main menu");
		System.out.println("5. Exit");
		System.out.println("--------------------------------------");
		System.out.println();
		System.out.print("\nEnter Option: "); // NOPMD by cogjava4491 on 29/11/22, 8:23 AM

		selectedOption = scan.nextInt();
		switch (selectedOption) {
		case 1:
			System.out.println("Enter Email : ");
			String em = scan.next();
			System.out.println("Enter Password : ");
			String pass = scan.next();
			dao.login(em, pass);
			userMenu();
			break;

		case 2:
			System.out.println("\n");
			System.out.println("Enter Id : ");
			int id = scan.nextInt();
			System.out.println("Enter Username : ");
			String username = scan.next();
			System.out.println("Enter First Name : ");
			String fname = scan.next();
			System.out.println("Enter last Name : ");
			String lname = scan.next();
			System.out.println("Enter email: ");
			String email = scan.next();
			System.out.println("Enter password : ");
			String password = scan.next();
			User u1 = new User(id,username, fname, lname, email, password);
			dao.Register(u1);
			System.out.println("---------------------------------------");
			userMenu();
			break;

		case 3:
			System.out.println("\n");
			System.out.println("Enter Username : ");
			String username1 = scan.next();
			System.out.println("Enter email : ");
			String email1 = scan.next();
			System.out.println("Enter new password : ");
			String newpass = scan.next();
			dao.forgotPassword(newpass, username1, email1);
			mainMenu();
			break;

		case 4:
			System.out.println("\n");
			mainMenu();
			break;

		case 5:
			System.out.println("Exiting the application...");
			System.exit(0);

		default:
			System.out.println("Error! Please input only the number options available above!!!");
			System.out.println("\n\n");
			guestMenu();
		}
	}

	public static void userMenu() throws IOException {
		Scanner scan = new Scanner(System.in);
		System.out.println("************************************");
		System.out.println("          User Panel                ");
		System.out.println("************************************");
		int selectedOption = 0;
		System.out.println("--------------------------------------");
		System.out.println("          1. Post a tweet               ");
		System.out.println("          2. Search tweet by username   ");
		System.out.println("          3. View all tweets            ");
		System.out.println("          4. View all users             ");
		System.out.println("          5. Reset password              ");
		System.out.println("          6. Log Out                 ");
		System.out.println("          7. Exit                    ");
		System.out.println("");
		System.out.println("---------------------------------------");
		System.out.print("\nEnter Option: ");

		selectedOption = scan.nextInt();
		switch (selectedOption) {
		case 1:
			System.out.println("\n");
			System.out.println("--------------------------------------");
			System.out.println("            Post a tweet              ");
			System.out.println("--------------------------------------");

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter ID : ");
			int id = scan.nextInt();
			System.out.println("Enter username : ");
			String username = scan.next();
			System.out.println("Enter your tweet : ");
			String tw = br.readLine();

			Tweet t1 = new Tweet(id, tw, username);
			tdao.postTweet(t1);
			System.out.println("--------------------------------------");
			System.out.println();
			userMenu();
			break;

		case 2:
			System.out.println("\n");
			System.out.println("--------------------------------------");
			System.out.println("          Search tweet by username     ");
			System.out.println("--------------------------------------");
			System.out.println("Enter username : ");
			String username1 = scan.next();

			tdao.getAllTweetsByUsername(username1);
			userMenu();
			System.out.println("--------------------------------------");
			System.out.println();
			break;

		case 3:
			System.out.println("\n");
			System.out.println("--------------------------------------");
			System.out.println("           View all tweets            ");
			System.out.println("--------------------------------------");
			tdao.getAllTweets();
			userMenu();
			System.out.println("--------------------------------------");
			System.out.println();
			break;

		case 4:
			System.out.println("\n");
			System.out.println("--------------------------------------");
			System.out.println("          View all Users                  ");
			System.out.println("--------------------------------------");
			dao.getAllUsers();
			userMenu();
			System.out.println();
			System.out.println("--------------------------------------");
			break;

		case 5:
			System.out.println("\n");
			System.out.println("--------------------------------------");
			System.out.println("            Reset Password            ");
			System.out.println("--------------------------------------");
			System.out.println("Enter username : ");
			String username2 = scan.next();
			System.out.println("Enter old password : ");
			String password = scan.next();
			System.out.println("Enter new password : ");
			String newpass = scan.next();
			dao.resetPassword(username2, password, newpass);
			userMenu();
			System.out.println();
			System.out.println("--------------------------------------");
			break;

		case 6:
			System.out.println("\n");
			dao.logout();

			break;

		case 7:
			System.out.println("Exiting the application...");
			System.exit(0);

		default:
			System.out.println("Error! Please input only the number options available above!!!");
			System.out.println("\n\n");
			userMenu();
		}

	}

	public static void main(String[] args) throws IOException {
		mainMenu();
	}
}
