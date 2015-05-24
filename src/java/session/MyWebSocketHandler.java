package session;

import java.util.ArrayList;

import objects.ClientObject;
import objects.HostObject;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import connection.states.EstablishClient;

@WebSocket
public class MyWebSocketHandler {

	public static ArrayList<ClientObject> clients = new ArrayList<ClientObject>();
	public static ArrayList<HostObject> hosts = new ArrayList<HostObject>();
	
	// Specific per connected socket
	private Session session;
	private boolean establishedConnection = false;
	private boolean host; // If socket connected is a host	private String hostID;
	private String hostID;
	
	@OnWebSocketClose
	public void onClose(Session session, int statusCode, String reason) {
			establishedConnection = false;
		}
	
	@OnWebSocketError
	public void onError(Throwable t) {
		System.out.println("Error: " + t.getMessage());
	}

	@OnWebSocketConnect
	public void onConnect(Session session) {
		this.session = session;
		session.setIdleTimeout(5000000);
	}

	@OnWebSocketMessage
	public void onMessage(Session session, String message) {
		if (establishedConnection) {
			if (!host) {
				for (HostObject host : hosts) {
					if (host.hostID.matches(this.hostID)) {
						host.sendMessage(message);
						break;
					}
				}
				
				// Save this message to the clients chat history
				for (ClientObject client : clients) {
					if (client.session == session) {
						client.SaveMessageToChatHistory("C/" + message);
						break;
					}
				}
				
			} else {
				String messageSplit[] = message.split(":");
				sendAlgorithm : {
					for (ClientObject client : clients) {
						if (messageSplit[0].matches(client.clientID)) {
							for (HostObject host : hosts) {
								if (host.hostID.matches(client.hostID + "")) {
									client.sendMessage(host.displayName + ":" + message.split(":")[1]);
									client.SaveMessageToChatHistory("H/" + host.displayName + ":" + message.split(":")[1] );
									break sendAlgorithm;
								}
							}
						}
					}
				}	
			}

		} else {
			String ConnectionResult[] = EstablishClient.establish(session, message).split(":");
			establishedConnection = Boolean.valueOf(ConnectionResult[0]);
			this.host = Boolean.valueOf(ConnectionResult[1]);
			this.hostID = ConnectionResult[2];
		}

	}
	
	

}