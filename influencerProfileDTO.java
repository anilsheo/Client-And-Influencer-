package com.example.demo.model;

public class influencerProfileDTO {

	private Long influencerId;
	private String influencerName;
	private String socialMediaName;
	private Long instagramFollowers;
	private Long youtubeSubscribers;
	private Double salary;
	public influencerProfileDTO(Long influencerId, String influencerName, String socialMediaName,
			Long instagramFollowers, Long youtubeSubscribers, Double salary) {
		super();
		this.influencerId = influencerId;
		this.influencerName = influencerName;
		this.socialMediaName = socialMediaName;
		this.instagramFollowers = instagramFollowers;
		this.youtubeSubscribers = youtubeSubscribers;
		this.salary = salary;
	}
	public influencerProfileDTO() {
		super();
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
	
	

}
