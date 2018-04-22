package com.emusic.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import com.emusic.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier("authenticationManager")
    protected AuthenticationManager authenticationManager;
    
	@Override
	public void login(HttpServletRequest request, String username, String password) {
	    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
	    authToken.setDetails(new WebAuthenticationDetails(request));
	    
	    // generate session if one doesn't exist
	    request.getSession();
	    
	    Authentication authentication = authenticationManager.authenticate(authToken);
	     
	    SecurityContextHolder.getContext().setAuthentication(authentication);
		
	}

}
