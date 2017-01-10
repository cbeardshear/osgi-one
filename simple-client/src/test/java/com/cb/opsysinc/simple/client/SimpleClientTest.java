package com.cb.opsysinc.simple.client;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cb.opsysinc.simple.service.api.SimpleService;

public class SimpleClientTest {
	
	private static Logger LOG = LoggerFactory.getLogger(SimpleClientTest.class); 
	
	@Test
	public void testStart()
	{
		SimpleService service = new SimpleService() {
			
			@Override
			public void sayHello() {
				LOG.info("hello");
			}
			
			@Override
			public String getMessage() {
				return "unit test message";
			}
		};
			
		SimpleClient client = new SimpleClient();
		client.service = service;
		
		//make sure that start/stop don't throw
		client.start();
		client.stop();
		Assert.assertTrue(true);
	}

}
