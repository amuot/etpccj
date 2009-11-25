package chat;

import java.util.Vector;

public interface TopicsManager {
	public Vector<String> listTopics ();
	public Chatroom joinTopic (String topic);
	public String createTopic (String topic);
}
