package server;

import java.io.IOException;
import java.net.Socket;

import net.Header;
import net.Message;
import net.TCPServer;

import chat.Chatroom;
import chat.Chatter;
import chat.TextChatRoom;

public class ServerChatRoom extends TCPServer implements Chatter, Chatroom {

	private TextChatRoom concretCR;
	private String pseudo;
	boolean doRun = true;
	
	public ServerChatRoom(String topic) {
		concretCR = new TextChatRoom(topic);
	}
	
	public void gereClient(Socket comm) {
		while(doRun) {
			try {
				Message m = getMessage();
				System.out.println(m);
				if(m.getHead()==Header.JOINCR) {
					pseudo = m.getData().firstElement();
					join(this);
				}
				if(m.getHead()==Header.POST) {
					post(m.getData().firstElement(), this);
				}
				if(m.getHead()==Header.QUITCR) {
					quit(this);
					sendMessage(new Message(Header.QUITCR));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getAlias() {
		return pseudo;
	}

	public void receiveAMessage(String msg, Chatter c) {
		try {
			sendMessage(new Message(Header.RECV_MSG, msg, c.getAlias()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getTopic() {
		return concretCR.getTopic();
	}

	public void join(Chatter c) {
		concretCR.join(c);

	}

	public void post(String msg, Chatter c) {
		concretCR.post(msg, c);
	}

	public void quit(Chatter c) {
		concretCR.quit(c);
	}

}
