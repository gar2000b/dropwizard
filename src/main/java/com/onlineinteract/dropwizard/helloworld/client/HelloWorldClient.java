package com.onlineinteract.dropwizard.helloworld.client;

import com.onlineinteract.dropwizard.helloworld.core.Saying;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class HelloWorldClient {

	public HelloWorldClient() {
	}

	public static void main(String[] args) {
		HelloWorldClient helloWorldClient = new HelloWorldClient();
		helloWorldClient.getRequest();
		helloWorldClient.postRequest();
	}

	public void getRequest() {
		Client client = Client.create();
		WebResource webResource = client
				.resource("http://localhost:9000/hello-world?name=Brian");

		ClientResponse response = webResource.accept("application/json").get(
				ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}

		Saying saying = response.getEntity(Saying.class);
		System.out.println("*** Id is " + saying.getId());
		System.out.println("*** Content is " + saying.getContent());
		System.out.println("*** Saying is " + saying);
	}

	public void postRequest() {
		Client client = Client.create();
		WebResource webResource = client
				.resource("http://localhost:9000/hello-world");

		String dataInput = "{\"id\": \"32\", \"content\": \"abc def content\"}";

		ClientResponse response = webResource.type("application/json").post(
				ClientResponse.class, dataInput);

		if (response.getStatus() != 201) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}

		System.out.println("Output from Server ....");
		String output = response.getEntity(String.class);
		System.out.println(output);
	}
}
