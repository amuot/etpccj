package authentification;

import java.io.Serializable;

public class User implements Serializable, Comparable<User> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1935581211235520991L;
	private String login;
	private String password;
	
	@Override
	public int compareTo(User arg0) {
		if(this.login==arg0.getLogin())
			return 1;   //1 si c'est pareil
		else
			return 0;
	}

	public User(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

}
