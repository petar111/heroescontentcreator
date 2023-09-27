package com.springpj.heroescontentcreator.service;

import com.springpj.heroescontentcreator.model.dto.OriginDto;

public interface OriginService {
	
	OriginDto save(OriginDto dto);

	OriginDto findById(Long id);

}
