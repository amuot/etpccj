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
		Chatter bouba = new TextChatter("bouba");
		
		try{
			s.setServer(InetAddress.getLocalHost(), 1664);
			s.connect();
			System.out.println("Connexion établie au TopicsManager");
			s.createTopic("pouet");
			System.out.println(s.listTopics());
			Chatroom cr = s.joinTopic("pouet");
			cr.join(joe);
//			
//			Chatroom cr2 = s.joinTopic("pouet");
//			cr2.join(bouba);
			
			cr.post("lool", joe);
			System.in.read();
			cr.quit(joe);
//			System.in.read();
//			cr2.quit(bouba);
			System.in.read();
			System.in.read();
			s.disconnect();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
