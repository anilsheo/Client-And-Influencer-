package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class influencerInterest 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long influencerInterestId;
	private Long influencerId;
	private Long clientId;
	private String clientName;
	private String productName;
	private String interestMessage;
	public influencerInterest(Long influencerId, Long clientId, String clientName, String productName,
			String interestMessage) {
		super();
		this.influencerId = influencerId;
		this.clientId = clientId;
		this.clientName = clientName;
		this.productName = productName;
		this.interestMessage = interestMessage;
	}
	public influencerInterest() {
		super();
	}
	public Long getInfluencerInterestId() {
		return influencerInterestId;
	}
	public void setInfluencerInterestId(Long influencerInterestId) {
		this.influencerInterestId = influencerInterestId;
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
	public String getInterestMessage() {
		return interestMessage;
	}
	public void setInterestMessage(String interestMessage) {
		this.interestMessage = interestMessage;
	}
	

}
