package presenter;
import java.io.IOException;

import net.logicdevelopment.biscotti.Presenter;
import net.logicdevelopment.biscotti.Session;

public class MainPresenter extends Presenter{
	
	public void showView() {
		// TODO Auto-generated method stub
		 views.get("main").show();

	}
	public void ConnectToServer()
	{
		chat.MyConnection connection = new chat.MyConnection();
        Session.set("Connection", connection);
		
	}
	public void initAsyncReader()
	{
		Thread readerThread = new Thread(new IncomingReader());
	}
	public void sendMessageToServer(String message)
	{
		((chat.MyConnection)Session.get("Connection")).writer.println(message);
	}
	public  class IncomingReader implements Runnable
	{
		public void run()
		{
			String message; 
			try {
				while((message=((chat.MyConnection)Session.get("Connection")).reader.readLine())!=null)
				{
				 view.Mainview mview = (view.Mainview)	views.get("main");
				 mview.setIncomingMessage(message);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
