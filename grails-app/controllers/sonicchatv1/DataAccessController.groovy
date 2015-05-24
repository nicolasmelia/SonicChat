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
		message.subject = "Message from " +  params.name + " : " + params.contact
		message.message = params.message
		message.email = params.contact
		message.name = params.name
		
		//Get the current time from server
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		message.date = date
		
		//Save the message
		message.save()
		//print(params.name + " " + params.contact + " " + params.message);
		render(text: "SUCCESS", contentType: "text/plain", encoding: "UTF-8");
	}
	
	def testHostActive() {
		def hostData = HostData.get(1);
		if (hostData.systemActive && hostData.totalActiveHost > 0) { 	
		render(text: "TRUE", contentType: "text/plain", encoding: "UTF-8");
		} else {
		render(text: "FALSE", contentType: "text/plain", encoding: "UTF-8");
		}
	}
	
	
	
	
	
}
