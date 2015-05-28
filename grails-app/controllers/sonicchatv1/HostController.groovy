package sonicchatv1

import session.Host

class HostController {

    def index() { 
		def hostData = HostData.get(1);
		if (hostData.systemActive) {
		render "System is active without errors."
		} else {
		render "SYSTEM IS VOLUNTARY CURRENTLY DOWN";
		}
	}
	
	
	def startSocketServer() {
		//Must start socket!
		
		if (Host.server == null) {
			Host.startServer();
			render "Server has started!"
		} else if (!Host.server.isStarted()) {
			Host.startServer();
			render "Server has started!"
		} else {
			render "Server is RUNNING at: " + session.Host.server.getURI().toString();
		}
	}
	
	def stopSocketServer() {
		//Must start socket!
		if (Host.server.isStarted()) {
			Host.server.stop();
			Host.thread.interrupt();
			render "Server has been succesfully been STOPPED"
		} else {
			render "Server is not running."
		}
	}
	
	
	def StartHostTest() {
		def hostData = HostData.get(1);
		if (hostData.systemActive) {
			def user = Users.findByUserName("Nick");
			session["displayName"] = user.displayName
			render (view: "ChatDashboard");
		}
	}
	
	def StartHostLive() {
		def hostData = HostData.get(1);
		if (hostData.systemActive) {
			def user = Users.findByUserName("Nick");
			session["displayName"] = user.displayName
			render (view: "ChatDashboardLive");
		}
	}
	

	def startChatTest() {
		render (view: "TestChat" )
	}
	
	def testSystem() {
		render "WORKING"
	}
}
