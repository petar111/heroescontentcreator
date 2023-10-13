package com.springpj.heroescontentcreator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springpj.heroescontentcreator.model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

}
