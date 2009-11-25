package chat;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class TextGestTopics implements TopicsManager{

	protected Hashtable<String, Chatroom> Topics;	
	
	public Hashtable<String, Chatroom> getTopics() {
		return Topics;
	}

	public TextGestTopics() {
		Topics = new Hashtable<String, Chatroom>();
	}

	@Override
	public String createTopic(String topic) {
		if(!Topics.containsKey(topic))
			Topics.put(topic, new TextChatRoom(topic));
		return null;
	}

	@Override
	public Chatroom joinTopic(String topic) {
		return Topics.get(topic);
	}

	@Override
	public Vector<String> listTopics() {
		
		Vector<String> ret = new Vector<String>();
		Enumeration<String> e = Topics.keys();
		
		while(e.hasMoreElements()) {		
			ret.add(e.nextElement());
		}
		return ret;
	}

}
