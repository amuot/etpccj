package server;

public class TestServer {

	public static void main(String[] args) {
		ServerGestTopics s = new ServerGestTopics();
		s.createTopic("Accueil");
		
		try {
			s.startServer(1664);
			Thread.sleep(10);
			//s.stopServer();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
