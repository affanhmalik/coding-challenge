package com.interset.interview;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;

public class Person {
	String firstName;
	String lastName;
	int siblings;
	String favouriteFood;
	String timeZoneOffset;
	long birthTimestamp;
	
	public Person(String firstName,String lastName,int siblings,String favouriteFood,String timeZoneOffset,long birthTimestamp) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.siblings = siblings;
		this.favouriteFood = favouriteFood;
		this.timeZoneOffset = timeZoneOffset;
		this.birthTimestamp = birthTimestamp; 
		
	}
	
	public String toString() {
		
		return "Person Name: " + this.firstName + " " + this.lastName + " , Siblings: " + this.siblings + " Born: " + Instant.ofEpochMilli(this.birthTimestamp) ;
	}
}