package textmessager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GUIBox {
	private String request;
	public static JTextArea log;
	private JTextArea respond;
	private JButton send;
	private static JFrame frame;
	public static JTextArea receive;
	
	public GUIBox(){
		respond = new JTextArea(200,150);
		JPanel east = new JPanel();
		
		log = new JTextArea(300,400);
		receive = new JTextArea(300,150);
		receive.setEditable(false);
		send = new JButton("Send");
		send.setBackground(Color.BLUE);
		respond.setLineWrap(true);
		respond.setEditable(true);
		respond.setWrapStyleWord(true);
		receive.setLineWrap(true);
		receive.setWrapStyleWord(true);
		log.setLineWrap(true);
		log.setWrapStyleWord(true);
		JScrollPane pane = new JScrollPane(respond);
		JScrollPane pane2 = new JScrollPane(receive);
		JScrollPane pane3 = new JScrollPane(log);
	//	pane.add(respond);
		pane.setPreferredSize(new Dimension(200,150));
		pane2.setPreferredSize(new Dimension(300,150));
		pane3.setPreferredSize(new Dimension(300,400));
		JPanel top = new JPanel();
		JPanel bottom = new JPanel(new FlowLayout());
		
		top.add(pane2);
		bottom.add(pane);
		bottom.add(send);
		east.add(pane3);
		JPanel west = new JPanel(new BorderLayout());
		
		frame = new JFrame("DroidText");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(pane3,BorderLayout.EAST);
		west.add(top,BorderLayout.NORTH);
		west.add(bottom,BorderLayout.CENTER);
		frame.add(west,BorderLayout.CENTER);
		frame.setResizable(false);
		send.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//TextMessager.output.println("hi there");
				sendText();
			}
			
		});
	//	frame.pack();
		//frame.setVisible(true);
		respond.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
					sendText();
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	public static void refresh(){
		frame.validate();
		frame.setVisible(false);
		frame.pack();
		frame.setVisible(true);
	}
	public void show(String str){
		request=str;
		String[] data = str.split(":");
		receive.setText("From: "+data[data.length-1]+"\n"+"Message: "+data[1]+"");
		log.setText("From: "+data[data.length-1]+"\n"+"Message: "+data[1]+"\n"+log.getText());
		frame.pack();
		frame.setVisible(true);
		frame.setExtendedState(JFrame.NORMAL);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	} 
	public void sendText(){
		System.out.println(request);
		String[] dat = request.split(":");
			
			TextMessager.output.println("TEXTS:"+dat[0]+":"+respond.getText());
			frame.setState ( JFrame.ICONIFIED );
			respond.setText("");
	}
	
	public static void main(String args[]){
		new GUIBox().show("hello");
	}
}
