package com.example.muletoSping;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
class Controller {
	private static final Logger LOG = Logger.getLogger(ELKController.class.getName());


	@RequestMapping(value = "/hellomule", method = RequestMethod.GET)
	public String get() {
		String response = "{	\"mule output\" : \"welcome To Spring\"}";
		LOG.log(Level.INFO, response);

		return response ;
	}

}