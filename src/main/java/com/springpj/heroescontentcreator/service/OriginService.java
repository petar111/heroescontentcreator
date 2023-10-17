package com.springpj.heroescontentcreator.service;

import java.util.List;

import com.springpj.heroescontentcreator.model.dto.OriginDto;
import com.springpj.heroescontentcreator.model.dto.OriginVersionDto;

public interface OriginService {
	
	OriginDto save(OriginDto dto);

	OriginDto findById(Long id);

	List<OriginVersionDto> findAllVersionsById(Long id);

}
