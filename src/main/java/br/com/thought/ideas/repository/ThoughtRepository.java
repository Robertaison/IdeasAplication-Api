package br.com.thought.ideas.repository;


import br.com.thought.ideas.domain.ThoughtEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThoughtRepository extends JpaRepository<ThoughtEntity, Long> {
    List<ThoughtEntity> findAllByOrderByDataDesc();
}
