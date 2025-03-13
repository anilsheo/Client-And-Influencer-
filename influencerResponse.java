package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class influencerResponse 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long influencerResponseId;
	private Long influencerId;
	private Long clientId;
	private String clientName;
	private String productName;
	private String responseMessage;

	
	public influencerResponse(Long influencerId, Long clientId, String clientName, String productName,
			String responseMessage) {
		super();
		this.influencerId = influencerId;
		this.clientId = clientId;
		this.clientName = clientName;
		this.productName = productName;
		this.responseMessage = responseMessage;
	}



	public influencerResponse() {
		super();
	}



	public Long getInfluencerResponseId() {
		return influencerResponseId;
	}



	public void setInfluencerResponseId(Long influencerResponseId) {
		this.influencerResponseId = influencerResponseId;
	}



	public Long getInfluencerId() {
		return influencerId;
	}



	public void setInfluencerId(Long influencerId) {
		this.influencerId = influencerId;
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



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	public String getResponseMessage() {
		return responseMessage;
	}



	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
	
	
	
	

}
