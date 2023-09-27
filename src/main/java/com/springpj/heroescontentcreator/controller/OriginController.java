package com.springpj.heroescontentcreator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springpj.heroescontentcreator.model.dto.OriginDto;
import com.springpj.heroescontentcreator.service.OriginService;

@RestController
@RequestMapping("origin")
public class OriginController {
	
	private final OriginService originService;
	
	@Autowired
	public OriginController(OriginService originService) {
		this.originService = originService;
	}
	
	@PostMapping("save")
	public OriginDto save(@RequestBody OriginDto dto) {
		return originService.save(dto);
	}
	
	@GetMapping("welcome")
	public String welcomeOrigin() {
		return "Welcome origin.";
	}

}
