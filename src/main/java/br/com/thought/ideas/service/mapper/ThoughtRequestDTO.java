package br.com.thought.ideas.service.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThoughtRequestDTO {

    @NotEmpty
    @Size(max = 85, message = "Seu título deve conter no máximo 85 caracteres")
    private String title;

    @NotEmpty
    private String humor;

    @NotEmpty
    @Size(max = 255, message = "O texto deve conter menos que 255 caracteres")
    private String description;

    @Size(max = 40, message = "Nome do autor deve ter no máximo 40 caracteres")
    private String author;
}
