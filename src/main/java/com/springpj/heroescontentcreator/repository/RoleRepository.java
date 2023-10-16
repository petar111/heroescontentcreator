package com.springpj.heroescontentcreator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springpj.heroescontentcreator.model.authorization.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Optional<Role> findByName(String name);
}
