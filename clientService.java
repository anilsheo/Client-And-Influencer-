package com.example.demo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.model.clientInterest;
import com.example.demo.model.clientProfile;
import com.example.demo.model.clientRegistration;
import com.example.demo.model.clientResponse;
import com.example.demo.model.influencerProfile;
import com.example.demo.model.influencerProfileDTO;
import com.example.demo.repository.clientInterestRepository;
import com.example.demo.repository.clientProfileRepository;
import com.example.demo.repository.clientRegRepository;
import com.example.demo.repository.clientResponseRepository;
import com.example.demo.repository.influencerProfileRepository;
import com.example.demo.repository.roleRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class clientService 
{
	@Autowired
	private clientRegRepository clientRepo;
	
	 @Autowired
	private PasswordEncoder passwordEncoder;
	 
	 @Autowired
	 private roleRepository roleRepo;
	 
	 @Autowired
	 private clientProfileRepository cProfileRepo;
	 
	 @Autowired
	 private influencerProfileRepository influencerPRepo;
	 
	 @Autowired
	 private clientInterestRepository InterestRepository;
	 
	 @Autowired
	 private clientResponseRepository responseRepo;
	
	 
	 @Autowired
	 private emailService email;
	 
	 
	 public clientRegistration register(clientRegistration client) {
		    Optional<clientRegistration> existingUser = clientRepo.findByClientEmail(client.getClientEmail());
		    if (existingUser.isPresent()) {
		        throw new IllegalArgumentException("Email is already existing! Please try login.");
		    }

		    if (client.getRoles() == null || client.getRoles().isEmpty()) {
		        throw new IllegalArgumentException("Role must be provided!");
		    }

		    Set<Role> userRoles = new HashSet<>();
		    for (Role role : client.getRoles()) {
		        Role existingRole = roleRepo.findByName(role.getName())
		                .orElseThrow(() -> new IllegalArgumentException("Invalid role: " + role.getName()));
		        userRoles.add(existingRole);
		    }

		    String hashedPassword = passwordEncoder.encode(client.getClientPassword());

		    clientRegistration newClient = new clientRegistration();
		    newClient.setClientName(client.getClientName());
		    newClient.setClientEmail(client.getClientEmail());
		    newClient.setClientPassword(hashedPassword);
		    newClient.setRoles(userRoles);  // Assign selected roles only

		    return clientRepo.save(newClient);
		}


	
	 public clientRegistration login(clientRegistration client, HttpSession session) {
		    Optional<clientRegistration> existingUser = clientRepo.findByClientEmail(client.getClientEmail());
		    
		    if (existingUser.isEmpty()) {
		        throw new IllegalArgumentException("Invalid Email or Password!");
		    }
		    
		    clientRegistration clientt = existingUser.get();
		    
		    boolean isMatch = passwordEncoder.matches(client.getClientPassword(), clientt.getClientPassword());
		    
		    if (!isMatch) {
		        throw new IllegalArgumentException("Invalid Email or Password!");
		    }
		    session.setAttribute("loggedInUserId", clientt.getClientId());
		    session.setAttribute("loggedInUserRole", clientt.getRoles());

		    return clientt;
		}
	 
	 
	 public clientProfile creatingProfile(clientProfile client,HttpSession session)
	 {
		 
		  Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");

		    if (loggedInUserId == null) 
		    {
		        throw new IllegalArgumentException("User not logged in!");
		    }
		    
		 clientRegistration user = clientRepo.findById(loggedInUserId)
		            .orElseThrow(() -> new IllegalArgumentException("Client not found. Please log in again."));

		 
		        Optional<clientProfile> existingProfile = cProfileRepo.findByClientId(loggedInUserId);
		        
		        if (existingProfile.isPresent()) 
		        {
		            throw new IllegalArgumentException("Profile already exists! You cannot create another.");
		        }
		       
			 clientProfile profile=new clientProfile();
			 profile.setClientId(user.getClientId());
			 profile.setClientName(user.getClientName());
			 profile.setClientEmail(user.getClientEmail());
			 profile.setClientAadhar(client.getClientAadhar());
			 profile.setClientPhoneNumber(client.getClientPhoneNumber());
			 profile.setProductName(client.getProductName());
			 profile.setProductDescription(client.getProductDescription());
			 profile.setProductPrice(client.getProductPrice());
			 profile.setBudget(client.getBudget());
			 
			 cProfileRepo.save(profile);
			 
			 return profile;
		 }
	 
	 
	 public List<influencerProfileDTO> showAllInfluencers(HttpSession session)
	 {
		 
		 Long loggedInUserId=(Long) session.getAttribute("loggedInUserId");
		 
		 if(loggedInUserId==null)
		 {
			 throw new IllegalArgumentException("User Not Logged In");
		 }
		 
		 if(!clientRepo.existsById(loggedInUserId))
		 {
			 throw new RuntimeException("User Not Found, Please Login First");
		 }
		 
		 List<influencerProfile> influencerProfiles= influencerPRepo.findAll();
		 
		 return influencerProfiles.stream()
				 .map(influencer-> new influencerProfileDTO(
						 influencer.getInfluencerId(),
						 influencer.getInfluencerName(),
						 influencer.getSocialMediaName(),
						 influencer.getInstagramFollowers(),
						 influencer.getYoutubeSubscribers(),
						 influencer.getSalary()))
				 .collect(Collectors.toList());
		 
		 
	 }
	 
	 public clientInterest clientMessageForInfluencer(clientInterest interest ,HttpSession session)
	 {
		 Long loggedInUserId=(Long) session.getAttribute("loggedInUserId");
		 
		 if(loggedInUserId==null)
		 {
			 throw new IllegalArgumentException("User Not Logged In");
		 }
		 
		Optional <clientProfile> checkClient=cProfileRepo.findById(loggedInUserId);
		 
		 if(!clientRepo.existsById(loggedInUserId))
		 {
			 throw new RuntimeException("User Not Found , Please Login First");
		 }
		 
		 clientProfile clientprofile=checkClient.get();
		 
		 Optional<influencerProfile> checkInfluencer=influencerPRepo.findByInfluencerId(interest.getInfluencerId());
		 
		 if(checkInfluencer.isEmpty())
		 {
			 throw new RuntimeException("Influencer Not Found");
		 }
		 
			 influencerProfile influencer=checkInfluencer.get();
			 clientInterest client=new clientInterest();
			 client.setClientId(loggedInUserId);			 client.setInfluencerId(interest.getInfluencerId());
			 client.setInfluencerName(influencer.getInfluencerName());
			 client.setSocialMediaName(influencer.getSocialMediaName());
			 client.setInterestMessage(interest.getInterestMessage());
			 
			 InterestRepository.save(client);
			 
			 String influencerEmail=influencer.getInfluencerEmail();
			 String subject="Client Interested In Your Profile";
			 String messageText="Dear "+influencer.getInfluencerName()+",\n\n"
					 +clientprofile.getClientName()+" is Interested In Your Profile :" +" He Think Your'e the Right Fit for Promote their  Poduct "+"\n"
					 +"Message :"+client.getInterestMessage() + "\n\n"
					 + "Best Regards,\nOur Team";

					 
			 email.sendEmail(influencerEmail,subject,messageText);
			 
			 return client;
	
	
	 }
	 
	 public clientResponse clientResponseForInfluencer(clientResponse response ,HttpSession session)
	 {
		 Long loggedInUserId=(Long) session.getAttribute("loggedInUserId");
		 
		 if(loggedInUserId==null)
		 {
			 throw new IllegalArgumentException("User Not Logged In");
		 }
		 
		 Optional<clientProfile> checkClient=cProfileRepo.findById(loggedInUserId);
		 
		 if(checkClient.isEmpty())
		 {
			 throw new RuntimeException("User Not Found , Please Login Again");
		 }
		 
		 clientProfile client=checkClient.get();
		 
		 Optional<influencerProfile> checkInfluencer=influencerPRepo.findById(response.getInfluencerId());
		 
		 influencerProfile influencer=checkInfluencer.get();
		 
		 clientResponse obj=new clientResponse();
		obj.setClientId(loggedInUserId);
		obj.setInfluencerId(response.getInfluencerId());
		obj.setInfluencerName(influencer.getInfluencerName());
		obj.setSocialMediaName(influencer.getSocialMediaName());
		obj.setInterestMessage(response.getInterestMessage());
		
		responseRepo.save(obj);
		
		
		 String influencerEmail=influencer.getInfluencerEmail();
		 String subject="Client Response Regarding Your Request";
		 String messageText="Dear "+influencer.getInfluencerName()+",\n\n"
				 +" Client "+client.getClientName()+" Replied To You "+"\n\n" 
				 +obj.getInterestMessage() + "\n\n"
				 + "Best Regards,\nOur Team";

				 
		 email.sendEmail(influencerEmail,subject,messageText);
		 
		 return obj;
		 
		
	 }
		 
	 
	 }

