import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;


public class Server {
	ArrayList clientOUtputStreams;
	BufferedReader reader;

	public class ClientHandler implements Runnable {
			BufferedReader isReader;
			Socket sock;
			
			public ClientHandler (Socket clientSocket) {
			
				try {
						sock = clientSocket;
						InputStreamReader isReader = new InputStreamReader(sock.getInputStream()) ;
						reader = new BufferedReader(isReader);
					}catch(Exception ex)  { ex.printStackTrace();}
			 }

			@Override
			public void run() {
				String message;
			}
				try {
						while ((message = reader.readLine()) != null)
						System.out.println( "raad " + message) ;
						tellEveryone(message);
				
			}
			}
	

}
