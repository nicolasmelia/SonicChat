class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
		
		//"/"(view:"/static/live/alpha/index.html")
		"/" {
			controller = "DataAccess"
			action = "index"
		}
		
        "/SCadmin"(view:"/index")
        "500"(view:'/error')
	}
}
