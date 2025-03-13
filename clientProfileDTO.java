package com.example.demo.model;

public class clientProfileDTO 
{
	private Long clientId;
	private String clientName;
	private String productName;
	private String productDescription;
	private Double productPrice;
	private Double budget;
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
	public clientProfileDTO(Long clientId, String clientName, String productName, String productDescription,
			Double productPrice, Double budget) {
		super();
		this.clientId = clientId;
		this.clientName = clientName;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.budget = budget;
	}
	public clientProfileDTO() {
		super();
	}
	
	

}
