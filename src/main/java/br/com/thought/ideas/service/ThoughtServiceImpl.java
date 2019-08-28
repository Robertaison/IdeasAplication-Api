package br.com.thought.ideas.service;


import br.com.thought.ideas.domain.ThoughtEntity;
import br.com.thought.ideas.repository.ThoughtRepository;
import br.com.thought.ideas.service.mapper.ThoughtMapper;
import br.com.thought.ideas.service.mapper.ThoughtRequestDTO;
import br.com.thought.ideas.service.mapper.ThoughtResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ThoughtServiceImpl {

    @Autowired
    private ThoughtRepository thoughtRepository;

    @Autowired
    private ThoughtMapper thoughtMapper;

    public void save(final ThoughtRequestDTO request) {
        if (request.getAuthor().isEmpty() || request.getAuthor() == null) {
            request.setAuthor("Anônimo");
        }
        ThoughtResponseDTO toPersist = persist(request);
    }


    @Transactional(readOnly = true)
    public List<ThoughtResponseDTO> retrieveAllThoughts() {

        List<ThoughtEntity> thoughts = thoughtRepository.findAllByOrderByDataDesc();
        List<ThoughtResponseDTO> response = new ArrayList<>();

        for(ThoughtEntity thought: thoughts){
            response.add(thoughtMapper.convertToResponse(thought));
        }
        log.info("retrieveAllThoughts, foram encontrados {} registros de ideas.", response.size());
        return response;
    }

    @Transactional
    private ThoughtResponseDTO persist (final ThoughtRequestDTO thoughtRequestDTO){

        treatHtmlAndJsInjection(thoughtRequestDTO);

        ThoughtEntity thoughtEntity = thoughtMapper.convertToEntity(thoughtRequestDTO);
        ThoughtEntity persisted = thoughtRepository.save(thoughtEntity);

        log.info("persist, objeto persistido = {}", thoughtEntity);
        ThoughtResponseDTO reponse = thoughtMapper.convertToResponse(persisted);
        log.info("persist, objeto criado = {}", reponse);
        return reponse;
    }

    @Transactional
    public void delete(long id) {
        thoughtRepository.deleteById(id);
    }

    private static void treatHtmlAndJsInjection (ThoughtRequestDTO thoughtRequestDTO){

        String descriptionHtml = StringEscapeUtils.escapeHtml4(thoughtRequestDTO.getDescription());
        String tittleHtml = StringEscapeUtils.escapeHtml4(thoughtRequestDTO.getTitle());
        String authorHtml = StringEscapeUtils.escapeHtml4(thoughtRequestDTO.getAuthor());

        String descriptionJS = StringEscapeUtils.escapeEcmaScript(descriptionHtml);
        String tittleJs = StringEscapeUtils.escapeEcmaScript(tittleHtml);
        String authorJs = StringEscapeUtils.escapeEcmaScript(authorHtml);

        thoughtRequestDTO.setTitle(tittleJs);
        thoughtRequestDTO.setDescription(descriptionJS);
        thoughtRequestDTO.setAuthor(authorJs);
    }
}
