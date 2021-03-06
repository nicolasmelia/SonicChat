package session;

import java.util.ArrayList;
import java.util.Iterator;

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
	private boolean host; // If socket connected is a host private String hostID;
	private String hostID;
	
	@OnWebSocketClose
	public void onClose(Session session, int statusCode, String reason) {
			establishedConnection = false;
			if (host) {
				for (Iterator<HostObject> iterator = hosts.iterator(); iterator.hasNext(); ) {
					HostObject host = iterator.next();
					if (host.session == this.session) {
						// Remove the disconnected host clients 		
						for (Iterator<ClientObject> iterator2 = clients.iterator(); iterator2.hasNext(); ) {
							ClientObject client = iterator2.next();
							if (client.hostID == Integer.parseInt(host.hostID)) {
								client.sendMessage("OOPS!" + ":" + "There was an error. Refresh this page to reconnect.");
								//MyWebSocketHandler.clients.remove(client);
								iterator2.remove();
							}
						}
						// Remove the host
						//MyWebSocketHandler.hosts.remove(host);
						iterator.remove();
						break;
					}
				}
			} else {
				// Nothing for now... May be a fail safe option soon...			
			}
			
		}
	
	@OnWebSocketError
	public void onError(Throwable t) {
		System.out.println("Error: " + t.getMessage());
	}

	@OnWebSocketConnect
	public void onConnect(Session session) {
		this.session = session;
		session.setIdleTimeout(50000000);
	}

	@OnWebSocketMessage
	public void onMessage(Session session, String message) {
		try {
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
						if (!message.split(":")[1].equals("!TYPING!")) {
						client.SaveMessageToChatHistory("C/" + message);
						}
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
									if (message.split(":")[1].equals("!TYPING!")) {
										client.sendMessage("!TYPING!");
									} else if (message.split(":")[1].equals("!TYPING!")) {
										client.sendMessage("!END!");
										session.close();
										 // Remove this client from the client list
										clients.remove(client);
									} else {
										client.sendMessage(host.displayName + ":" + message.split(":")[1]);
										client.SaveMessageToChatHistory("H/" + host.displayName + ":" + message.split(":")[1] );
									}
		
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
		} catch (Exception ex) {
			System.out.print(ex);	
		}
	}
	
	// Kill session of object gets lost, uhoh!
	@Override
	protected void finalize() throws Throwable {
		session.close();
	}

}