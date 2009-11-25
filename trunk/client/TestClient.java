package client;

import java.io.IOException;
import java.net.InetAddress;

import chat.Chatroom;
import chat.Chatter;
import chat.TextChatter;

public class TestClient {

	public static void main(String[] args) {
		ClientGestTopics s = new ClientGestTopics();
		Chatter bob = new TextChatter ("bob");
		Chatter joe = new TextChatter("joe");
		Chatter jon = new TextChatter("jon");
		
		try{
			s.setServer(InetAddress.getLocalHost(), 1664);
			s.connect();
			System.out.println("Connexion établie au TopicsManager");
			System.out.println(s.listTopics());
			
			s.createTopic("pouet");
			System.out.println(s.listTopics());
			Chatroom cr = s.joinTopic("pouet");
			cr.join(jon);
			Chatroom cr2 = s.joinTopic("pouet");
			cr2.join(bob);
//			Chatroom cr3 = s.joinTopic("pouet");
//			cr3.join(joe);
//			
//			cr.post("coucou", jon);
			cr.post("j'en ai marre", bob);
			System.in.read();
			cr.post("la", bob);
			System.in.read();
			cr.post("lo", bob);
			System.in.read();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
