package com.example.tags_ms.controllers;


import com.example.tags_ms.dtos.TagDTO;
import com.example.tags_ms.models.TagEntity;
import com.example.tags_ms.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/tags")
public class TagController {

    private final TagService tagService;

    @GetMapping("all")
    public List<TagEntity> getAllTags() {
        return tagService.getAll();
    }

    @GetMapping("all/{key}")
    public List<TagEntity> getByKey(@PathVariable String key) {
        return tagService.getAllByKey(key);
    }


    @GetMapping("pageable")
    public Page<TagEntity> getAllWithPage(Pageable pageable) {
        return tagService.getAllPageable(pageable);
    }


    @PostMapping("add")
    public ResponseEntity<TagDTO> createTag(@Valid @RequestBody TagDTO dto) {
        return new ResponseEntity<>(tagService.createTag(dto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public TagDTO updateTag(@NotNull(message = "id parameters must be filled in order to update")
                            @PathVariable Long id, @Valid @RequestBody TagDTO dto) {
        return tagService.updateTag(id, dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, Boolean>> deleteById(@PathVariable Long id) {
        Map<String, Boolean> map = new HashMap<>();
        map.put("deleted", tagService.deleteById(id));
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);

    }
}
