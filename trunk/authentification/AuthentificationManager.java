package authentification;

import java.io.IOException;

public interface AuthentificationManager {
	public void addUser(String login, String password) throws UserExistsException;
	public void removeUser(String login) throws UserUnknownException;
	public void authentify(String login, String password) throws UserUnknownException, WrongPasswordException;
	public AuthentificationManager load(String path) throws IOException;
	public void save(String path) throws IOException;
}
