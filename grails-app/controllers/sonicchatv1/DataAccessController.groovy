package sonicchatv1


import java.text.DateFormat
import java.text.SimpleDateFormat
import session.MyWebSocketHandler
import java.util.ArrayList

class DataAccessController {

	// **** METHODS USED WITHIN THE SAME ORIGIN URL SERVER ****
    def index() { 
		render "Data Access controller is running.";
	}
	
	def getSiteNameByID() {
		def js = WebSiteJS.findBySiteID(params.id);
		render(text: js.siteURL.toString(), contentType: "text/plain", encoding: "UTF-8");
	}
	
	def quickSearch() {
		String[] query = params.id.split(":");
		String[] querySplit = query[1].split(" ");
		def answers = AnswerBase.findAll("FROM AnswerBase where siteID = " + query[0]);
		
		String result = "";
		
		// Brain for finding answers.. TODO
		for (AnswerBase a1 : answers) {
			for (String queryString : querySplit) {
				
				// Get question tags
				String tags = a1.tags;
				try {
					tags.replace(",", " ")
				} catch (Exception ex) {
					tags = "";
				}	
				
				if ((tags.toLowerCase().contains(queryString.toLowerCase())) && queryString.length() > 3) {
					if (!result.contains(a1.answer)) { // Don't allow duplicate answers
						result = result.concat(a1.answer + ":::");
					}
				}
					
			}	
		}
		
		
		render(text: result, contentType: "text/plain", encoding: "UTF-8");
	}
	
	// **** METHODS USED WITHIN THE SAME ORIGIN URL SERVER ****
	// **** Receives data from DIFFERENT ORIGIN URL with JSONP. NOTICE THE JSONCALLBACK ****
	def recieveMessage() {
		Messages message = new Messages()
		message.subject = "Message from " +  params.name;
		message.message = params.message
		message.email = params.contact
		message.name = params.name
		
		//Get the current time from server
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		message.date = date;
		
		//Save the message
		message.save();
		//message.save(flush: true, failOnError: true);
		render ('jsonCallbackMessage({"result" : "SUCCESS"});');
	}
	
	def recieveAwayMessage() {
		Messages message = new Messages()
		message.subject = "Message from " +  params.name;
		message.message = params.message
		message.email = params.contact
		message.name = params.name
		
		//Get the current time from server
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		message.date = date;
		
		//Save the message
		message.save();
		//message.save(flush: true, failOnError: true);
		render ('jsonCallbackAwayMessage({"result" : "SUCCESS"});');
	}
	
	def testHostActive() {
		// request.getRemoteAddr()
		//def hostData = HostData.get(1);	
		if (MyWebSocketHandler.hosts != null){
			if (MyWebSocketHandler.hosts.size > 0) { 	
			render ('jsonCallback({"result" : "TRUE"});');
			} else {
			//render(text: "falseHost", contentType: "text/plain", encoding: "UTF-8");
			render ('jsonCallback({"result" : "FALSE"});');
			}
		}
	}
	// **** Receives data from DIFFERENT ORIGIN URL ****
	
	
	
	
	
}
