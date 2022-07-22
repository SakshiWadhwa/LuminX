package com.uwindsor.ase.lightpollution.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="recommend_place")
public class RecommendPlace implements Serializable{

	private static final long serialVersionUID = 2650919651906005115L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String category;
	private String name;
	private String type;
	private String city;
	private String province;
	private String continent;
	
	public RecommendPlace() {}
	
	public RecommendPlace(String category, String name, String type, String city, String province, String continent) {
		super();
		this.category = category;
		this.name = name;
		this.type = type;
		this.city = city;
		this.province = province;
		this.continent = continent;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	@Override
	public String toString() {
		return "RecommendPlace [id=" + id + ", category=" + category + ", name=" + name + ", type=" + type + ", city="
				+ city + ", province=" + province + ", continent=" + continent + "]";
	}
}
