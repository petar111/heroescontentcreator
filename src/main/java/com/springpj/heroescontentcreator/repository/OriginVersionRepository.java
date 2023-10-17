package com.springpj.heroescontentcreator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springpj.heroescontentcreator.model.origin.Origin;
import com.springpj.heroescontentcreator.model.version.OriginVersion;

public interface OriginVersionRepository extends JpaRepository<OriginVersion, Long> {
	
	List<OriginVersion> findAllByOrigin(Origin origin);
}
