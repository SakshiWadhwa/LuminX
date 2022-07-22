package com.uwindsor.ase.lightpollution.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="location_master")
public class LocationMaster implements Serializable{

	private static final long serialVersionUID = -1767901276169217082L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String current_location;
	private String zip_code;
	private String street_name;
	
	public LocationMaster() {}
	
	public LocationMaster(String current_location, String zip_code, String street_name) {
		super();
		this.current_location = current_location;
		this.zip_code = zip_code;
		this.street_name = street_name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
