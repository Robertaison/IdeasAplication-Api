package br.com.thought.ideas.controller;

import br.com.thought.ideas.service.ThoughtServiceImpl;
import br.com.thought.ideas.service.mapper.ThoughtRequestDTO;
import br.com.thought.ideas.service.mapper.ThoughtResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ThoughtController {

    @Autowired
    private ThoughtServiceImpl thoughtService;

    @GetMapping("/ideas")
    public List<ThoughtResponseDTO> findAll() {
        return thoughtService.retrieveAllThoughts();
    }

    @PostMapping("/share")
    public void save(@Valid @RequestBody final ThoughtRequestDTO thought) {

        thoughtService.save(thought);
    }

//    @GetMapping("/{id}")
//    public ThoughtResponseDTO findById(@PathVariable final Long id) {
//        return thoughtService.findById(id);
//    }

//    @DeleteMapping("/{id}")
//    public void deleteThought(@PathVariable long id) {
//        thoughtService.delete(id);
//    }
}
