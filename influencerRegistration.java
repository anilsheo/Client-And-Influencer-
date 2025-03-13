package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class influencerRegistration 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long influencerId;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Set<Role> roles = new HashSet<>();

	private String influencerName;
	private String influencerEmail;
	private String influencerPassword;
	public influencerRegistration(Set<Role> roles, String influencerName, String influencerEmail,
			String influencerPassword) {
		super();
		
		this.roles = roles;
		this.influencerName = influencerName;
		this.influencerEmail = influencerEmail;
		this.influencerPassword = influencerPassword;
	}
	public influencerRegistration() {
		super();
	}
	public Long getInfluencerId() {
		return influencerId;
	}
	public void setInfluencerId(Long influencerId) {
		this.influencerId = influencerId;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getInfluencerName() {
		return influencerName;
	}
	public void setInfluencerName(String influencerName) {
		this.influencerName = influencerName;
	}
	public String getInfluencerEmail() {
		return influencerEmail;
	}
	public void setInfluencerEmail(String influencerEmail) {
		this.influencerEmail = influencerEmail;
	}
	public String getInfluencerPassword() {
		return influencerPassword;
	}
	public void setInfluencerPassword(String influencerPassword) {
		this.influencerPassword = influencerPassword;
	}
	@Override
	public String toString() {
		return "influencerRegistration [influencerId=" + influencerId + ", roles=" + roles + ", influencerName="
				+ influencerName + ", influencerEmail=" + influencerEmail + ", influencerPassword=" + influencerPassword
				+ "]";
	}
	
	
}
