package textmessager;

import java.util.ArrayList;

public class Contact {

	private String number;
	private String name;
	private ArrayList<String> messages;
	
	public Contact(String number,String name){
		this.number=number;
		this.name=name;
		messages = new ArrayList<String>();
	}
	public void addMessage(String message){
		messages.add(message);
	}
	
	public String getNumber(){
		return number;
	}
	
	public String getName(){
		return name;
	}
	
	public ArrayList<String> getMessages(){
		return messages;
	}
}
