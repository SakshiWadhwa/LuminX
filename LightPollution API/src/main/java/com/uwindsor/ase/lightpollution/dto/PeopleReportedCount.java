package com.uwindsor.ase.lightpollution.dto;

public class PeopleReportedCount {

	public String message;
	public int reported_incidents;

	public PeopleReportedCount() {}
	
	public PeopleReportedCount(int reported_incidents) {
		super();
		this.reported_incidents = reported_incidents;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getReported_incidents() {
		return reported_incidents;
	}

	public void setReported_incidents(int reported_incidents) {
		this.reported_incidents = reported_incidents;
	}
}
