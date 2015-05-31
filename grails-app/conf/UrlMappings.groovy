class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
		
		//"/"(view:"/static/live/alpha/index.html")
        "/"(view:"/index")
        "500"(view:'/error')
	}
}
