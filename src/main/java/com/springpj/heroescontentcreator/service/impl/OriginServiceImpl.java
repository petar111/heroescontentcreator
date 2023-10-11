package com.springpj.heroescontentcreator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpj.heroescontentcreator.errorhandler.exception.OriginNotFoundByIdException;
import com.springpj.heroescontentcreator.mapper.OriginMapper;
import com.springpj.heroescontentcreator.model.Origin;
import com.springpj.heroescontentcreator.model.dto.OriginDto;
import com.springpj.heroescontentcreator.repository.OriginRepository;
import com.springpj.heroescontentcreator.repository.OriginVersionRepository;
import com.springpj.heroescontentcreator.service.OriginService;

@Service
public class OriginServiceImpl implements OriginService {
	
	private final OriginRepository originRepository;
	private final OriginVersionRepository originVersionRepository;
	private final OriginMapper originMapper;
	
	@Autowired
	public OriginServiceImpl(OriginRepository originRepository,
			OriginVersionRepository originVersionRepository,
			OriginMapper originMapper) {
		
		this.originRepository = originRepository;
		this.originMapper = originMapper;
		this.originVersionRepository = originVersionRepository;
	}

	@Override
	public OriginDto save(OriginDto dto) {
		Origin savedOrigin = originRepository.save(originMapper.toEntity(dto));
		originVersionRepository.save(originMapper.toVersionEntity(savedOrigin));
		return originMapper.toDto(savedOrigin);
	}

	@Override
	public OriginDto findById(Long id) {
		Origin origin = originRepository.findById(id)
				.orElseThrow(() -> new OriginNotFoundByIdException(id));
		return originMapper.toDto(origin);
	}
	
	

}
