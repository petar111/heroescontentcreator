package com.springpj.heroescontentcreator.service;

import com.springpj.heroescontentcreator.model.dto.UserDto;

public interface UserService {
    UserDto findByUsername(String username);

    UserDto save(UserDto userDto);

}
