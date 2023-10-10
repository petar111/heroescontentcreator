package com.springpj.heroescontentcreator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springpj.heroescontentcreator.model.dto.OriginDto;
import com.springpj.heroescontentcreator.service.OriginService;

@RestController
@RequestMapping("origin")
public class OriginController {
	
	private static final Logger log = LoggerFactory.getLogger(OriginController.class);
	
	private final OriginService originService;
	
	private final MessageSource messageSoruce;
	
	@Autowired
	public OriginController(OriginService originService,
			MessageSource messageSource) {
		this.originService = originService;
		this.messageSoruce = messageSource;
	}
	
	@GetMapping("{id}")
	public OriginDto findById(@PathVariable Long id) {
		log.info("Finding by id:" + id + " - START");
		OriginDto origin = originService.findById(id);
		log.info("Finding by id:" + id + " - END");
		return origin;
		
	}
	
	@GetMapping("metadata/title")
	public String getTitle() {
		return messageSoruce.getMessage("model.origin.title", null, LocaleContextHolder.getLocale());
	}
	
	
	@PostMapping("add")
	public OriginDto save(@RequestBody OriginDto dto) {
		return originService.save(dto);
	}
	
	@PutMapping("{id}/update")
	public OriginDto update(@RequestBody OriginDto dto, @PathVariable Long id) {
		dto.setId(id);
		return originService.save(dto);
	}
	
	@GetMapping("welcome")
	public String welcomeOrigin() {
		return "Welcome origin.";
	}

}
