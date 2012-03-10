package com.DroidText;

import java.io.IOException;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract.PhoneLookup;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class ReceiveThread extends BroadcastReceiver {
	/** TAG used for Debug-Logging */
	private static final String LOG_TAG = "SMSReactor";
	private Context context;
	/** The Action fired by the Android-System when a SMS was received.
	 * We are using the Default Package-Visibility */

	private static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";

	public void onReceive(Context context, Intent intent) {
		this.context=context;
		if (intent.getAction().equals(ACTION)) {
			// if(message starts with SMStretcher recognize BYTE)
			StringBuilder sb = new StringBuilder();
			
			/* The SMS-Messages are 'hiding' within the extras of the Intent. */
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				/* Get all messages contained in the Intent
				 * Telephony.Sms.Intents.getMessagesFromIntent(intent) does not work anymore
				 * hence the below changes
				 */
				
				Object[] pduObj = (Object[]) bundle.get("pdus");
				SmsMessage[] messages = new SmsMessage[pduObj.length];
				for(int i=0;i<pduObj.length;i++)
					messages[i]=SmsMessage.createFromPdu((byte[])pduObj[i]);
				/* Feed the StringBuilder with all Messages found. */
				for (SmsMessage currentMessage : messages){
					sb.append("TEXTR:");
					//sb.append("SMS Received From: ");
					/* Sender-Number */
					
					sb.append(currentMessage.getDisplayOriginatingAddress());
					sb.append(":");
					/* Actual Message-Content */
					sb.append(currentMessage.getDisplayMessageBody());
					sb.append(":");
					sb.append(getContactNameFromNumber(currentMessage.getDisplayOriginatingAddress()));
				}
			}
			/* Logger Debug-Output */
			Log.i(LOG_TAG, "[SMSApp] onReceive: " + sb);

			/* Show the Notification containing the Message. */
			//Toast.makeText(context, sb.toString(), Toast.LENGTH_LONG).show();
			/* Start the Main-Activity */
			//Intent i = new Intent(context, DroidTextActivity.class);
			//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		//	context.startActivity(i);
			
				if(DroidTextActivity.output!= null){
				DroidTextActivity.output.println(sb.toString());
				}
			
		}
		
		
	}
	private String getContactNameFromNumber(String number) {
		// define the columns I want the query to return
		Activity a = new Activity();
		String[] projection = new String[] {
				Contacts.Phones.DISPLAY_NAME,
				Contacts.Phones.NUMBER };
 
		// encode the phone number and build the filter URI
		Uri contactUri = Uri.withAppendedPath(Contacts.Phones.CONTENT_FILTER_URL, Uri.encode(number));
 
		// query time
		
		Cursor c = context.getContentResolver().query(contactUri, projection, null,
				null, null);
 
		// if the query returns 1 or more results
		// return the first result
		if (c.moveToFirst()) {
			String name = c.getString(c
					.getColumnIndex(Contacts.Phones.DISPLAY_NAME));
			return name;
		}
 
		// return the original number if no match was found
		return number;
	}
}