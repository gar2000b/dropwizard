package com.onlineinteract.dropwizard.helloworld.resources;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.junit.Test;

import com.onlineinteract.dropwizard.helloworld.core.Saying;
import com.onlineinteract.dropwizard.helloworld.resources.HelloWorldResource;

public class HelloWorldResourceTest {

	@Test
	public void test_receivePost_submittingSaying_reponse201passingBackCOntent() {
		
		HelloWorldResource target = new HelloWorldResource("", "");
		Response result = target.receivePost(new Saying(32, "some content"));
		assertEquals(201, result.getStatus());
		assertEquals("some content", result.getEntity());
	}

}
