package client;

import java.io.IOException;
import java.util.Vector;

import net.Header;
import net.Message;
import net.TCPClient;
import chat.Chatroom;
import chat.Chatter;

public class ClientChatRoom extends TCPClient implements Chatroom, Runnable {

	private Chatter leChatter;
	private String topic;
	private boolean doRun;
	private Thread th;
	
	public ClientChatRoom() {
		
	}
	
	@Override
	public String getTopic() {
		return topic;
	}

	@Override
	public void join(Chatter c) {
		leChatter = c;
		try {
			Vector<String> v = new Vector<String>();
			v.add(c.getAlias());
			sendMessage(new Message(Header.JOINCR, v));
			doRun = true;
			th = new Thread(this);
			th.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void post(String msg, Chatter c) {
		try {
			Vector<String> v = new Vector<String>();
			v.add(msg);
			v.add(c.getAlias());
			sendMessage(new Message(Header.POST, v));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void quit(Chatter c) {
		try {
			sendMessage(new Message(Header.QUITCR, c.getAlias()));
			doRun = false;
			Thread.currentThread().interrupt();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(doRun) {
			try {
				System.out.println("en écoute");
				Message m = getMessage();
				System.out.println(m);
				if(m.getHead()==Header.RECV_MSG) {
					leChatter.receiveAMessage(m.getData().firstElement(), null);
				}
				if(m.getHead()==Header.QUITCR){
					doRun = false;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
