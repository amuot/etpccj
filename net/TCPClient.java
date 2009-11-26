package net;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient implements MessageConnection {

	protected ObjectOutputStream out;
	protected ObjectInputStream in;	
	
	protected Socket sock;
	
	public Message getMessage() throws IOException {	
		try {
			Message m = (Message) in.readObject(); 
			return  m;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void sendMessage(Message m) throws IOException {
		out.writeObject(m);
		out.flush();
	}
	
	public void setServer(InetAddress adr, int port) {
		try {
			sock = new Socket( adr, port ) ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void connect() {
		try {
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream( new BufferedInputStream(sock.getInputStream())) ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		try {
			sock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
