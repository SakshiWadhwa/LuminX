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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="pollution_report")
public class PollutionReport implements Serializable{

	private static final long serialVersionUID = -2947809070142902215L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long pollution_report_id;
	
	private String image;
	private String light_source;
	private String style;
	private String brightness;
	private String light_color;
	private Date date;
	
	@OneToMany(mappedBy = "pollutionReport", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<LocationPollutionReportLink> locationPollutionReportLink;
	
	
	public PollutionReport() {}
	
	public PollutionReport(String image, String light_source, String style, String brightness,
			String light_color, Date date) {
		super();
		this.image = image;
		this.light_source = light_source;
		this.style = style;
		this.brightness = brightness;
		this.light_color = light_color;
		this.date = date;
	}

	public Long getPollution_report_id() {
		return pollution_report_id;
	}

	public void setPollution_report_id(Long pollution_report_id) {
		this.pollution_report_id = pollution_report_id;
	}

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getLight_source() {
		return light_source;
	}
	public void setLight_source(String light_source) {
		this.light_source = light_source;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getBrightness() {
		return brightness;
	}
	public void setBrightness(String brightness) {
		this.brightness = brightness;
	}
	public String getLight_color() {
		return light_color;
	}
	public void setLight_color(String light_color) {
		this.light_color = light_color;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
