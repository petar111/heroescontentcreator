package com.springpj.heroescontentcreator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springpj.heroescontentcreator.model.authorization.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
