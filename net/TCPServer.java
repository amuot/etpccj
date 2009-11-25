package net;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class TCPServer implements MessageConnection, Runnable, Cloneable {

	protected Socket commSocket;
	protected ServerSocket waitSocket;

	protected ObjectOutputStream out;
	protected ObjectInputStream in;	

	protected Thread thread;
	
	protected boolean doRun;
	protected boolean treatClient;
	
	@Override
	public void run() {

		if(treatClient) {
			try {
				in = new ObjectInputStream(new BufferedInputStream(commSocket.getInputStream()));
				out = new ObjectOutputStream(commSocket.getOutputStream());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			gereClient(commSocket);
		}
		else {
			TCPServer myClone;
			while (doRun) {
				try {
					commSocket = waitSocket.accept() ;
					myClone = (TCPServer)clone();
					myClone.treatClient = true;
					new Thread(myClone).start();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void startServer(int port) {
		try {
			treatClient = false;
			waitSocket = new ServerSocket( port ) ;
			thread = new Thread(this);
			thread.start();
			doRun = true;
		} catch(IOException e) { 
			System.err.println(e);
			e.printStackTrace(); 
		}
	}
	
	@SuppressWarnings("deprecation")
	public void stopServer() {
		try {
			waitSocket.close() ;
			doRun = false;	
			this.thread.stop();
//			out.close();
		} catch(IOException e) { e.printStackTrace(); }
	}
	
	public int getPort() {
		return waitSocket.getLocalPort();
	}
	
	public abstract void gereClient(Socket comm);
	
	@Override
	public Message getMessage() throws IOException {
//		in = new ObjectInputStream(commSocket.getInputStream());
		try {
			Message m = (Message) in.readObject();
			return  m;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void sendMessage(Message m) throws IOException {
//		out = new ObjectOutputStream(commSocket.getOutputStream());
		out.writeObject(m);
		out.flush();
		
//		out.close();
	}

}
