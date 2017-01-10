package com.cb.opsysinc.simple.client;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Invalidate;
import org.apache.felix.ipojo.annotations.Requires;
import org.apache.felix.ipojo.annotations.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cb.opsysinc.simple.service.api.SimpleService;

/**
 * SimpleClient that uses iPojo annotations to inject a SimpleService instance and uses lifecycle methods
 * 
 * @author cbear
 */
@Component(name = "AnnotatedIpojoClient", immediate = false)
@Instantiate
public class SimpleClient {

	private static Logger LOG = LoggerFactory.getLogger(SimpleClient.class); 
	
	/*
	 * TODO: make the SimpleService member private after figuring out how to unit test with ipojo
	 */
	@Requires
	protected SimpleService service;

	/**
	 * constructor - should not be used directly
	 */
	public SimpleClient() {
	}

	/**
	 * Called when bundle started by container. 
	 */
	@Validate
	public void start() {
		String msg = String.format("SimpleClient::start message is [%s]", service.getMessage());
		System.out.println(msg);
		LOG.info(msg);
	}

	/**
	 * Called when bundle stopped by container. 
	 */
	@Invalidate
	public void stop() {
		System.out.println("SimpleClient::stop()");
		LOG.info("SimpleClient::stop()");
	}

}
