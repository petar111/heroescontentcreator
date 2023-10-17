package com.springpj.heroescontentcreator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springpj.heroescontentcreator.model.dto.UserDto;
import com.springpj.heroescontentcreator.model.dto.paging.PageRequestDto;
import com.springpj.heroescontentcreator.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	
	
	private final UserService userService;
	
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}



	@GetMapping("{id}")
	public UserDto findById(@PathVariable Long id) {
		return userService.findById(id);
	}
	
	@GetMapping("all")
	public Page<UserDto> findAllPage(
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int pageSize,
			@RequestParam(required = false, defaultValue = "ASC") String sortOrder,
			@RequestParam(required = false, defaultValue = "username") String sortBy
			){
		
		PageRequestDto pageRequest = PageRequestDto.builder()
				.withPage(page)
				.withPageSize(pageSize)
				.withSortOrder(sortOrder)
				.withSortBy(sortBy)
				.build();
		
		return userService.findAllPage(pageRequest);
	}

}
