package com.example.tags_ms.services.impl;


import com.example.tags_ms.dtos.StatusType;
import com.example.tags_ms.dtos.TagDTO;
import com.example.tags_ms.models.TagEntity;
import com.example.tags_ms.repositories.TagRepository;
import com.example.tags_ms.services.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TagEntity> getAll() {
        return tagRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Page<TagEntity> getAllPageable(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TagEntity> getAllByKey(String key) {
        return tagRepository.findAllByKey(key);
    }

    @Override
    public TagDTO createTag(TagDTO tag) {
        return tagRepository
                .save(tag.toEntity()).toDTO()
                .setMessage(StatusType.SUCCESS.name());
    }

    @Override
    public TagDTO updateTag(Long id, TagDTO dto) {
        if (id != null && id > 0) {
            Optional<TagEntity> byId = tagRepository.findById(id);
            if (byId.isPresent()) {
                TagEntity entity = byId.get();
                entity.setKey(dto.getKey());
                entity.setValue(dto.getValue());
                return tagRepository.save(entity).toDTO()
                        .setMessage(StatusType.SUCCESS.name());
            }
        }
        log.warn("---------------    TAG with id: {} not found    -------------", id);
        return new TagDTO();
    }

    @Override
    public boolean deleteById(Long id) {
        if (id != null && id > 0) {
            Optional<TagEntity> byId = tagRepository.findById(id);
            if (byId.isPresent()) {
                tagRepository.deleteById(id);
                return true;
            }
        }
        return false;
    }


}
