package session;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class Host {
	public static Server server;
	public static Thread thread;
	final static int port = 50005;

	public static void startServer() {
		thread = new Thread() {
			public void run() {
				System.out.println("Hello from a thread!");
				try {
						Host.server = new Server(port);
						WebSocketHandler wsHandler = new WebSocketHandler() {
							// Create a new host
							@Override
							public void configure(
									WebSocketServletFactory factory) {
								factory.register(MyWebSocketHandler.class);
							}
						};
						server.setHandler(wsHandler);
						server.start();
						server.join();
				} catch (Exception e) {
					// Log exception downt he road
				}
			}

		};

		thread.start();
	}

}
