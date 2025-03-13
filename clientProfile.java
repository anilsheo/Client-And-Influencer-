package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class clientProfile 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long clientProfile;
	private Long clientId;
	private String clientName;
	private String clientEmail;
	private String clientAadhar;
	private String clientPhoneNumber;
	private String productName;
	private String productDescription;
	private Double productPrice;
	private Double budget;
	public clientProfile() {
		super();
	}
	public clientProfile(Long clientId, String clientName, String clientEmail, String clientAadhar,
			String clientPhoneNumber, String productName, String productDescription, Double productPrice,
			Double budget) {
		super();
		this.clientId = clientId;
		this.clientName = clientName;
		this.clientEmail = clientEmail;
		this.clientAadhar = clientAadhar;
		this.clientPhoneNumber = clientPhoneNumber;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.budget = budget;
	}
	public Long getClientProfile() {
		return clientProfile;
	}
	public void setClientProfile(Long clientProfile) {
		this.clientProfile = clientProfile;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
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
	public String getClientAadhar() {
		return clientAadhar;
	}
	public void setClientAadhar(String clientAadhar) {
		this.clientAadhar = clientAadhar;
	}
	public String getClientPhoneNumber() {
		return clientPhoneNumber;
	}
	public void setClientPhoneNumber(String clientPhoneNumber) {
		this.clientPhoneNumber = clientPhoneNumber;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	public Double getBudget() {
		return budget;
	}
	public void setBudget(Double budget) {
		this.budget = budget;
	}
	@Override
	public String toString() {
		return "clientProfile [clientProfile=" + clientProfile + ", clientId=" + clientId + ", clientName=" + clientName
				+ ", clientEmail=" + clientEmail + ", clientAadhar=" + clientAadhar + ", clientPhoneNumber="
				+ clientPhoneNumber + ", productName=" + productName + ", productDescription=" + productDescription
				+ ", productPrice=" + productPrice + ", budget=" + budget + "]";
	}
	
	
}
