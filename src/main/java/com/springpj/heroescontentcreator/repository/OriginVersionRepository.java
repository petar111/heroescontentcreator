package com.springpj.heroescontentcreator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springpj.heroescontentcreator.model.version.OriginVersion;

public interface OriginVersionRepository extends JpaRepository<OriginVersion, Long> {
}
