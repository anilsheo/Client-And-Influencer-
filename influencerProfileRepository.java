package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.influencerProfile;


public interface influencerProfileRepository extends JpaRepository <influencerProfile,Long> 
{
	
@Query("select u from influencerProfile u where u.influencerId=?1")
Optional<influencerProfile>findByInfluencerId(Long influencerId);
	
}
