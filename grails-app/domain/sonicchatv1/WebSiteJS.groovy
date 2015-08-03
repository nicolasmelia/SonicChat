package sonicchatv1

class WebSiteJS {
	String siteID;
	String chatBoxJS;
	String selectedTiles;
	String siteURL;
	 
	static constraints = {
		chatBoxJS maxSize: 170000;
		siteID nullable : true
		chatBoxJS nullable : true
		siteURL nullable : true
		selectedTiles nullable : true
	}
	 
	static mapping = {
		chatBoxJS sqlType: 'longtext'
	}
	
	
}
