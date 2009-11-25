package net;

import java.io.Serializable;
import java.util.Vector;

public class Message implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1063338749736861122L;
	private Header head;
	private Vector<String> data;

	public Message(Header h) {
		head = h;
		data = null;
	}
	public Message(Header h, String s) {
		head = h;
		data = new Vector<String>(1);
		data.add(s);
	}
	public Message(Header h, String s1, String s2) {
		head = h;
		data = new Vector<String>(2);
		data.add(s1);
		data.add(s2);
	}
	public Message(Header h, Vector<String> v) {
		head = h;
		data = new Vector<String>(v);
	}
	
	public Vector<String> getData() {
		return data;
	}

	public Header getHead() {
		return head;
	}

	public String toString() {
		String msg = head.toString();
		if (data != null)
			msg += " " + data.toString();
		return msg;
	/*	switch (head) {
			case DEBUG: return "DEBUG";
			case QUIT_TOPIC: return "QUIT_TOPIC";
			case JOIN_TOPIC: return "JOIN_TOPIC";
			case JOIN_REPLY: return "JOIN_REPLY";
			case CREATE_TOPIC: return "CREATE_TOPIC";
			case LIST_TOPIC: return "LIST_TOPIC";
			case LIST_TOPICS_REPLY: return "LIST_TOPICS_REPLY";
			case JOINCR: return "JOINCR";
			case POST: return "POST";
			case QUITCR: return "QUITCR";
			case RECV_MSG: return "RECV_MSG";
		}
		return null;*/
	}
}
