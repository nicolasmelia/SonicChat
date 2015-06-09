package sonicchatv1

import session.Host

class HostController {

	// ****************** LIVE SERVER SOCKET CONTROL ******************
	
    def index() { 
		def hostData = HostData.get(1);
		if (hostData.systemActive) {
		render "Server is RUNNING at: " + session.Host.server.getURI().toString();
		} else {
		render "SYSTEM IS VOLUNTARY DOWN...";
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
			render "Server is currently RUNNING at: " + session.Host.server.getURI().toString();
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
	
	// ****************** Employee Systems ******************
	def loginEmployee() {	
		if (params.username == null && params.password == null){
			render(view: "login", model: ["message":"Welcome to SonicChat Employee Systems.", "color": "#606060"])
		} else {
		def user = Users.findByUserNameAndPassword(params.username, params.password);
			if (user != null) {
					session["displayName"] = user.displayName
					render (view: "ChatDashboardLive");
			} else {
			render(view: "login", model: ["message":"*Username or password not found. Please try again.", "color": "red"])	
			}
		
		}	
	}
	
	
	// ****************** TESTING ******************
	
	def StartHostLiveTest() {
		def hostData = HostData.get(1);
		if (hostData.systemActive) {
			def user = Users.findByUserName("Nick");
			session["displayName"] = user.displayName
			render (view: "ChatDashboardLive");
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
		} else  {
			render "Server is not running."
		}
	}
	
	def startChatTest() {
		render (view: "TestChat" )
	}
	
}
