package com.uwindsor.ase.lightpollution.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_pollution_report_link")
public class UserPollutionReportLink implements Serializable{

	private static final long serialVersionUID = -6748047559085211357L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private UserDetails userDetails;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "pollution_report_id", nullable = false)
	private LocationPollutionReportLink locationPollutionReportLink;
	
	private Date date;

	public UserPollutionReportLink() {}
	
	public UserPollutionReportLink(UserDetails userDetails, LocationPollutionReportLink locationPollutionReportLink, Date date) {
		super();
		this.userDetails = userDetails;
		this.locationPollutionReportLink = locationPollutionReportLink;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public LocationPollutionReportLink getLocationPollutionReportLink() {
		return locationPollutionReportLink;
	}

	public void setLocationPollutionReportLink(LocationPollutionReportLink locationPollutionReportLink) {
		this.locationPollutionReportLink = locationPollutionReportLink;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
