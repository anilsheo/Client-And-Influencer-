package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class clientResponse 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long clientResponseId;
	private Long clientId;
	private Long influencerId;
	private String influencerName;
	private String socialMediaName;
	private String interestMessage;
	public clientResponse(Long clientId, Long influencerId, String influencerName, String socialMediaName,
			String interestMessage) {
		super();
		this.clientId = clientId;
		this.influencerId = influencerId;
		this.influencerName = influencerName;
		this.socialMediaName = socialMediaName;
		this.interestMessage = interestMessage;
	}
	public clientResponse() {
		super();
	}
	public Long getClientResponseId() {
		return clientResponseId;
	}
	public void setClientResponseId(Long clientResponseId) {
		this.clientResponseId = clientResponseId;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public Long getInfluencerId() {
		return influencerId;
	}
	public void setInfluencerId(Long influencerId) {
		this.influencerId = influencerId;
	}
	public String getInfluencerName() {
		return influencerName;
	}
	public void setInfluencerName(String influencerName) {
		this.influencerName = influencerName;
	}
	public String getSocialMediaName() {
		return socialMediaName;
	}
	public void setSocialMediaName(String socialMediaName) {
		this.socialMediaName = socialMediaName;
	}
	public String getInterestMessage() {
		return interestMessage;
	}
	public void setInterestMessage(String interestMessage) {
		this.interestMessage = interestMessage;
	}
	
	
	

}
