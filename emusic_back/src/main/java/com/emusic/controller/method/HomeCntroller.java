package com.emusic.controller.method;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeCntroller {
	@RequestMapping({"/", "/product/{id}", "/browse", "/cart"})
	public String home() {
		System.out.println("home");
		return "index.html";
	}	
}
