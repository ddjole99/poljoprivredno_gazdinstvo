package rs.ac.bg.fon.poljoprivredno_gazdinstvo.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.filters.JwtAuthenticationFilter;

@AllArgsConstructor
@Configuration
public class SecurityConfig {

	private final UserDetailsService userDetailsService;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		var provider=new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	 @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { 
		 http 
	          .csrf(csrf -> csrf.disable())
	          .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
	          .sessionManagement(c->c.sessionCreationPolicy(SessionCreationPolicy.STATELESS ))
	          .authorizeHttpRequests(auth -> auth
	        		  .requestMatchers(HttpMethod.POST,"/api/administratori").permitAll()
	        		  .requestMatchers(HttpMethod.POST,"/api/auth/login").permitAll()
	        		  .requestMatchers(HttpMethod.POST,"/api/auth/register").permitAll()
	        		  .anyRequest().permitAll() // dozvolio sam sve endpointe radi brzeg testiranja 
	        ).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		 
		 return http.build();
	      
	    }
}
