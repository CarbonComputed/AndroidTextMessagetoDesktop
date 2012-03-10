package textmessager;

public class HandleRequests {
	
	public static void HandleRequests(String request){
		String[] data = request.split(":");
		String box = GUIBox.log.getText();
		if(request.equals("Password")){
			
		}
		if(request.equals("Text Failed")){
			GUIBox.log.setText("Text Failed"+"\n"+box);
			GUIBox.refresh();
		}
		if(request.equals("Text Sent")){
			GUIBox.log.setText("Text Sent"+"\n"+box);
			GUIBox.refresh();
		}
		if(data[0].equals("TEXTR")){
			
			TextMessager.gui.show(data[1]+":"+data[2]+":"+data[3]);
		}
		
	}
	
	public void addContact(String name,String number){
		for(Contact contact:Globals.contacts){
			if(contact.getNumber().equals(number)){
				
			}
		}
	}
}
