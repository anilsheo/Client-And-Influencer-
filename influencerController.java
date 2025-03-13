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

import com.example.demo.model.clientProfileDTO;
import com.example.demo.model.influencerInterest;
import com.example.demo.model.influencerProfile;
import com.example.demo.model.influencerRegistration;
import com.example.demo.service.influencerService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/influencer")
public class influencerController 
{
	@Autowired
	private influencerService serviceInfluencer;
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody influencerRegistration influencer)
	{
		try
		{
			influencerRegistration obj=serviceInfluencer.register(influencer);
			return ResponseEntity.ok("SignUp is Successfull ! Welcome "+obj.getInfluencerName());
		}
		catch(Exception e )
		{
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody influencerRegistration influencer,HttpSession session)
	{
		try
		{
			
			influencerRegistration loggedInUser=serviceInfluencer.login(influencer, session);
			return ResponseEntity.ok("Login Successfull Welcome ! "+loggedInUser.getInfluencerName());
		}
		catch(Exception e)
		{
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/influencerProfile")
	public ResponseEntity<?> creatingProfile(@RequestBody influencerProfile influencer ,HttpSession session)
	{
		try
		{
			influencerProfile loggedInUser=serviceInfluencer.creatingProfile(influencer, session);
			return ResponseEntity.ok("Profile Created Successfully ! ;"+" Welcome "+loggedInUser.getInfluencerName()+" You're Ready To Ready to Promote Products  ");
		}
		catch(Exception e)
		{
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/showClientProducts")
	public ResponseEntity<List<clientProfileDTO>> showClientProducts(HttpSession session) {
	    try {
	        List<clientProfileDTO> clientProducts = serviceInfluencer.showClientProducts(session);
	        return ResponseEntity.ok(clientProducts);
	    }
	    
	    catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); 
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}

	@PostMapping("/influencerMessageForClient")
	public ResponseEntity<String> influencerMessageForClient( @RequestBody influencerInterest interest, HttpSession session) {
	    try {
	        serviceInfluencer.influencerMessageForClient(interest, session);
	        return ResponseEntity.ok("Your request has been sent. Please wait for a response.");
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
