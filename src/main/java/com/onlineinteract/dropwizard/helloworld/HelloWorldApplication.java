package com.onlineinteract.dropwizard.helloworld;

import com.onlineinteract.dropwizard.helloworld.health.GaryHealthCheck;
import com.onlineinteract.dropwizard.helloworld.health.TemplateHealthCheck;
import com.onlineinteract.dropwizard.helloworld.resources.HelloWorldResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Hello world!
 * 
 */
public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
	public static void main(String[] args) throws Exception {
		new HelloWorldApplication().run(args);
	}

	@Override
	public String getName() {
		return "hello-world";
	}

	@Override
	public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
		// TODO Auto-generated method stub
	}

	@Override
	public void run(HelloWorldConfiguration configuration,
			Environment environment) throws Exception {

		final HelloWorldResource resource = new HelloWorldResource(
				configuration.getTemplate(), configuration.getDefaultName());
		environment.jersey().register(resource);

		// Health Checks
		TemplateHealthCheck healthCheck = new TemplateHealthCheck(
				configuration.getTemplate());
		GaryHealthCheck garyHealthCheck = new GaryHealthCheck();
		environment.healthChecks().register("Template", healthCheck);
		environment.healthChecks().register("Gary", garyHealthCheck);
	}
}
