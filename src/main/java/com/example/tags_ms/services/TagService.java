package com.example.tags_ms.services;


import com.example.tags_ms.dtos.TagDTO;
import com.example.tags_ms.models.TagEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    List<TagEntity> getAll();

    Page<TagEntity> getAllPageable(Pageable pageable);

    List<TagEntity> getAllByKey(String key);

    TagDTO createTag(TagDTO tag);

    TagDTO updateTag(Long id, TagDTO dto);

    boolean deleteById(Long id);
}
