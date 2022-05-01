package com.example.tags_ms.models;

import com.example.tags_ms.dtos.TagDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tags")
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String key;
    private String value;

    public TagDTO toDTO() {
        TagDTO dto = new TagDTO();
        BeanUtils.copyProperties(this, dto, ""/*we can hide some params*/);
        return dto;
    }
}
