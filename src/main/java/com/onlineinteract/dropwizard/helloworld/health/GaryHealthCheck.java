package com.onlineinteract.dropwizard.helloworld.health;

import com.codahale.metrics.health.HealthCheck;

public class GaryHealthCheck extends HealthCheck{

	private int index;
	private int total;
	
	public GaryHealthCheck() {
		index = 11;
		total = 11;
	}
	
	@Override
	protected Result check() throws Exception {
		if(index == total) {
			return HealthCheck.Result.healthy("Gary's Health is 100%");
		} else {
			return HealthCheck.Result.unhealthy("There appears to be a problem");
		}
	}

}
