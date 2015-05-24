package objects;

import java.util.ArrayList;

import org.eclipse.jetty.websocket.api.Session;

import session.MyWebSocketHandler;

public class HostObject implements Comparable<HostObject> {
	
	public Session session;
	public int port;

	public String hostID;
	public String displayName;

	public ArrayList<String> chatHistory; //Stores all chat history from host and client.
	public ArrayList<ClientObject> connectedClients; //Stores all client IDs tied to this host.

	public boolean establishedConnection = false;
	
	public HostObject(Session session) {
	this.session = session;
	}
	
	//Store chat history for the client for use in database and cross browser chat box
	public void SaveMessageToChatHistory(String message) {
		if (chatHistory == null) {
		chatHistory = new ArrayList<String>();
		}
		chatHistory.add(message);
	}
	
	public boolean initiateHost(String message) {
		// Generate clients unique ID and Send client establish message
		hostID = new Integer(session.getRemote().hashCode()).toString();
		connectedClients = new ArrayList<ClientObject>();
		sendMessage("CONNECTION INFORMATION:" + hostID + "::" + "None"); 
		MyWebSocketHandler.hosts.add(this);
		
		displayName =  message.split(":")[2];
		
		return true;
	}
	
	public void sendMessage(String message) {
		try { 
			session.getRemote().sendString(message);
		}
		catch(Exception ex) {
			// Log exception
		}		
	}

	// Orders host in array for the connection algor
	@Override
	public int compareTo(HostObject p) {
	      if(this.connectedClients.size() > p.connectedClients.size()) return 1;
	         if(this.connectedClients.size() < p.connectedClients.size()) return -1;
	         return 0;
	}
	
}
