package br.com.thought.ideas.service;


import br.com.thought.ideas.domain.ThoughtEntity;
import br.com.thought.ideas.repository.ThoughtRepository;
import br.com.thought.ideas.service.mapper.ThoughtMapper;
import br.com.thought.ideas.service.mapper.ThoughtRequestDTO;
import br.com.thought.ideas.service.mapper.ThoughtResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

        for (ThoughtEntity thought : thoughts) {
            response.add(thoughtMapper.convertToResponse(thought));
        }
        return response;
    }

    @Transactional
    public ThoughtResponseDTO persist(final ThoughtRequestDTO thoughtRequestDTO) {
        ThoughtEntity thoughtEntity = thoughtMapper.convertToEntity(thoughtRequestDTO);
        ThoughtEntity persisted = thoughtRepository.save(thoughtEntity);

        ThoughtResponseDTO reponse = thoughtMapper.convertToResponse(persisted);
        return reponse;
    }
}
