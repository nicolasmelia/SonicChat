package sonicchatv1

class Users {
	String userName;
	String password;
	String special;
	String siteID; // site the answer belongs too
	String displayName;
	
	static constraints = {
		userName nullable : true
		password nullable : true
		special nullable : true
		siteID nullable : true
		
	}

}
