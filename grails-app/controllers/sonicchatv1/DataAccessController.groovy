package sonicchatv1


import java.text.DateFormat
import java.text.SimpleDateFormat

class DataAccessController {

    def index() { 
		render "WORKS";
	}
	
	def getSiteNameByID() {
		def js = WebSiteJS.findBySiteID(params.id);
		render(text: js.siteURL.toString(), contentType: "text/plain", encoding: "UTF-8");
	}
	
	def quickSearch() {
		String[] query = params.id.split(":");
		String result = "";
		def answers = AnswerBase.findAll("FROM AnswerBase where siteID = " + query[0]);
		for (AnswerBase a1 : answers) {
				result = result.concat(a1.answer + ":::");
			}
		render(result);
	}
	
	
	// **** Receives data ****
	def RecieveAwaymessage() {
		Messages message = new Messages()
		message.subject = "Message from " +  params.name;
		message.message = params.message
		message.email = params.contact
		message.name = params.name
		
		//Get the current time from server
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		message.date = date
		
		//Save the message
		message.save(flush: true, failOnError: true);
		
        render(contentType: 'text/json') {['result:SUCCESS']}
	}
	
	def testHostActive() {
		def hostData = HostData.get(1);
		if (hostData.systemActive && hostData.totalActiveHost > 0) { 	
		render ('jsonCallback({"result" : "TRUE"});');
		
		} else {
		//render(text: "falseHost", contentType: "text/plain", encoding: "UTF-8");
		render ('jsonCallback({"result" : "FALSE"});');
		
		
		}
	}
	
	
	
	
	
}
