package authentification;

import java.io.IOException;

public class Chat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AuthentificationManager am = new Authentification () ;
		// users management
//		try {
//			am.addUser("bob","123");
//			System.out.println("Bob has been added !");
//			am.removeUser("bob");
//			System.out.println("Bob has been removed !");
//			am.removeUser("bob");
//			System.out.println("Bob has been removes twice !");
//		} catch (UserUnknownException e) {
//			System.out.println(e.login +" : user unknown (enable to remove)!");
//		} catch (UserExistsException e) {
//			System.out.println(e.login +" has already been added !");
//		}
		
		// authentication
		try {
			am.addUser("bob","123");
			System.out.println("Bob has been added !");
			am.addUser("jon","456");
			System.out.println("jon has been added !");
			am.addUser("vong","123");
			System.out.println("vong has been added !");
			am.authentify("bob","123");
			System.out.println("Authentification OK !");
			am.authentify("jon","456");
		} catch (WrongPasswordException e) {
			System.out.println(e.login+" has provided an invalid password !");
		} catch (UserExistsException e) {
			System.out.println(e.login +" has already been added !");
		} catch (UserUnknownException e) {
			System.out.println(e.login +" : user unknown (enable to remove)!");
		}
		
		// persistance
		try {
			am.save("users.txt");
			Authentification am1 = new Authentification();
			am1.load("users.txt");
			am1.authentify("bob","123");
			System.out.println("Loading complete !");
		} catch (UserUnknownException e) {
			System.out.println(e.login +" is unknown ! error during the saving/loading.");
		} catch (WrongPasswordException e) {
			System.out.println(e.login+" has provided an invalid password!error during the saving/loading .");
		} catch (IOException e) {
			System.out.println(e);
		} 
	}
		
}
	

