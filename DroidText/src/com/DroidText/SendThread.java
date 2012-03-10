package com.DroidText;

import java.io.IOException;

import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;

public class SendThread extends Thread{
	public SendThread(){
		
	}
	public void run(){
		getRequest();
	}
	public void getRequest(){
		Log.d("test", "Tst");
		while(true){
			try {
				String req = new String(DroidTextActivity.input.readLine());
				if(req!=null){
				HandleRequests.HandleRequest(req);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void sendSMS(String number,String text){
		SmsManager sm = SmsManager.getDefault();
		// here is where the destination of the text should go
	  
		sm.sendTextMessage(number, null, text, null, null);
		DroidTextActivity.output.println("Text Sent");
	}
}
