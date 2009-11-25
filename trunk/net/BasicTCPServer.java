package net;

import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

public class BasicTCPServer extends TCPServer {

	@Override
	public void gereClient(Socket comm) {
		try {
			boolean test = true;
			while(test) {
				Message m = getMessage();
				System.out.println(m);
				
				Vector<String> v = new Vector<String>();
				v.add("pong server");
				if(m.getHead() == Header.JOIN_TOPIC) {
					sendMessage(new Message(Header.JOIN_REPLY, v));
					System.out.println("Server finished handling client, quitting");
				}
				else {
					sendMessage(new Message(Header.JOIN_REPLY, v));
					System.out.println("Server finished handling client, quitting");
					stopServer();
					test = false;
				}
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static void main(String[] args) {
		BasicTCPServer serv = new BasicTCPServer();
		
		try {
			serv.startServer(1664);
			System.out.println("1664");
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
