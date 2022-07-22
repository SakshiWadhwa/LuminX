package com.uwindsor.ase.lightpollution.model;

import java.io.Serializable;
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
@Table(name="user_details")
public class UserDetails implements Serializable{

	private static final long serialVersionUID = 7458570202877091199L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long user_id;
	
	private String name;
	private String mail;
	
	@OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UserPollutionReportLink> userPollutionReportLink;
	
	public UserDetails() {
		
	}
	
	public UserDetails(String name, String mail) {
		super();
		this.name = name;
		this.mail = mail;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
}
