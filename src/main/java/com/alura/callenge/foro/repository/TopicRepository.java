package com.alura.callenge.foro.repository;

import com.alura.callenge.foro.domain.topic.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    boolean existsByTitleAndMessageAndDeletedFalse(String title, String message);
    Page<Topic> findByDeletedFalse(Pageable pageable);
    Optional<Topic> findByIdAndDeletedFalse(Long id);

}
