package br.com.thought.ideas.service.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThoughtRequestDTO {

    @NotEmpty
    private String title;

    @NotEmpty
    private String humor;

    @NotEmpty
    private String description;

}
