package br.com.thought.ideas.service.mapper;

import br.com.thought.ideas.domain.ThoughtEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ThoughtMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "data", ignore = true)
    })
    ThoughtEntity convertToEntity(ThoughtRequestDTO thoughtRequestDTO);

    @Mappings({
            @Mapping(target = "data", dateFormat = "dd/MM/yyyy HH:mm")
    })
    ThoughtResponseDTO convertToResponse(ThoughtEntity thoughtEntity);
}
