package com.example.tags_ms.repositories;

import com.example.tags_ms.models.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<TagEntity, Long> {
    List<TagEntity> findAllByKey(String key);
}
