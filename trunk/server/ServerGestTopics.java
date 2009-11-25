package server;

import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

import net.Header;
import net.Message;
import net.TCPServer;


import chat.Chatroom;
import chat.TopicsManager;

public class ServerGestTopics extends TCPServer implements TopicsManager {

	TCPGestTopics concretGT;
	
	public ServerGestTopics() {
		concretGT = new TCPGestTopics();
		TCPGestTopics.nextPort = 1665;
	}
	
	public void gereClient(Socket comm) {
		while(true) {
			try {
				Message m = getMessage();
				System.out.println(m);
				if(m.getHead()==Header.LIST_TOPIC) {
					sendMessage(new Message(Header.LIST_TOPICS_REPLY, listTopics()));
				}
				if(m.getHead()==Header.CREATE_TOPIC) {
					createTopic(m.getData().firstElement());
//					stopServer();
				}
				if(m.getHead()==Header.JOIN_TOPIC) {
					Chatroom CR = joinTopic(m.getData().firstElement());
					int port = ((ServerChatRoom)CR).getPort();
					Vector<String> v = new Vector<String>();
					v.add(Integer.toString(port));
					sendMessage(new Message(Header.JOIN_REPLY, v));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public String createTopic(String topic) {
		return concretGT.createTopic(topic);
	}

	public Chatroom joinTopic(String topic) {
		return concretGT.joinTopic(topic);
	}

	public Vector<String> listTopics() {
		return concretGT.listTopics();
	}

}
