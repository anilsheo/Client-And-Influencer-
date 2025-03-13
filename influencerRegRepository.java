package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.influencerRegistration;



public interface influencerRegRepository extends JpaRepository<influencerRegistration,Long> 
{
	@Query("Select u from influencerRegistration u where u.influencerEmail=?1")
	Optional<influencerRegistration>findByInfluencerEmail(String influencerEmail);
	

	@Query("select u from influencerRegistration u where u.influencerId=?1")
	Optional<influencerRegistration>findByInfluencerId(Long influencerId);
}
