package sonicchatv1

import java.util.Date;

class Ticket {
	Date date
	String email
	String phone
	String issue
	String name
	String issueID
	String product
	String requestRemoteAddress
	boolean opened
	boolean active
	boolean deleted // Never truly allow deletions
	String siteID
	String status
	
	
	static constraints = {
		issue maxSize: 30000;
		phone nullable : true;
		email nullable : true;
		date nullable : true;
		name nullable : true;
		issueID nullable : true;
		product nullable : true;
		opened nullable : true;
		active nullable : true;
		status nullable : true;
		siteID nullable : true;
		deleted nullable : true;
		
		requestRemoteAddress nullable : true;
	}

}
