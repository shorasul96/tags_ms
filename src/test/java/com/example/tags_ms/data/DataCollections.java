package com.example.tags_ms.data;

import com.example.tags_ms.dtos.StatusType;
import com.example.tags_ms.dtos.TagDTO;
import com.example.tags_ms.models.TagEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataCollections {
    public static List<TagEntity> getAllData() {
        List<TagEntity> list = new ArrayList<>();
        list.add(new TagEntity(1L, "mobile", "Samsung Galaxy S20"));
        list.add(new TagEntity(2L, "mobile", "Samsung Galaxy S21 Mini"));
        list.add(new TagEntity(3L, "mobile", "Samsung Galaxy S22 Plus"));
        list.add(new TagEntity(4L, "mobile", "Samsung Galaxy S22 Ultra"));
        list.add(new TagEntity(5L, "mobile", "iPhone X"));
        list.add(new TagEntity(6L, "mobile", "iPhone 11 Pro"));
        list.add(new TagEntity(7L, "mobile", "iPhone 12 Mini"));
        list.add(new TagEntity(8L, "mobile", "iPhone 13 Pro Max"));

        list.add(new TagEntity(9L, "car", "Tesla"));
        list.add(new TagEntity(10L, "car", "BMW"));
        list.add(new TagEntity(11L, "car", "Honda"));
        list.add(new TagEntity(12L, "car", "Volvo"));
        list.add(new TagEntity(13L, "car", "Chevrolet"));

        list.add(new TagEntity(14L, "car", "MacBook Pro"));
        list.add(new TagEntity(15L, "car", "Dell"));
        list.add(new TagEntity(16L, "car", "Acer"));
        list.add(new TagEntity(17L, "car", "Asus"));

        return list;
    }


    public static List<TagEntity> getByKeyData(String str) {
        return getAllData()
                .stream()
                .filter(tag -> tag.getKey().equals(str))
                .collect(Collectors.toList());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static TagDTO newTag() {
        return new TagDTO(18L, "test_key", "test_value", StatusType.SUCCESS.name());
    }

    public static TagDTO updated() {
        return new TagDTO(17L, "laptop", "Venomancer", StatusType.SUCCESS.name());
    }


}
