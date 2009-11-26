package chat;

import java.util.Vector;

public class TextChatRoom implements Chatroom {

	private Vector<Chatter> Participants; 
	private String Topic;

	public TextChatRoom(String topic) {
		Participants = new Vector<Chatter>();
		Topic = topic;
	}

	public String getTopic() {
		return Topic;
	}

	public void join(Chatter c) {
		System.out.println("(Message de Chatroom : "+Topic+") "+c.getAlias()+" a rejoint la room.");
		Participants.add(c);		
	}

	public void post(String msg, Chatter c) {
		for (Chatter ct : Participants)
		{
			ct.receiveAMessage(msg, c);
		}		
	}

	public void quit(Chatter c) {
		System.out.println("(Message de Chatroom : "+Topic+") "+c.getAlias()+" a quitté la room.");
		Participants.remove(c);
		
		for (Chatter ct : Participants)
		{
			System.out.println(ct.getAlias()+" est encore la!");
		}	
	}
	
	
	
}
