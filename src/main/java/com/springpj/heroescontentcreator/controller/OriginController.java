package com.springpj.heroescontentcreator.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springpj.heroescontentcreator.model.dto.OriginDto;
import com.springpj.heroescontentcreator.model.dto.OriginVersionDto;
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
	public EntityModel<OriginDto> findById(@PathVariable Long id) {
		log.info("Finding by id:" + id + " - START");
		OriginDto origin = originService.findById(id);
		log.info("Finding by id:" + id + " - END");
		
		EntityModel<OriginDto> originModel = EntityModel.of(origin);
		
		Link selfLink = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
						.findById(origin.getId())).withRel("_self");
		
		originModel.add(selfLink);
		
		return originModel;
		
	}
	
	@GetMapping("{id}/versions")
	public List<OriginVersionDto> findAllVersionsById(@PathVariable Long id) {
		
		List<OriginVersionDto> versions = originService.findAllVersionsById(id);
		
		return versions;
		
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
