package chat;

public class TextChatter implements Chatter {

	private String Alias;
	
	public TextChatter(String pseudo) {
		Alias = pseudo;
	}

	public String getAlias() {
		return Alias;
	}

	public void receiveAMessage(String msg, Chatter c) {
		
	}
}
