package authentification;

public class AuthentificationException extends Exception {
	
	public AuthentificationException(String string) {
		this.login = string;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String login;
	
}
