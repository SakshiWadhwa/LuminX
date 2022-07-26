package com.uwindsor.ase.lightpollution.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="location_pollution_report_link")
public class LocationPollutionReportLink implements Serializable{

	private static final long serialVersionUID = 2222942026858737640L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "location_id", nullable = false)
	private LocationMaster locationMaster;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "pollution_report_id", nullable = false)
	private PollutionReport pollutionReport;
	
	@OneToMany(mappedBy = "locationPollutionReportLink", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<UserPollutionReportLink> userPollutionReportLink;
	
	private Date date;
	
	public LocationPollutionReportLink() {}

	public LocationPollutionReportLink(LocationMaster locationMaster, PollutionReport pollutionReport,
			Date date) {
		super();
		this.locationMaster = locationMaster;
		this.pollutionReport = pollutionReport;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocationMaster getLocationMaster() {
		return locationMaster;
	}

	public void setLocationMaster(LocationMaster locationMaster) {
		this.locationMaster = locationMaster;
	}

	public PollutionReport getPollutionReport() {
		return pollutionReport;
	}

	public void setPollutionReport(PollutionReport pollutionReport) {
		this.pollutionReport = pollutionReport;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
