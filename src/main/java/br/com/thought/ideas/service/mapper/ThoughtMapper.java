package br.com.thought.ideas.service.mapper;

import br.com.thought.ideas.domain.ThoughtEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ThoughtMapper {

    ThoughtEntity convertToEntity(ThoughtRequestDTO thoughtRequestDTO);

    @Mappings({
            @Mapping(target = "data", dateFormat = "dd/mm/yyyy HH:mm")
    })
    ThoughtResponseDTO convertToResponse(ThoughtEntity thoughtEntity);
}
