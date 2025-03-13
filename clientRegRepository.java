package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.clientProfile;
import com.example.demo.model.clientRegistration;

@Repository
public interface clientRegRepository extends JpaRepository<clientRegistration,Long>
{
	@Query("select u from clientRegistration u where u.clientEmail=?1")
	Optional<clientRegistration>findByClientEmail(String clientEmail);

	@Query("select u from clientRegistration u where u.clientId=?1")
	Optional<clientRegistration>findByClientId(Long clientId);
}
