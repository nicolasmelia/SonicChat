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
	
	static constraints = {
		issue maxSize: 30000;
		phone nullable : true;
		email nullable : true;
		date nullable : true;
		name nullable : true;
		issueID nullable : true;
		product nullable : true;
	}

}
