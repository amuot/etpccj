package chat;

public interface Chatter {
	
	public void receiveAMessage(String msg, Chatter c);
	public String getAlias();

}
