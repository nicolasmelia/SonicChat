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
	
	def startHost() {
		def hostData = HostData.get(1);
		if (hostData.systemActive) {
			// Log in
			def user = Users.findByUserName("Nick");
			session["displayName"] = user.displayName
	
			hostData.totalActiveHost += 1;
			hostData.save();
			
			Host host = new Host();
			host.startHost(50002);
			render (view: "ChatDashboard");
		} else {
		render "SYSTEM IS VOLUNTARY CURRENTLY DOWN";
		}
	}
	

	def startClient() {
		render (view: "TestChat" )
	}
}
