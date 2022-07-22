package com.uwindsor.ase.lightpollution.dto;

import java.util.Date;

public class PollutionReports {

	private String name;
	private String email;
	private String image;
	private ReportLocation location;
	private String light_source;
	private String Style;
	private String brightness;
	private String light_color;
	private Date date;
	
	public PollutionReports() {}

	public PollutionReports(String name, String email, String image, ReportLocation location, String light_source,
			String style, String brightness, String light_color, Date date) {
		super();
		this.name = name;
		this.email = email;
		this.image = image;
		this.location = location;
		this.light_source = light_source;
		Style = style;
		this.brightness = brightness;
		this.light_color = light_color;
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public ReportLocation getLocation() {
		return location;
	}

	public void setLocation(ReportLocation location) {
		this.location = location;
	}

	public String getLight_source() {
		return light_source;
	}

	public void setLight_source(String light_source) {
		this.light_source = light_source;
	}

	public String getStyle() {
		return Style;
	}

	public void setStyle(String style) {
		Style = style;
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
