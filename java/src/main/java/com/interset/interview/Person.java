package com.interset.interview;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Person {
	
	private String first_name;
	private String last_name;
	private int siblings;
	private String favourite_food;
	private String birth_timezone;
	private long birth_timestamp;

	
	public Person(String firstName,String lastName,int siblings,String favouriteFood,String timeZoneOffset,long birthTimestamp) {

		this.first_name = firstName;
		this.last_name = lastName;
		this.siblings = siblings;
		this.favourite_food = favouriteFood;
		this.birth_timezone = timeZoneOffset;
		this.birth_timestamp = birthTimestamp;
		
	}
	
	public ZonedDateTime getBirthDateTime() {
		ZonedDateTime birthDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.getBirth_timestamp()),ZoneId.of(this.getBirth_timzone()));
		return birthDateTime;
	}
	public int getBirthMonth() {		
		return this.getBirthDateTime().getMonthValue();
	}
	
	public String getFirst_name() {
		return first_name;
	}



	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}



	public String getLast_name() {
		return last_name;
	}



	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}



	public int getSiblings() {
		return siblings;
	}



	public void setSiblings(int siblings) {
		this.siblings = siblings;
	}



	public String getFavourite_food() {
		return favourite_food;
	}



	public void setFavourite_food(String favourite_food) {
		this.favourite_food = favourite_food;
	}



	public String getBirth_timzone() {
		return birth_timezone;
	}



	public void setBirth_timzone(String birth_timzone) {
		this.birth_timezone = birth_timzone;
	}



	public long getBirth_timestamp() {
		return birth_timestamp;
	}



	public void setBirth_timestamp(long birth_timestamp) {
		this.birth_timestamp = birth_timestamp;
	}


	@Override
	public String toString() {
		
		return "Person Name: " + this.first_name + " " + this.last_name + " , Siblings: " + this.siblings + ", Born: " + this.getBirthDateTime() + ", Favorite Food: " + this.favourite_food ;
	}
}