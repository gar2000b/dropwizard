package com.onlineinteract.dropwizard.helloworld.regex.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class RandomDomainObject {

	private static final Pattern NINO = compile("^(\\w{9})(\\w{4})(\\w{5})$");

	private String nino;
	private String forename;
	private String surname;

	public static RandomDomainObject parse(String randomRecordString) {
		Matcher randomMatcher = NINO.matcher(randomRecordString);

		if (randomMatcher.matches()) {
			RandomDomainObject randomDomainObject = new RandomDomainObject();
			randomDomainObject.setNino(randomMatcher.group(1));
			randomDomainObject.setForename(randomMatcher.group(2));
			randomDomainObject.setSurname(randomMatcher.group(3));
			return randomDomainObject;
		}

		return null;
	}

	public String getNino() {
		return nino;
	}

	public void setNino(String nino) {
		this.nino = nino;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
}