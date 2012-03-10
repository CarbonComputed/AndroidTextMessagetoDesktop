/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package textmessager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;



/**
 *
 * @author kevin
 */
public class TextMessager {

    private Socket socket;
    public static PrintWriter output;
    public static BufferedReader input;
    public static GUIBox gui;
    private ServerSocket serveSock;
    
    public TextMessager(int port){
    	gui = new GUIBox();
    	try {
			serveSock = new ServerSocket(8888);
			socket=serveSock.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			output= new PrintWriter(socket.getOutputStream(),true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			input= new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Logger logger =null;
        try {
            // Create a file handler that write log record to a file called my.log
            FileHandler handler = new FileHandler("my.log",0,1);

            // Add to the desired logger
            logger = Logger.getLogger("com.TextMessager");
            logger.addHandler(handler);
        } catch (IOException e) {
        }
        System.out.println("Phone found");
        while(true){
            try {
            	
            	String receive =  input.readLine();
            	System.out.println(receive);
            	if(receive!=null){
            		logger.info(receive);
            	HandleRequests.HandleRequests(receive);
            	}
            	else{
            		throw new IOException("NULL!!");
            	}
              //  System.out.println(new String(udt.receive(0).getData()));
            } catch (IOException ex) {
            	try {
            		System.out.println("resetting connection");
            		socket.setReuseAddress(true);
					socket.close();
					serveSock.close();
					output.close();
					input.close();
					new TextMessager(8888);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                Logger.getLogger(TextMessager.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
            
        }
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        new TextMessager(9999);
    }
}
