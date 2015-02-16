package com.onlineinteract.dropwizard.helloworld.rabbitmq.publisher;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import com.rabbitmq.client.ReturnListener;

import java.io.IOException;

/**
 * Created by dev on 10/02/15.
 */
public class CreateCustomerRequestPublisher {

	private static final String EXCHANGE_NAME = "customer";
	public static final String ROUTING_KEY = "customer.insert";

	private final Channel channel;
	private final Connection connection;

	public CreateCustomerRequestPublisher() throws IOException {

		String abc = "abc";
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		connection = factory.newConnection();
		channel = connection.createChannel();

		channel.exchangeDeclare(EXCHANGE_NAME, "direct");
		channel.addReturnListener(new ReturnListener() {
			@Override
			public void handleReturn(int arg0, String arg1, String arg2,
					String arg3, BasicProperties arg4, byte[] arg5)
					throws IOException {
				// I guess the argument you want to check is arg1 for "NO_ROUTE"
				// But basically if it gets routed OK, this isn't even invoked but 
				// worth doing a conditional to see what it is anyway.
				System.out.println("*** Return Values:");
				System.out.println("arg0: " + arg0);
				System.out.println("arg1: " + arg1);
				System.out.println("arg2: " + arg2);
				System.out.println("arg3: " + arg3);
				System.out.println("arg4: " + arg4);
				System.out.println("arg5: " + arg5);
				/*
				Example Output: if NO_ROUTE:
				arg0: 312
				arg1: NO_ROUTE
				arg2: customer
				arg3: customer.insert
				arg4: #contentHeader<basic>(content-type=text/plain, content-encoding=null, headers=null, delivery-mode=2, priority=0, correlation-id=null, reply-to=null, expiration=null, message-id=null, timestamp=null, type=null, user-id=null, app-id=null, cluster-id=null)
				arg5: [B@e4cb7d
				*/
			}
		});
	}

	public void publish(String message) throws IOException, InterruptedException {
		channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, true, MessageProperties.PERSISTENT_TEXT_PLAIN,
				message.getBytes());
		System.out.println(" [x] Sent '" + "customer.insert" + "':'" + message
				+ "'");
		channel.confirmSelect();
		channel.waitForConfirms();
		// Thread.sleep(1000); - don't need this, just proving the listener got invoked before putting in the above blocking call waitForConfirms().
	}

	public void closeConnections() throws IOException {
		channel.close();
		connection.close();
	}

	public static void main(String args[]) throws IOException, InterruptedException {

		CreateCustomerRequestPublisher requestPublisher = new CreateCustomerRequestPublisher();
		requestPublisher.publish("{\"message\": \"hi Gary\"}");
		requestPublisher.closeConnections();
	}

}
