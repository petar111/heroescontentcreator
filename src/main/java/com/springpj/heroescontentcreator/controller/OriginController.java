package com.springpj.heroescontentcreator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("origin")
public class OriginController {
	
	@GetMapping("welcome")
	public String welcomeOrigin() {
		return "Welcome origin.";
	}

}
