package br.com.thought.ideas.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThoughtEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @NotNull
    private String description;

    private String humor;

    private String author;

    @CreationTimestamp
    @DateTimeFormat(pattern = "pattern = dd/mm/yyyy HH:mm")
    private Calendar data;
}
