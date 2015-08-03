package sonicchatv1

class Messages {
	Date date
	String email
	String phone
	String message
	String subject
	String name
	String requestRemoteAddress
	String messageType
	String hash
	String siteID
	String messageID
	boolean deleted // never truly allow deletions 
	boolean opened // If message is new or not
	String status 
	
	static constraints = {
		message maxSize: 30000;
		phone nullable : true;
		email nullable : true;
		date nullable : true;
		subject nullable : true;
		name nullable : true;
		deleted nullable : true;
		messageID nullable : true;
		messageType nullable : true;
		hash nullable : true;
		siteID nullable : true;
		status nullable : true;
		requestRemoteAddress nullable : true;
	}

}
