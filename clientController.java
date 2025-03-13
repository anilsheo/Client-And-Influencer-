package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.clientInterest;
import com.example.demo.model.clientProfile;
import com.example.demo.model.clientRegistration;
import com.example.demo.model.influencerProfileDTO;
import com.example.demo.service.clientService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/client")
public class clientController 
{
	@Autowired
	private clientService serviceClient;
	
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody clientRegistration client) 
	{
	    try {
	        clientRegistration obj = serviceClient.register(client);
	        return ResponseEntity.ok("SignUp is Successful! Welcome " + obj.getClientName());
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body(e.getMessage()); 
	    }
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody clientRegistration client, HttpSession session) {
	    try {
	        clientRegistration loggedInUser = serviceClient.login(client, session);
	        
	        return ResponseEntity.ok("Login Successful! Welcome " + loggedInUser.getClientName());
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body(e.getMessage()); 
	    }
	}

	@PostMapping("/clientProfile")
	public ResponseEntity<?> creatingProfile(@RequestBody clientProfile client,HttpSession session)
	{
		try
		{
			clientProfile loggedInUser=serviceClient.creatingProfile(client, session);
			return ResponseEntity.ok("Profile Created Successfully ! ;"+" Welcome "+loggedInUser.getClientName()+" Your Product Is To Ready to Promote ");
		}
		catch(Exception e)
		{
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/showAllInfluencers")
	public ResponseEntity<List<influencerProfileDTO>> showAllInfluencers(HttpSession session)
	{
		try
		{
			List<influencerProfileDTO> influencerProfiles=serviceClient.showAllInfluencers(session);
			return ResponseEntity.ok(influencerProfiles);
		}
		catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); 
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
		
	}

	@PostMapping("/clientMessageForInfluencer")
	public ResponseEntity<String>  clientMessageForInfluencer(@RequestBody clientInterest interest,HttpSession session )
	{
		try
		{
			
			serviceClient.clientMessageForInfluencer(interest, session);
			return ResponseEntity.ok("Your Request was Sended Please Wait For Response");
		}
		
		catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong. Please try again.");
	    	}
	}
}
