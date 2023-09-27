package com.springpj.heroescontentcreator.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.springpj.heroescontentcreator.model.Origin;
import com.springpj.heroescontentcreator.model.dto.OriginDto;
import com.springpj.heroescontentcreator.model.version.OriginVersion;

@Mapper(componentModel = "spring")
public interface OriginMapper {
	
	OriginDto toDto(Origin origin);
	Origin toEntity(OriginDto originDto);
	
	@Mapping(source = ".", target = "origin")
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "dateCreated", ignore = true)
	OriginVersion toVersionEntity(Origin origin);
	
	List<Origin> toEntityList(List<OriginDto> originDtos);
	List<OriginDto> toDtoList(List<Origin> origins);

}
