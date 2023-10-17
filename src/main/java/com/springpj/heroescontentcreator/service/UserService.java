package com.springpj.heroescontentcreator.service;

import org.springframework.data.domain.Page;

import com.springpj.heroescontentcreator.model.dto.UserDto;
import com.springpj.heroescontentcreator.model.dto.paging.PageRequestDto;

public interface UserService {
	
	UserDto findById(Long id);
	
    UserDto findByUsername(String username);

    UserDto save(UserDto userDto);

	Page<UserDto> findAllPage(PageRequestDto pageRequest);

}
