package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import presenter.MainPresenter;

import net.logicdevelopment.biscotti.View;

public class Mainview extends JPanel implements View,ActionListener{
	JButton sendButton;
	JTextField outgoing;
	JTextArea incoming;
    JFrame displayFrame;
    MainPresenter presenter;
 
    public  Mainview(MainPresenter presenter)
    {
    	this.presenter = presenter;
    }

	public void show()
	{
		outgoing = new JTextField(20);
		sendButton = new JButton("send");
		incoming = new JTextArea(15,50);
		incoming.setLineWrap(true);
		incoming.setWrapStyleWord(true) ;
		incoming.setEditable(false);
		JScrollPane qScroller = new JScrollPane(incoming) ;
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS) ;
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(incoming);
		add(outgoing);
		add(sendButton);
		add(qScroller);
		sendButton.addActionListener(this);
	    displayFrame = new JFrame("main Form");
	    displayFrame.getContentPane().add(this, BorderLayout.CENTER);
        displayFrame.setSize(new Dimension(600, 130));
        displayFrame.setVisible(true);
	}
	public void setIncomingMessage(String message)
	{
		incoming.append(message);
	}
	@Override
	public void close() {
		// TODO Auto-generated method stub
		displayFrame.setVisible(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		presenter.submit();
		presenter.sendMessageToServer(outgoing.getText());
	}

}
