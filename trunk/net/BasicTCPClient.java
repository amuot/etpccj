package net;

import java.net.InetAddress;
import java.util.Vector;

public class BasicTCPClient extends TCPClient {
	
	public static void main (String[] args) {
		TCPClient cli = new TCPClient();
		try {
			cli.setServer(InetAddress.getLocalHost(), 1664);
			Vector<String> v = new Vector<String>();
			v.add("ping client");
			cli.sendMessage(new Message(Header.JOIN_TOPIC, v));
			System.out.println(cli.getMessage());
//			cli.disconnect();
			cli.sendMessage(new Message(Header.JOIN_REPLY, v));
			System.out.println(cli.getMessage());
			cli.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
