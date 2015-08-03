package sonicchatv1

class UserDataAccessController {

    def index() { 
		render "You do not have access to this page."
	}
	
	def login() {
		if (params.username == null && params.password == null){
			render(view: "login", model: ["message":"Welcome to the SonicChat dashboard.", "color": "#606060"])
		} else {
		def user = Users.findByUserNameAndPassword(params.username, params.password);
			if (user != null) {
				session["displayName"] = user.displayName
				session["userName"] = user.userName
				session["siteID"] = user.siteID
				dashboard()
			} else {
				render(view: "login", model: ["message":"*Username or password not found. Please try again.", "color": "red"])
			}
		
		}
	}
	
	def logOut() {
		session.invalidate()
		login()
	}
	
	def dashboard() {
		def user = Users.findByUserName(session["userName"])
		if (user != null) {
		// Open the main dashboard
		def siteInfo = WebSiteJS.findBySiteID(session["siteID"]);
		ArrayList<Tiles> tiles = new ArrayList<Tiles>();
		for (String tileID : siteInfo.selectedTiles.split(",")) {
			def tile = Tiles.findByTileID(tileID.trim())
			if (tile != null) {
				tiles.add(tile)
			} 
		}
			render(view: "mainDash", model: [tiles: tiles])
		} else {
			render "UhOh! Your session has ended. Please go back to SonicChats.com and sign back in."
		}	
	}
	
	// *********** Methods linked to tiles ***********
	def startChatV1 () {
		def hostData = HostData.get(1);
		def user = Users.findByUserName(session["userName"])
		if (user != null) {
			if (hostData.systemActive) {
				render (view: "ChatDashboardLive");
			} else {
				render "SonicChats Chat Server is voluntary down. Please go back and contact support."
			}
		} else {
			render "UhOh! Your session has ended. Please go back to SonicChats.com and sign back in."
		}
	}
	
	
	// *********** GET FOR MESSAGES ***********
	
	def messagesV1 () {
		// Get messages and order them by desc
		def messagesFromDB = Messages.findAllBySiteIDAndMessageTypeAndDeleted(session["siteID"], "Message", false, [sort:'date',order:'desc']);
		for (Messages message : messagesFromDB) {
			if (message.hash != null && message.hash != "") {
				int TimesRead = Integer.parseInt(message.hash);
				TimesRead += 1
				message.hash = "" + TimesRead
				
				if (TimesRead == 2) {
					message.status = "Read"
				}
				message.save(flush:true);
			}
		}
		render (view: "messages",  model : [messages:messagesFromDB]);
	}
	
	def deleteMessage () {		
		def messageFromDB = Messages.findByMessageID(params.id);
		messageFromDB.deleted = true
		if (messageFromDB.save(flush:true)) {
			render "success"
		} else {
			render "No permission."
		}
	}
	
	// *********** GET FOR TILES ***********
	
	
}
