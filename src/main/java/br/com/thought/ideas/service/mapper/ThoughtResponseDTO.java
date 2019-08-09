package br.com.thought.ideas.service.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThoughtResponseDTO {

    public String title;

    public String description;

    public String humor;

    public String data;
}
