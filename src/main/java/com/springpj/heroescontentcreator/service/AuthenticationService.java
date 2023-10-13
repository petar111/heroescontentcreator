package com.springpj.heroescontentcreator.service;

import com.springpj.heroescontentcreator.model.dto.LoginRequestDto;
import com.springpj.heroescontentcreator.model.dto.RegisterRequestDto;
import com.springpj.heroescontentcreator.model.dto.UserDto;
import com.springpj.heroescontentcreator.security.user.UserPrincipal;

public interface AuthenticationService {
	
	UserDto login(LoginRequestDto loginRequestDto);

    UserPrincipal getUserPrincipal(String username);

    UserDto register(RegisterRequestDto registerRequestDto);

}
