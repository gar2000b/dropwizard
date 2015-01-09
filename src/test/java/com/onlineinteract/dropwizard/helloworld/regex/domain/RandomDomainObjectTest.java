package com.onlineinteract.dropwizard.helloworld.regex.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class RandomDomainObjectTest {

	private static final String RECORD = "JG410825BGaryBlack";

	@Test
	public void shouldParseRandomRecordStringReturningRandomDomainObject() throws Exception {
		RandomDomainObject randomDomainObject = RandomDomainObject.parse(RECORD);
		assertEquals("JG410825B", randomDomainObject.getNino());
		assertEquals("Gary", randomDomainObject.getForename());
		assertEquals("Black", randomDomainObject.getSurname());
	}
}