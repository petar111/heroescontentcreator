package com.springpj.heroescontentcreator.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.springpj.heroescontentcreator.model.dto.OriginVersionDto;
import com.springpj.heroescontentcreator.model.version.OriginVersion;

@Mapper(componentModel = "spring", uses = {OriginMapper.class})
public interface OriginVersionMapper {
	
	@Mapping(target = "origin", ignore = true)
	OriginVersionDto toDto(OriginVersion origin);
	OriginVersion toEntity(OriginVersionDto originDto);
	
	List<OriginVersion> toEntityList(List<OriginVersionDto> originDtos);
	List<OriginVersionDto> toDtoList(List<OriginVersion> origins);

}
