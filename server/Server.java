import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;


public class Server {
	ArrayList<PrintStream> clientOutputStreams;
	BufferedReader reader;
public static void main(String[] args)
{
	Server server = new Server();
	server.go();
}
	public class ClientHandler implements Runnable {
			BufferedReader isReader;
			Socket sock;
			
			public ClientHandler (Socket clientSocket) {
				try{
						sock = clientSocket;
						InputStreamReader isReader = new InputStreamReader(sock.getInputStream(),"UTF8") ;
						reader = new BufferedReader(isReader);
				
					}catch(Exception ex)  { ex.printStackTrace();}
			 }

			@Override
			public void run() {
				String message;
			
				try {
						while ((message = reader.readLine()) != null){
						System.out.println( "read " + message) ;
						tellEveryone(message);
						}
				} catch(Exception ex)
				{
					ex .printStackTrace();
				}
			
				
			}
			}
	public void tellEveryone(String message)
	{
		Iterator it = clientOutputStreams.iterator();
		while(it.hasNext())
		{
			PrintStream writer = (PrintStream) it.next();
			writer.println(message);
			writer.flush() ;
		}
	}
	public void go()
	{
		clientOutputStreams = new ArrayList<PrintStream>();
		try {
			ServerSocket serverSock = new ServerSocket(5000);
			while(true)
			{
				Socket clientSocket = serverSock.accept();
				System.out.println("Connected...");
				PrintStream writer = new PrintStream(clientSocket.getOutputStream(),true,"UTF8");
				clientOutputStreams.add(writer);
				Thread t = new Thread(new ClientHandler(clientSocket));
				t.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
