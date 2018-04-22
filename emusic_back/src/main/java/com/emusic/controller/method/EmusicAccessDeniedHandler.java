package com.emusic.controller.method;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

public class EmusicAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(
      HttpServletRequest request,
      HttpServletResponse response, 
      AccessDeniedException exc) throws IOException, ServletException {
         
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  System.out.println("access denied");
	  if (auth != null) {
        System.out.println("User: " + auth.getName() 
          + " attempted to access the protected URL: "
              + request.getRequestURI());
            System.out.println(exc.getMessage());
	  }
	 
	  response.sendRedirect(request.getContextPath() + "/");
    }
	
}
