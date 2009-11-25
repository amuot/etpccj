package client;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Hashtable;
import java.util.Vector;

import net.Header;
import net.Message;
import net.TCPClient;

import chat.Chatroom;
import chat.TopicsManager;

public class ClientGestTopics extends TCPClient implements TopicsManager {

	Hashtable<String, Chatroom> Topics;	
	
	public ClientGestTopics() {
		Topics = new Hashtable<String, Chatroom>();
	}

	public String createTopic(String topic) {
		try {
			Vector<String> v = new Vector<String>();
			v.add(topic);
			sendMessage(new Message(Header.CREATE_TOPIC, v));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Chatroom joinTopic(String topic) {
		ClientChatRoom CRproxy = null;
		try {
			Vector<String> v = new Vector<String>();
			v.add(topic);
			sendMessage(new Message(Header.JOIN_TOPIC, v));
			Message m = getMessage();
			CRproxy = new ClientChatRoom();
			CRproxy.setServer(InetAddress.getLocalHost(),Integer.valueOf(m.getData().firstElement()));
			CRproxy.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return CRproxy;
	}

	public Vector<String> listTopics() {
		
		try {
			sendMessage(new Message(Header.LIST_TOPIC));
			Message m = getMessage();
			return m.getData();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
