package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class securityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    return http
	            .csrf(csrf -> csrf.disable()) 
	            .authorizeHttpRequests(auth -> auth
	            	    .requestMatchers("/client/register", "/client/login").permitAll() 
	            	    .requestMatchers("/influencer/register" , "/influencer/login").permitAll()
	            	    .requestMatchers("/client/clientProfile").permitAll()
	            	    .requestMatchers("/influencer/influencerProfile").permitAll()
	            	    .requestMatchers("/influencer/showClientProducts").permitAll()
	            	    .requestMatchers("/client/showAllInfluencers").permitAll()
	            	    .requestMatchers("/influencer/influencerMessageForClient").permitAll()
	            	    .requestMatchers("/client/clientMessageForInfluencer").permitAll()// ✅ Allow profile creation
	            	    .requestMatchers("/admin/**").hasRole("ADMIN")
	            	    .requestMatchers("/client/**").hasRole("CLIENT")
	            	    .requestMatchers("/influencer/**").hasRole("INFLUENCER")
	            	    .anyRequest().authenticated()
	            )
	            .httpBasic(AbstractHttpConfigurer::disable) 
	            .sessionManagement(session -> session
	                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // ✅ Create session only if needed
	            )
	            .build();
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

//package com.example.demo;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class securityConfig {
//
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	    return http
//	            .csrf(csrf -> csrf.disable()) // Disable CSRF for testing (enable in production)
//	            .authorizeHttpRequests(auth -> auth
//	            	    .requestMatchers("/client/register", "/client/login").permitAll() // ✅ Allow register & login
//	            	    .requestMatchers("/influencer/register" , "/influencer/login").permitAll()
//	            	    .requestMatchers("/influencer/clientProfile").permitAll()
//	            	    .requestMatchers("/admin/**").hasRole("ADMIN")
//	            	    .requestMatchers("/client/**").hasRole("CLIENT")
//	            	    .requestMatchers("/influencer/**").hasRole("INFLUENCER")
//	            	    .anyRequest().authenticated()
//
//	            )
//	            .formLogin(form -> form
//	                    .loginPage("/login")
//	                    .defaultSuccessUrl("/home", true)
//	                    .permitAll()
//	            )
//	            .logout(logout -> logout
//	                    .logoutUrl("/logout")
//	                    .logoutSuccessUrl("/login?logout")
//	            )
//	            .sessionManagement(session -> session
//	                    .sessionCreationPolicy(SessionCreationPolicy.ALWAYS) 
//	            )
//	            .build();
//	}
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//
//}
