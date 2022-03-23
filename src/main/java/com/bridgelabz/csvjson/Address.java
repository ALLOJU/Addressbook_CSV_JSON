package com.bridgelabz.csvjson;

import com.opencsv.bean.CsvBindByName;

public class Address {
	@CsvBindByName
	public String firstname;
	@CsvBindByName
	public String lastname;
	@CsvBindByName
	public String address;
	@CsvBindByName
	public String city;
	@CsvBindByName
	public String state;
	@CsvBindByName
	public String zip;
	@CsvBindByName
	public String number;
	@CsvBindByName
	public String email;

	// constructor
	public Address(String firstname, String lastname, String address, String city, String state, String zip,String number, String email) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.number = number;
		this.email = email;
	}

	public Address() {

	}

	public void Display() {
		System.out.println(firstname + " " + lastname + " " + address + " " + city + " " + state + " " + zip + " "+ number + " " + email);
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
