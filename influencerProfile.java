package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class influencerProfile 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long influencerProfile;
	private Long influencerId;
	private String influencerName;
	private String influencerEmail;
	private String influencerAadhar;
	private String influencerPhoneNumber;
	private String socialMediaName;
	private Long instagramFollowers;
	private Long youtubeSubscribers;
	private Double salary;
	public influencerProfile(Long influencerId, String influencerName, String influencerEmail, String influencerAadhar,
			String influencerPhoneNumber, String socialMediaName, Long instagramFollowers, Long youtubeSubscribers,
			Double salary) {
		super();
		this.influencerId = influencerId;
		this.influencerName = influencerName;
		this.influencerEmail = influencerEmail;
		this.influencerAadhar = influencerAadhar;
		this.influencerPhoneNumber = influencerPhoneNumber;
		this.socialMediaName = socialMediaName;
		this.instagramFollowers = instagramFollowers;
		this.youtubeSubscribers = youtubeSubscribers;
		this.salary = salary;
	}
	public influencerProfile() {
		super();
	}
	public Long getInfluencerProfile() {
		return influencerProfile;
	}
	public void setInfluencerProfile(Long influencerProfile) {
		this.influencerProfile = influencerProfile;
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
	public String getInfluencerEmail() {
		return influencerEmail;
	}
	public void setInfluencerEmail(String influencerEmail) {
		this.influencerEmail = influencerEmail;
	}
	public String getInfluencerAadhar() {
		return influencerAadhar;
	}
	public void setInfluencerAadhar(String influencerAadhar) {
		this.influencerAadhar = influencerAadhar;
	}
	public String getInfluencerPhoneNumber() {
		return influencerPhoneNumber;
	}
	public void setInfluencerPhoneNumber(String influencerPhoneNumber) {
		this.influencerPhoneNumber = influencerPhoneNumber;
	}
	public String getSocialMediaName() {
		return socialMediaName;
	}
	public void setSocialMediaName(String socialMediaName) {
		this.socialMediaName = socialMediaName;
	}
	public Long getInstagramFollowers() {
		return instagramFollowers;
	}
	public void setInstagramFollowers(Long instagramFollowers) {
		this.instagramFollowers = instagramFollowers;
	}
	public Long getYoutubeSubscribers() {
		return youtubeSubscribers;
	}
	public void setYoutubeSubscribers(Long youtubeSubscribers) {
		this.youtubeSubscribers = youtubeSubscribers;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "influencerProfile [influencerProfile=" + influencerProfile + ", influencerId=" + influencerId
				+ ", influencerName=" + influencerName + ", influencerEmail=" + influencerEmail + ", influencerAadhar="
				+ influencerAadhar + ", influencerPhoneNumber=" + influencerPhoneNumber + ", socialMediaName="
				+ socialMediaName + ", instagramFollowers=" + instagramFollowers + ", youtubeSubscribers="
				+ youtubeSubscribers + ", salary=" + salary + "]";
	}
	
	
	
	
}