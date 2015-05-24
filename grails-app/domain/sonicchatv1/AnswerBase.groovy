package sonicchatv1

class AnswerBase {
	String question;
	String answer;
	String tags;
	
	String siteID; // site the answer belongs too
	
	static constraints = {
		tags nullable : true;
	}
}
