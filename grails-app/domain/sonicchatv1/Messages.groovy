package sonicchatv1

class Messages {
	Date date
	String email
	String phone
	String message
	String subject
	String name
	
	static constraints = {
		message maxSize: 30000;
		phone nullable : true;
		email nullable : true;
		date nullable : true;
		subject nullable : true;
		name nullable : true;
	}

}
