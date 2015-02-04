package com.onlineinteract.dropwizard;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import io.dropwizard.testing.junit.DropwizardAppRule;

import org.junit.ClassRule;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlineinteract.dropwizard.helloworld.HelloWorldApplication;
import com.onlineinteract.dropwizard.helloworld.HelloWorldConfiguration;
import com.onlineinteract.dropwizard.helloworld.core.Saying;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class IntegrationTest {

	@ClassRule
    public static final DropwizardAppRule<HelloWorldConfiguration> RULE =
            new DropwizardAppRule<HelloWorldConfiguration>(HelloWorldApplication.class, "hello-world.yml");
	
	// This test is intended in hitting the REST endpoint Posting in JSON and assuming a 201 response with content.
	@Test
	public void test() throws JsonProcessingException {
		Client client = Client.create();
		WebResource webResource = client
				.resource("http://localhost:9000/hello-world");
		
		ObjectMapper mapper = new ObjectMapper();
		String dataInput = mapper.writeValueAsString(new Saying(32, "ghi klm content"));
		
		ClientResponse response = webResource.type("application/json").post(
				ClientResponse.class, dataInput);
		
		assertEquals(201, response.getStatus());
		assertEquals("ghi klm content", response.getEntity(String.class));
	}

}