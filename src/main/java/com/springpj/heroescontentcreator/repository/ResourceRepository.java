package com.springpj.heroescontentcreator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springpj.heroescontentcreator.model.authorization.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

}
