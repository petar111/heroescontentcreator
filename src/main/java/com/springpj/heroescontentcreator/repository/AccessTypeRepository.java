package com.springpj.heroescontentcreator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springpj.heroescontentcreator.model.authorization.AccessType;

public interface AccessTypeRepository extends JpaRepository<AccessType, Long> {

}
