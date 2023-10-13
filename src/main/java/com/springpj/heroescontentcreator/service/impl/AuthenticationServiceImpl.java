package com.springpj.heroescontentcreator.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springpj.heroescontentcreator.errorhandler.exception.UserAlreadyExistsException;
import com.springpj.heroescontentcreator.mapper.UserMapper;
import com.springpj.heroescontentcreator.model.dto.LoginRequestDto;
import com.springpj.heroescontentcreator.model.dto.RegisterRequestDto;
import com.springpj.heroescontentcreator.model.dto.UserDto;
import com.springpj.heroescontentcreator.model.user.User;
import com.springpj.heroescontentcreator.repository.UserRepository;
import com.springpj.heroescontentcreator.security.user.UserPrincipal;
import com.springpj.heroescontentcreator.service.AuthenticationService;
import com.springpj.heroescontentcreator.service.UserService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final UserService userService;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;

	public AuthenticationServiceImpl(AuthenticationManager authenticationManager, UserService userService,
			UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDto login(LoginRequestDto loginRequestDto) {
		authenticate(loginRequestDto.getUsername(), loginRequestDto.getPassword());
		UserDto result = userMapper.toDto(userRepository.findByUsername(loginRequestDto.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("")));
		return result;
	}

	@Override
	public UserPrincipal getUserPrincipal(String username) {
		return new UserPrincipal(
				userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("")));
	}

	@Override
	public UserDto register(RegisterRequestDto registerRequestDto) {

		userRepository.findByUsername(registerRequestDto.getUsername()).ifPresent((foundUser) -> {
			throw new UserAlreadyExistsException("User with username " + foundUser.getUsername() + " already exist.");
		});
		userRepository.findByEmail(registerRequestDto.getEmail()).ifPresent((foundUser) -> {
			throw new UserAlreadyExistsException("User with email " + foundUser.getEmail() + " already exist.");
		});

		User user = prepareUserForRegistration(registerRequestDto);

		User savedUser = userRepository.save(user);
		return userMapper.toDto(savedUser);
	}

	private User prepareUserForRegistration(RegisterRequestDto registerRequestDto) {
		User result = userMapper.toEntity(registerRequestDto);
		result.setPassword(passwordEncoder.encode(result.getPassword()));

		return result;
	}

	private void authenticate(String username, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}
}