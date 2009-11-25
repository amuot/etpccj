package client;

import java.io.IOException;
import java.net.InetAddress;

import chat.Chatroom;
import chat.Chatter;
import chat.TextChatter;

public class CopyOfTestClient {

	public static void main(String[] args) {
		ClientGestTopics s = new ClientGestTopics();
		Chatter joe = new TextChatter("joe");
		
		try{
			s.setServer(InetAddress.getLocalHost(), 1664);
			s.connect();
			System.out.println("Connexion établie au TopicsManager");
			System.out.println(s.listTopics());
			s.createTopic("pouet");
			Chatroom cr = s.joinTopic("pouet");
			cr.join(joe);
			cr.post("lool", joe);
			System.in.read();
			cr.quit(joe);
////			System.in.read();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
