package sonicchatv1

class WebSiteJS {
	String siteID;
	String chatBoxJS;
	String siteURL;
	
	static constraints = {
		chatBoxJS maxSize: 60000;
		siteID nullable : true
		chatBoxJS nullable : true
		siteURL nullable : true
	}
}
