package br.com.thought.ideas.controller;

import br.com.thought.ideas.service.ThoughtServiceImpl;
import br.com.thought.ideas.service.mapper.ThoughtRequestDTO;
import br.com.thought.ideas.service.mapper.ThoughtResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Api responsável por salvar e exibir ideias", tags = "Ideas Controller")
@RestController
public class ThoughtController {

    @Autowired
    private ThoughtServiceImpl thoughtService;

    @ApiOperation(value = "Método responsável por retornar todas as ideas cadastradas")
    @GetMapping("/ideas")
    public List<ThoughtResponseDTO> findAll() {
        return thoughtService.retrieveAllThoughts();
    }

    @ApiOperation(value = "Método responsável por cadastrar uma nova ideia")
    @PostMapping("/share")
    public void save(@Valid @RequestBody final ThoughtRequestDTO thought) {
        thoughtService.save(thought);
    }
}
