package com.DroidText;

public class HandleRequests {
		public static void HandleRequest(String request){
			String[] data = request.split(":");
			if(data[0].equals("TEXTS")){
				if(data.length==3){
				SendThread.sendSMS(data[1], data[2]);
				}
				else{
				DroidTextActivity.output.println("Text Failed");
				DroidTextActivity.output.println(request);
				}
			}
		}
}
