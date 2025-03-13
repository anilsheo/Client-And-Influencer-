package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class clientInterest 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long clientInterestId;
	private Long clientId;
	private Long influencerId;
	private String influencerName;
	private String socialMediaName;
	private String interestMessage;
	public clientInterest(Long clientId, Long influencerId, String influencerName, String socialMediaName,
			String interestMessage) {
		super();
		this.clientId = clientId;
		this.influencerId = influencerId;
		this.influencerName = influencerName;
		this.socialMediaName = socialMediaName;
		this.interestMessage = interestMessage;
	}
	public clientInterest() {
		super();
	}
	public Long getClientInterestId() {
		return clientInterestId;
	}
	public void setClientInterestId(Long clientInterestId) {
		this.clientInterestId = clientInterestId;
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