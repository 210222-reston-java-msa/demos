package com.revature.view;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("viewController") // The @Controller annotation marks this calss as a web request handler
public class ViewControllerImpl implements ViewController {

	private static Logger logger = Logger.getLogger(ViewControllerImpl.class);
	
	// We are mapping requests to this particular controller (or handler)
	// This annotation specifies what types of requests this class handles
	@RequestMapping(value= { "/", "/index", "/test" }, method=RequestMethod.GET)
	public String index() {
		logger.info("Getting index HTML from View Controller");
		System.out.println("=====================  This is a test  ================================");
		return "redirect: index.html";
	}

	
	
}
