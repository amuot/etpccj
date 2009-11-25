package server;

import chat.Chatroom;
import chat.TextGestTopics;

public class TCPGestTopics extends TextGestTopics {

	static int nextPort;
	
	public TCPGestTopics() {
//		nextPort = port;
	}
	
	public String createTopic(String topic) {
		if(!Topics.containsKey(topic)) {
			ServerChatRoom SCR = new ServerChatRoom(topic);
			SCR.startServer(nextPort++);
			Topics.put(topic, SCR);
		}
		return null;
	}

	@Override
	public Chatroom joinTopic(String topic) {
		return Topics.get(topic);
	}
}
