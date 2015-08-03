package sonicchatv1


import com.google.gson.Gson
import java.text.DateFormat
import java.text.SimpleDateFormat
import session.MyWebSocketHandler
import java.util.ArrayList

class DataAccessController {
	
	// **** METHODS USED WITHIN THE SAME ORIGIN URL SERVER ****
    def index() { 
		render "You do not have access to this page.";
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
	def recieveAwayMessage() {
		if (checkDBSecurity()) {
			Messages message = new Messages()
			message.messageType = "Message"
			message.subject = "Message"
			message.message = params.message
			message.email = params.contact
			message.name = params.name
			message.siteID = params.siteID
			message.deleted = false
			message.opened = false
			message.requestRemoteAddress = request.getRemoteAddr().toString()
			message.status = "New"
			message.hash = "0"
			
			//Get the current time from server
			Date date = new Date();
			message.date = date;
			
			//Create a unique message ID
			Random rand = new Random();
			int randomNum = rand.nextInt((400000 - 150000) + 1) + 150000;
			message.messageID = ("M" + Integer.toString(randomNum))
			
			//Save the message
			message.save();
			//message.save(flush: true, failOnError: true);
			render ('jsonCallbackAwayMessage({"result" : "SUCCESS"});');
		} else {
			render ('jsonCallbackAwayMessage({"result" : "Server Denial"});');
		}
	}
	
	def recieveFeedback() {
		if (checkDBSecurity()) {
			Messages message = new Messages()
			message.messageType = "Feedback"
			message.siteID = params.siteID
			message.subject = "This is feedback"
			message.message = params.message
			message.hash = params.rating
			message.email = params.contact
			message.name = "EMAIL ONLY"
			message.deleted = false
			message.requestRemoteAddress = request.getRemoteAddr().toString()
			
			//Get the current time from server
			Date date = new Date();
			message.date = date;
			
			//Create a unique message ID
			Random rand = new Random();
			int randomNum = rand.nextInt((400000 - 150000) + 1) + 150000;
			message.messageID = ("F" + Integer.toString(randomNum))
			
			//Save the message
			message.save();
			render ('jsonCallbackFeedback({"result" : "SUCCESS"});');
		} else {
			render ('jsonCallbackFeedback({"result" : "Server Denial"});');
		}
	}
	
	
	def recieveTicket() {
		if (checkDBSecurity()) {
			Ticket ticket = new Ticket()
			ticket.siteID = params.siteID
			ticket.issue = params.issue
			ticket.email = params.contact
			ticket.name = params.name
			ticket.active = true
			ticket.opened = true
			ticket.deleted = false
			ticket.product = params.product
			ticket.requestRemoteAddress = request.getRemoteAddr().toString()
			
			//Get the current time from server
			Date date = new Date();
			ticket.date = date;
			
			// Generate a random ticket ID
			Random rand = new Random();
			int randomNum = rand.nextInt((400000 - 150000) + 1) + 150000;
			ticket.issueID = ("F" + Integer.toString(randomNum))

			//Save the ticket to the DB
			ticket.save();
			render ('jsonCallbackTicket({"result" : "' + ticket.issueID + '"});')
		} else {
			render ('jsonCallbackTicket({"result" : "' + "Server Blocked" + '"});')
		}	
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
	
	def quickSearchWidget() {
		String[] query = params.id.split(":");
		String[] querySplit = query[1].split(" ");
		def answers = AnswerBase.findAllBySiteID(query[0].toString());
		
		String result = "";
		
		// Create an empty list to add answers that match too
		ArrayList<AnswerBase> answerList = new ArrayList<AnswerBase>();
		
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
						answerList.add(a1);
					}
				}
					
			}
		}
		
		String jsonResults = new Gson().toJson(answerList);
		
		// Renders json 
		render ('jsonCallbackAnswers(' + jsonResults + ');');	
	}
	
	def getTicketById() {
		def answers = Ticket.findAllByIssueIDOrEmail(params.issueID, params.Email)
		String jsonResults = new Gson().toJson(answers);
		// Renders json
		render ('jsonCallbackTicketStatus(' + jsonResults + ');');
	}
	
	
	// **** METHODS USED TO CHECK SECURITY ****
	def checkDBSecurity() {
		SecurityHash DBRequest = SecurityHash.findByHashId("DBRequest")
		SecurityHash AllowedDBRequest = SecurityHash.findByHashId("AllowedDBRequest")		
		if (Integer.parseInt(DBRequest.hash) < Integer.parseInt(AllowedDBRequest.hash)) {
			// If allowed to make a DB request make sure address of request does not exceed max allowed request
			def messageCount = Messages.countByRequestRemoteAddress(request.getRemoteAddr().toString());
			def ticketCount = Messages.countByRequestRemoteAddress(request.getRemoteAddr().toString());
			
			if ((messageCount + ticketCount) < 50) {	
				int requestCount = Integer.parseInt(DBRequest.hash)
				requestCount++
				DBRequest.hash = requestCount + ""
				DBRequest.save(flush:true);
				return true;	
			}	else {
				return false;
			}
			
		} else {
			return false;
		}	
	}
	
	
	
}
