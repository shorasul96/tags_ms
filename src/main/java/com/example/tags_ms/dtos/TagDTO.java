package com.example.tags_ms.dtos;

import com.example.tags_ms.models.TagEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TagDTO {

    private Long id;

    @NotNull(message = "Key cannot be null")
    @Size(min = 1, max = 255, message = "Key size must be between {min} and {max} characters long")
    private String key;

    @NotNull(message = "Value cannot be null")
    @Size(min = 1, max = 255, message = "Value size must be between {min} and {max} characters long")
    private String value;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String message = StatusType.FAILED.name();

    public TagEntity toEntity() {
        TagEntity entity = new TagEntity();
        BeanUtils.copyProperties(this, entity, "id");
        return entity;
    }
}
