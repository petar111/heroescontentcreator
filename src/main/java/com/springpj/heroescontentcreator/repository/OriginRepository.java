package com.springpj.heroescontentcreator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springpj.heroescontentcreator.model.origin.Origin;

public interface OriginRepository extends JpaRepository<Origin, Long> {
}
