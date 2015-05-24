package objects;


import java.util.ArrayList;
import java.util.Collections;



import org.eclipse.jetty.websocket.api.Session;





import dataLogicLayer.DataURLs;
import session.MyWebSocketHandler;

public class ClientObject {
	
	public Session session;
	public String clientID;
	public int hostID; // Host this client belongs to
	public int port;
	
	public String siteID; // Site ID this client belongs to
	public String siteMainURL; // Site full name this client belongs to (ex. www.something.com)

	public ArrayList<String> chatHistory; //Stores all chat history from host and client.
	
	public ClientObject(Session session) {
		this.session = session;
	}
	
	public void sendMessage(String message) {
		try { 
			session.getRemote().sendString(message);
		}
		catch(Exception ex) {
			// Log exception
		}		
	}
	
	//Store chat history for the client for use in database and cross browser chat box
	public void SaveMessageToChatHistory(String message) {
		if (chatHistory == null) {
		chatHistory = new ArrayList<String>();
		}
		chatHistory.add(message);
	}
	
	
	public String getConInfo() {
		// String of connection info for host. USED IN initiate.
		// For Sending array of data to HOST.
		//ARRAY INFO: CLIENT ID = 1, FULL URL = 2
		return this.clientID + ":" + this.siteMainURL + ":" + this.siteID;
	}
	
	public boolean initiateClient(String Message) {
		// Generate clients unique ID and Send client establish message
		this.clientID = new Integer(session.getRemote().hashCode()).toString();
		sendMessage("CONNECTION INFORMATION:" + clientID); 
			
		//Set up the clients siteID (Website the client it from)
		String clientIDArray[] = Message.split(":");
		this.siteID =  clientIDArray[2];
		this.siteMainURL = DataURLs.getSiteNameByID(this.siteID);
	    
        Collections.sort(MyWebSocketHandler.hosts); // sorts host, lowest client connections to top
		this.hostID = Integer.parseInt(MyWebSocketHandler.hosts.get(0).hostID);
		
		for (HostObject host : MyWebSocketHandler.hosts) {
			String clientBrowserURL[] = Message.split("::");
			
			if (host.hostID.matches(this.hostID + "")){
				host.connectedClients.add(this);
				host.sendMessage("CONNECTION INFORMATION:" + getConInfo() + "::" + clientBrowserURL[1]);
				break;
			}
		}
		
		MyWebSocketHandler.clients.add(this);
		return true;
	}
	
	public boolean initiateExistingClient(ClientObject oldClient, String clientID, String message,  boolean found) {
		this.clientID = clientID;
		this.siteID = oldClient.siteID;
		
		// Transfer chat history
		chatHistory = new ArrayList<String>();
		this.chatHistory = oldClient.chatHistory;
		
		// Make sure host client is connected to is still connected
		boolean hostConnected = false;
		for (HostObject host : MyWebSocketHandler.hosts) {
			if (host.hostID.matches(oldClient.hostID + "")){
				String ClientsBrowserURL[] = message.split("::"); 
				this.hostID = oldClient.hostID;
				this.siteMainURL = oldClient.siteMainURL;
				host.sendMessage("REESTABLISH:"  + getConInfo() + "::" + ClientsBrowserURL[1]);
				hostConnected = true;
				break;
			}
			
		}
		
		if (!hostConnected) {
	        Collections.sort(MyWebSocketHandler.hosts); // sorts host, lowest client connections to top
			this.hostID = Integer.parseInt(MyWebSocketHandler.hosts.get(0).hostID); // setup the host for this client
			this.siteID = message.split(":")[3];
			this.siteMainURL = DataURLs.getSiteNameByID(this.siteID);
			
				for (HostObject host : MyWebSocketHandler.hosts) {
					if (host.hostID.matches(this.hostID  + "")){
						host.connectedClients.add(this);
						String clientBrowserURL[] = message.split("::");
						host.sendMessage("CONNECTION INFORMATION:" + getConInfo() + "::" + clientBrowserURL[1]);
						break;
					}
			} 
			
		}
			
		// Build chat history string and send to current client
		String chatHist = "";
		for (String ch : this.chatHistory ) {
			chatHist = chatHist + ch + "%:%";
		}
		if (chatHist != "") { 
				sendMessage("!HISTORY!:%:%" + chatHist);
		}
		
		MyWebSocketHandler.clients.add(this);
		return true;
	}
	
	
	
	
	
}
