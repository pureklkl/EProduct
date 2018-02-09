package com.emusic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeCntroller {
	@RequestMapping("/")
	public String home() {
		System.out.println("home");
		return "index";
	}
}
