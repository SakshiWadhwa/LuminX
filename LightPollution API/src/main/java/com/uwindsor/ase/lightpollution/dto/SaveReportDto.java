package com.uwindsor.ase.lightpollution.dto;

public class SaveReportDto {

	private String name;
	private String email;
	private String image;
	private ReportLocation location;
	private String light_source;
	private String Style;
	private String brightness;
	private String light_color;
	
	public SaveReportDto() {}
	
	public SaveReportDto(String name, String email, String image, ReportLocation location, String light_source,
			String style, String brightness, String light_color) {
		super();
		this.name = name;
		this.email = email;
		this.image = image;
		this.location = location;
		this.light_source = light_source;
		Style = style;
		this.brightness = brightness;
		this.light_color = light_color;
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
}
