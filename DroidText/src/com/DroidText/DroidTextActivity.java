package com.DroidText;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
 
public class DroidTextActivity extends Activity {
    /** Called when the activity is first created. */
    private Socket socket;
    public static PrintWriter output;
    public static BufferedReader input;
    private String host;
    private boolean clicked=false;
    private Button button1;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText hst = (EditText) findViewById(R.id.editText1);
				setHost(hst.getText().toString());
				start();
			}
        	
        });
        //start();
    }
		public void start(){
			  //Log.d("Td", "D");
		       //new ReceiveThread().;
		        	//DatagramSocket socket = new DatagramSocket(8888);
		        	//DatagramPacket pack = new DatagramPacket("hi".getBytes(),"hi".getBytes().length,InetAddress.getByName("192.168.2.6"),9999);
		        //	socket.send(pack);
			
			if(output!=null){
				
					output.close();
			}
			if(input!=null){
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(socket!=null){
				try {
					socket.close();
					try {
						Thread.sleep(500); 
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		       try {
				socket = new Socket(InetAddress.getByName(host),8888);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		       Toast toast = Toast.makeText(this, "Connection Established", 1000);
		       toast.show();
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
		    
		    //   output.println("TEXTR:asdfasdf:dsafdalklsf");
		      new SendThread().start();
		}
        public void setHost(String host){
        	this.host=host;
        }
        
        public void reconnect() throws IOException{
        	socket.close();
        	output.close();
        	input.close();
        	start();
        }
}