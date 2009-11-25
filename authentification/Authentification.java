package authentification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;


public class Authentification implements Serializable, AuthentificationManager {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4719256712313735567L;
	private Hashtable<String, User> users;	
	
	public Authentification() {
		users = new Hashtable<String, User>();
	}

	@Override
	public void addUser(String login, String password)
			throws UserExistsException {
		if(users.containsKey(login)) throw new UserExistsException(login);
		else
			users.put(login, new User(login, password));		
	}

	@Override
	public void authentify(String login, String password)
			throws UserUnknownException, WrongPasswordException {
		if(!users.containsKey(login)) throw new UserUnknownException(login);	
		if(users.get(login).getPassword().compareTo(password)!=0) throw new WrongPasswordException(login);	
	}

	@SuppressWarnings("unchecked")
	public AuthentificationManager load(String path)
		throws IOException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
	    try {
	    	users = (Hashtable<String, User>)ois.readObject();
		    ois.close();
		} catch (ClassNotFoundException e) { e.printStackTrace(); }
		return null;
	}

	public void removeUser(String login)
			throws UserUnknownException {
		if(!users.containsKey(login)) throw new UserUnknownException(login);
		users.remove(login);
	}

	@Override
	public void save(String path) throws IOException {
		try{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
			oos.writeObject(users);
		    oos.close();
		}
		catch (java.io.IOException e) {e.printStackTrace();}
	}
	
	public Hashtable<String, User> showHash() {
		Enumeration<String> e = users.keys();
		while(e.hasMoreElements()) {
			String i = e.nextElement();
			System.out.println(i);
			System.out.println(users.get(i).getPassword());
		}
		return users;
	}

}
