package com.springpj.heroescontentcreator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springpj.heroescontentcreator.errorhandler.exception.UserNotFoundByIdException;
import com.springpj.heroescontentcreator.mapper.UserMapper;
import com.springpj.heroescontentcreator.model.dto.UserDto;
import com.springpj.heroescontentcreator.model.dto.paging.PageRequestDto;
import com.springpj.heroescontentcreator.model.user.User;
import com.springpj.heroescontentcreator.repository.UserRepository;
import com.springpj.heroescontentcreator.service.UserService;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            UserMapper userMapper, 
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public UserDto findByUsername(String username) {
        return userMapper.toDto(userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usename " + username + " not found- User service.")));
    }

    @Override
    public UserDto save(UserDto userDto) {
        User userDb = userRepository.findById(userDto.getId()).orElseThrow(
                () -> new UserNotFoundByIdException(userDto.getId())
        );
        User user = userMapper.toEntity(userDto);
        if(userDto.getPassword() == null){
            user.setPassword(userDb.getPassword());
        }else{
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }



	@Override
	public UserDto findById(Long id) {
		User user = userRepository.findById(id)
						.orElseThrow(() -> new UserNotFoundByIdException(id));
		return userMapper.toDto(user);
	}



	@Override
	public Page<UserDto> findAllPage(PageRequestDto pageRequestDto) {
		
		PageRequest pageRequest = PageRequest.of(pageRequestDto.getPage(), 
				pageRequestDto.getPageSize(), 
				Sort.by(Direction.fromString(pageRequestDto.getSortOrder()), pageRequestDto.getSortBy()));
		
		Page<User> users = userRepository.findAll(pageRequest);
		
		return users.map(userMapper::toDto);
	}


}
