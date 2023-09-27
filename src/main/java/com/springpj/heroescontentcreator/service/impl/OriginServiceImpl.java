package com.springpj.heroescontentcreator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpj.heroescontentcreator.mapper.OriginMapper;
import com.springpj.heroescontentcreator.model.Origin;
import com.springpj.heroescontentcreator.model.dto.OriginDto;
import com.springpj.heroescontentcreator.repository.OriginRepository;
import com.springpj.heroescontentcreator.service.OriginService;

@Service
public class OriginServiceImpl implements OriginService {
	
	private final OriginRepository originRepository;
	private final OriginMapper originMapper;
	
	@Autowired
	public OriginServiceImpl(OriginRepository originRepository,
			OriginMapper originMapper) {
		this.originRepository = originRepository;
		this.originMapper = originMapper;
	}

	@Override
	public OriginDto save(OriginDto dto) {
		Origin savedOrigin = originRepository.save(originMapper.toEntity(dto));
		return originMapper.toDto(savedOrigin);
	}
	
	

}
