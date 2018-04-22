package com.emusic.controller.method;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

public class EmusicAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	private String defaultTargetUrl = "/";
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private RequestCache requestCache = new HttpSessionRequestCache();
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication)
      throws IOException {
  
	    handle(request, response, authentication);
	    clearAuthenticationAttributes(request);
    }
	
    protected void handle(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication)
      throws IOException {
  
        String targetUrl = determineTargetUrl(request, response);
 
        if (response.isCommitted()) {
            //logger.debug(
             //       "Response has already been committed. Unable to redirect to "
                //    + targetUrl);
            return;
        }
 
        redirectStrategy.sendRedirect(request, response, targetUrl);
        clearAuthenticationAttributes(request);
   }
	
	protected String determineTargetUrl(HttpServletRequest request,
			HttpServletResponse response) {
		String targetUrl = null;
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		if (savedRequest != null) {
			targetUrl = savedRequest.getRedirectUrl();
			targetUrl =  removeApi(targetUrl);
			System.out.println("Redirecting to DefaultSavedRequest Url: " + targetUrl);
			return targetUrl;
		}
		
		targetUrl = request.getHeader("Referer");
		if(StringUtils.hasText(targetUrl) && targetUrl.indexOf("login") < 0) {
			targetUrl =  removeApi(targetUrl);
			System.out.println(targetUrl);
			return targetUrl;
		}

		return defaultTargetUrl;
	}
	
	String removeApi(String targetUrl) {
		int api = targetUrl.indexOf("/api");
		if(api > 0) {
			targetUrl = targetUrl.substring(0, api) + targetUrl.substring(api + "/api".length());
		}
		return targetUrl;
	}
	
    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
 
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
	public void setRequestCache(RequestCache requestCache) {
		this.requestCache = requestCache;
	}
}
