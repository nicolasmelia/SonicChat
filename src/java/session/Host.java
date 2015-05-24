package session;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class Host {
	Server server;
	public void startHost(final int port) {
		Thread startSevice = new Thread(new Runnable() {
			@Override
			public void run() {
		        try {
				        server = new Server(port);
				        WebSocketHandler wsHandler = new WebSocketHandler() {
				    		// Create a new host
				            @Override
				            public void configure(WebSocketServletFactory factory) {
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
		});
		startSevice.start();
	}
}
