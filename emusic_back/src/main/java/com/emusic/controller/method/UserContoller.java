package com.emusic.controller.method;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.emusic.auth.IAuthenticationFacade;
import com.emusic.controller.dto.UserInfoDto;
import com.emusic.controller.dto.UserRegDto;
import com.emusic.dao.exception.DuplicateEmailException;
import com.emusic.service.CustomerService;
import com.emusic.service.UserService;

@RequestMapping("/user")
@Controller
@EnableWebMvc
public class UserContoller {
    @Autowired
    private IAuthenticationFacade authenticationFacade;
    @Autowired
    private AuthenticationTrustResolver authResolver;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;
    
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getUser() {
		return "user.html";
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String showRegistrationForm(WebRequest request, Model model) {
	    UserRegDto userDto = new UserRegDto();
	    model.addAttribute("user", userDto);
	    return "regist.html";
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView registerUserAccount(
	  @ModelAttribute("user") @Valid UserRegDto userRegDto, 
	  BindingResult result, 
	  HttpServletRequest request, 
	  Errors errors) {
	    if (result.hasErrors()) {
	        return new ModelAndView("regist.html", "user", userRegDto);
	    }
	    try {
	    	customerService.addNewCustomer(userRegDto);
	    } catch(DuplicateEmailException e) {
	    	result.rejectValue("email", "message.regError");
	    	return new ModelAndView("regist.html", "user", userRegDto);
	    }
    	userService.login(request, userRegDto.getEmail(), userRegDto.getPassword());
    	return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/api/getuserauth", method = RequestMethod.GET)
	@ResponseBody
	public UserInfoDto getUserAuth() {
		Authentication auth = authenticationFacade.getAuthentication();
		UserInfoDto user = new UserInfoDto();
		if(!authResolver.isAnonymous(auth)) {
			System.out.println(auth.getName());
			user.setEmail(auth.getName());
		}
		return user;
	}
}
