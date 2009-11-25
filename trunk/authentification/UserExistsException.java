package authentification;

@SuppressWarnings("serial")
public class UserExistsException extends AuthentificationException {

	public UserExistsException(String string) {
		super(string);
	}
	
}
