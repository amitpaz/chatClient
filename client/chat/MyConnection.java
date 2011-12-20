
package chat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;


public class MyConnection {
		public Socket sock;
		public BufferedReader reader;
		public PrintStream writer; 
	public MyConnection()
	{
		if(sock==null)
		setUpNetworking();
	}
	private void setUpNetworking() {
		try {
		sock = new Socket("127.0.0.1",5000);
		InputStreamReader streamReader = new InputStreamReader(sock.getInputStream(),"UTF8");
		reader = new BufferedReader(streamReader);
		writer = new PrintStream(sock.getOutputStream(),true,"UTF8");
		}catch(IOException ex)
		{
			ex.printStackTrace();
		}
		
}
}