package sonicchatv1

class WebSiteJSController {
	static scaffold = true;
	def js() {
		def js = WebSiteJS.findBySiteID(params.id);
		 render(text: js.chatBoxJS.toString(), contentType: "application/javascript", encoding: "UTF-8")
	}
}
