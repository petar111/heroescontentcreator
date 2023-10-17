package com.springpj.heroescontentcreator.mapper;

import org.mapstruct.Mapper;

import com.springpj.heroescontentcreator.model.dto.RegisterRequestDto;
import com.springpj.heroescontentcreator.model.dto.UserDto;
import com.springpj.heroescontentcreator.model.user.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    List<UserDto> toListDto(List<User> users);

    User toEntity(UserDto userDto);

    User toEntity(RegisterRequestDto registerRequestDto);

}
