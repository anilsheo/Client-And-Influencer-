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
public class clientRegistration 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long clientId;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Set<Role> roles = new HashSet<>();

	private String clientName;
	private String clientEmail;
	private String clientPassword;
	public clientRegistration(Set<Role> roles, String clientName, String clientEmail, String clientPassword) {
		super();
		this.roles = roles;
		this.clientName = clientName;
		this.clientEmail = clientEmail;
		this.clientPassword = clientPassword;
	}
	public clientRegistration() {
		super();
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientEmail() {
		return clientEmail;
	}
	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}
	public String getClientPassword() {
		return clientPassword;
	}
	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}
	@Override
	public String toString() {
		return "clientRegistration [clientId=" + clientId + ", roles=" + roles + ", clientName=" + clientName
				+ ", clientEmail=" + clientEmail + ", clientPassword=" + clientPassword + "]";
	}
		
}