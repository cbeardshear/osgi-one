package com.cb.opsysinc.simple.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cb.opsysinc.simple.service.api.SimpleService;

/**
 * Trivial implementation of the SimpleService interface. It is just used to test that the container
 * wiring is working
 * 
 * @author cbear
 *
 */
public class SimpleServiceImpl implements SimpleService {
	
	private static Logger LOG = LoggerFactory.getLogger(SimpleServiceImpl.class); 

	/**
	 * Prints message to logging framework and stdout
	 */
	public void sayHello() {
		LOG.info("Hello From SimpleServiceImpl");
		System.out.println("Hello From SimpleServiceImpl");
	}

	/**
	 * @return a hard-coded message
	 */
	public String getMessage() {
		return "Howdy";
	}

}
