package com.springpj.heroescontentcreator.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.springpj.heroescontentcreator.model.Origin;
import com.springpj.heroescontentcreator.model.dto.OriginDto;

@Mapper(componentModel = "spring")
public interface OriginMapper {
	
	OriginDto toDto(Origin origin);
	Origin toEntity(OriginDto originDto);
	
	List<Origin> toEntityList(List<OriginDto> originDtos);
	List<OriginDto> toDtoList(List<Origin> origins);

}
