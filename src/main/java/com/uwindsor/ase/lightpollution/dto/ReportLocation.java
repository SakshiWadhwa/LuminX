package com.uwindsor.ase.lightpollution.dto;

public class ReportLocation {

	private String current_location;
	private String zip_code;
	private String street_name;
	
	public ReportLocation() {}
	
	public ReportLocation(String current_location, String zip_code, String street_name) {
		super();
		this.current_location = current_location;
		this.zip_code = zip_code;
		this.street_name = street_name;
	}
	public String getCurrent_location() {
		return current_location;
	}
	public void setCurrent_location(String current_location) {
		this.current_location = current_location;
	}
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	public String getStreet_name() {
		return street_name;
	}
	public void setStreet_name(String street_name) {
		this.street_name = street_name;
	}
}
