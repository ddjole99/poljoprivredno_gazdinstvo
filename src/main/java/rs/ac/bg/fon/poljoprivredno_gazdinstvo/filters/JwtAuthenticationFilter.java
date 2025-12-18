package rs.ac.bg.fon.poljoprivredno_gazdinstvo.filters;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.service.JwtService;

@AllArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		var autHeader = request.getHeader("Authorization");

		if (autHeader == null || !autHeader.startsWith("Bearer ")) {

			filterChain.doFilter(request, response);
			return;
		}

		var token = autHeader.replace("Bearer ", "");
		if(!jwtService.validateToken(token)) {
			filterChain.doFilter(request, response);
			return;
		}
		
		var authentication = new UsernamePasswordAuthenticationToken(jwtService.getEmailFromToken(token), null,null);
	
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		filterChain.doFilter(request, response);
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
	    return request.getServletPath().startsWith("/h2-console");
	}
}