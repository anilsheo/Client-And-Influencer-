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
import com.example.demo.model.clientProfile;
import com.example.demo.model.clientProfileDTO;
import com.example.demo.model.influencerInterest;
import com.example.demo.model.influencerProfile;
import com.example.demo.model.influencerRegistration;
import com.example.demo.model.influencerResponse;
import com.example.demo.repository.clientProfileRepository;
import com.example.demo.repository.influencerInterestRepository;
import com.example.demo.repository.influencerProfileRepository;
import com.example.demo.repository.influencerRegRepository;
import com.example.demo.repository.influencerResponseRepository;
import com.example.demo.repository.roleRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class influencerService 
{
	@Autowired
	private influencerRegRepository influencerRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private roleRepository roleRepo;
	
	@Autowired
	private influencerProfileRepository profileRepo;
	
	
	@Autowired
	private clientProfileRepository clientPRepo;
	
	@Autowired
	private influencerInterestRepository interestRepo;
	
	@Autowired
	private emailService email;
	
	@Autowired
	private influencerResponseRepository responseRepo;
	
	public influencerRegistration register(influencerRegistration influencer)
	{
		Optional<influencerRegistration> existingUser=influencerRepo.findByInfluencerEmail(influencer.getInfluencerEmail());
		if(existingUser.isPresent())
		{
			throw new IllegalArgumentException("Email Is Already Existing ! Please Try Login Method ");
		}
		if(influencer.getRoles()==null || influencer.getRoles().isEmpty())
		{
			throw new IllegalArgumentException("Role Must Be Provided !");
		}
			
		Set<Role> userRoles=new HashSet<>();
		for(Role i :influencer.getRoles())
		{
			Role existingRole=roleRepo.findByName(i.getName())
					.orElseThrow(()->
					new IllegalArgumentException("Invalid Role : "+i.getName()));
			userRoles.add(existingRole);
		}
		
		String hashedPassword=passwordEncoder.encode(influencer.getInfluencerPassword());
		influencerRegistration newInfluencer=new influencerRegistration();
		newInfluencer.setInfluencerName(influencer.getInfluencerName());
		newInfluencer.setInfluencerEmail(influencer.getInfluencerEmail());
		newInfluencer.setInfluencerPassword(hashedPassword);
		newInfluencer.setRoles(userRoles);
		
		return influencerRepo.save(newInfluencer);
		
	}
	
	 public influencerRegistration login(influencerRegistration influencer, HttpSession session) {
		    Optional<influencerRegistration> existingUser = influencerRepo.findByInfluencerEmail(influencer.getInfluencerEmail());
		    if (existingUser.isEmpty()) {
		        throw new IllegalArgumentException("Invalid Email or Password!");
		    }
		    
		    influencerRegistration storedinfluencer = existingUser.get();
		    
		    boolean isMatch = passwordEncoder.matches(influencer.getInfluencerPassword(), storedinfluencer.getInfluencerPassword());
		    
		    if (!isMatch) {
		        throw new IllegalArgumentException("Invalid Email or Password!");
		    }

		    // Store only necessary details in session
		    session.setAttribute("loggedInUserId", storedinfluencer.getInfluencerId());
		    session.setAttribute("loggedInUserRole", storedinfluencer.getRoles());

		    return storedinfluencer;
		}
	 
	 public influencerProfile creatingProfile(influencerProfile influencer ,HttpSession session)
	 {
		 Long loggedInUserId=(Long) session.getAttribute("loggedInUserId");
		 
		 if(loggedInUserId==null)
		 {
			 throw new IllegalArgumentException("User Not Logged In !");
		 }
		 
		 influencerRegistration user=influencerRepo.findById(loggedInUserId)
				 .orElseThrow(()-> new IllegalArgumentException("User Not Found Login Again"));
		 
		 Optional<influencerProfile> existingUser=profileRepo.findByInfluencerId(loggedInUserId);
		 if(existingUser.isPresent())
		 {
			 throw new IllegalArgumentException("Profile already exists! You cannot create another.");
		 }
		 
		 influencerProfile profile=new influencerProfile();
		 profile.setInfluencerId(user.getInfluencerId());
		 profile.setInfluencerName(user.getInfluencerName());
		 profile.setInfluencerEmail(user.getInfluencerEmail());
		 profile.setInfluencerAadhar(influencer.getInfluencerAadhar());
		 profile.setInfluencerPhoneNumber(influencer.getInfluencerPhoneNumber());
		 profile.setSocialMediaName(influencer.getSocialMediaName());
		 profile.setInstagramFollowers(influencer.getInstagramFollowers());
		 profile.setYoutubeSubscribers(influencer.getYoutubeSubscribers());
		 profile.setSalary(influencer.getSalary());
		 
		 
		 profileRepo.save(profile);
		 
		 return profile;
	 }
	 
	 
	 public List<clientProfileDTO> showClientProducts(HttpSession session)
	 {
		 Long loggedInUserId=(Long) session.getAttribute("loggedInUserId");
		 if(loggedInUserId==null)
		 {
			 throw new IllegalArgumentException("User Not Logged In");
		 }
		 
		if(!influencerRepo.existsById(loggedInUserId))
		{
			throw new RuntimeException ("User Not Found.Please Login Again");
		}
		 
		 List<clientProfile> clientProfiles=clientPRepo.findAll();
		 
		return clientProfiles.stream().map(clients-> new clientProfileDTO(
				 clients.getClientId(),
				 clients.getClientName(),
				 clients.getProductName(),
				 clients.getProductDescription(),
				 clients.getProductPrice(),
				 clients.getBudget()
				 )
		 ).collect(Collectors.toList());
	 }
	 
	 
	 public influencerInterest influencerMessageForClient(influencerInterest interest, HttpSession session) 
	 {
	     Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");

	     if (loggedInUserId == null) {
	         throw new IllegalArgumentException("User Not Logged In");
	     }

	     Optional<influencerProfile> checkInfluencer =profileRepo.findById(loggedInUserId);
	     if (checkInfluencer.isEmpty()) 
	     {
	         throw new RuntimeException("User Not Found, Please Login");
	     }
	     
	     influencerProfile influencerProfile = checkInfluencer.get(); // Current influencer

	     Optional<clientProfile> checkClient = clientPRepo.findByClientId(interest.getClientId());
	     if (checkClient.isEmpty()) 
	     {
	         throw new RuntimeException("Client Not Found");
	     }
	     clientProfile client = checkClient.get();

	     influencerInterest influencer = new influencerInterest();
	     influencer.setInfluencerId(loggedInUserId);
	     influencer.setClientId(interest.getClientId());
	     influencer.setClientName(client.getClientName());
	     influencer.setProductName(interest.getProductName());
	     influencer.setInterestMessage(interest.getInterestMessage());

	     interestRepo.save(influencer);

	     String clientEmail = client.getClientEmail();
	     String subject = "Influencer is Interested In Your Product";
	     String messageText = "Dear " + client.getClientName() + ",\n\n"
	             + influencerProfile.getInfluencerName() + " is interested in your product: " + interest.getProductName() + ".\n"
	             + "Influencer Message For You : " + interest.getInterestMessage() + "\n\n"
	             + "Best Regards,\nYour Team";

	     email.sendEmail(clientEmail, subject, messageText);

	     return influencer;
	 }
	 
	 public influencerResponse influencerResponseForClient(influencerResponse response,HttpSession session)
	 {
		 Long loggedInUserId=(Long) session.getAttribute("loggedInUserId");
		 
		 if(loggedInUserId==null)
		 {
			 throw new IllegalArgumentException("User Not Logged In");
		 }
		 
		 Optional<influencerProfile> checkInfluencer=profileRepo.findById(loggedInUserId);
		 
		 if(checkInfluencer.isEmpty())
		 {
			 throw new RuntimeException("User not Found , Please Login Again");
			 
		 }
		 
		 influencerProfile influencer=checkInfluencer.get();
		 
		 Optional<clientProfile> checkClient=clientPRepo.findByClientId(response.getClientId());
		 
		 if(checkClient.isEmpty())
		 {
			 throw new RuntimeException("Client Not Found Please Check It ");
		 }
		 
		 clientProfile client=checkClient.get();
		 
		 influencerResponse obj=new influencerResponse();
		 obj.setInfluencerId(loggedInUserId);
		 obj.setClientId(response.getClientId());
		 obj.setClientName(client.getClientName());
		 obj.setProductName(response.getProductName());
		 obj.setResponseMessage(response.getResponseMessage());
		 
		 responseRepo.save(obj);
		 
		 String clientEmail=client.getClientEmail();
		 String subject="Influencer Response Regarding Your Request ";
		 String messageText="Dear "+client.getClientName()+",\n\n"
				 +" Influencer "+influencer.getInfluencerName()+" Replied To You "+"\n\n" 
				 +response.getResponseMessage() + "\n\n"
	             + "Best Regards,\nYour Team";
		 
		 email.sendEmail(clientEmail, subject, messageText);
		 
		 return obj;
		 
	 }

}
