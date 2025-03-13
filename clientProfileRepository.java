package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.clientProfile;

public interface clientProfileRepository extends JpaRepository<clientProfile,Long> 
{
	@Query("select u from clientProfile u where u.clientId=?1")
	Optional<clientProfile>findByClientId(Long clientId);
	

}

