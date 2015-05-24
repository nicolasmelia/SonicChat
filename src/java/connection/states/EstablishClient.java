package connection.states;

import objects.ClientObject;
import objects.HostObject;
import session.MyWebSocketHandler;
import org.eclipse.jetty.websocket.api.Session;


public class EstablishClient {
	public static String establish(Session session, String message) {
		boolean establishedConnection = false;
		boolean hostSocket = false;
		ClientObject client = new ClientObject(session);
		client.port = session.getLocalAddress().getPort();
		if (message.contains("CONNECTION INFORMATION")) {
			String connectionInformaton[] = message.split(":");
			switch (EstablishStates.valueOf(connectionInformaton[1])) {
			case HOST:
				HostObject host = new HostObject(session);
				host.port = session.getLocalAddress().getPort();
				establishedConnection = host.initiateHost(message);
				hostSocket = true;
				break;
			case ESTABLISH:
				establishedConnection = client.initiateClient(message);
				break;
			case REESTABLISH:
				boolean clientFound = false;
				for (ClientObject co : MyWebSocketHandler.clients) {
					if (connectionInformaton[2].matches(co.clientID + "")) {
						clientFound = true;
						establishedConnection = client.initiateExistingClient(
								co, connectionInformaton[2], message,  true);
						MyWebSocketHandler.clients.remove(co);
						break;
					}
				}
				if (!clientFound) {
					establishedConnection = client.initiateExistingClient(
							client, connectionInformaton[2], message, false);
				}
				break;
			default:
				client.sendMessage("Did not recieve establish message");
				break;
			}

		}
		return establishedConnection + ":" + hostSocket + ":" + client.hostID;
	}
}
	
	
