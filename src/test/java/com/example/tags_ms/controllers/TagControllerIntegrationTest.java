package com.example.tags_ms.controllers;

import com.example.tags_ms.dtos.TagDTO;
import com.example.tags_ms.models.TagEntity;
import com.example.tags_ms.services.TagService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.example.tags_ms.data.DataCollections.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class TagControllerIntegrationTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TagService tagService;

    @BeforeEach
    void setUp() {
        when(tagService.getAll())
                .thenReturn(getAllData());

        Page<TagEntity> pagedResponse = new PageImpl<>(getAllData());
        when(tagService.getAllPageable(any()))
                .thenReturn(pagedResponse);

        when(tagService.getAllByKey(ArgumentMatchers.anyString()))
                .thenReturn(getByKeyData("car"));

        when(tagService.createTag(any()))
                .thenReturn(newTag());

        when(tagService.updateTag(anyLong(), any()))
                .thenReturn(updated());
    }


    @Test
    void getAllTags() throws Exception {
        mockMvc.perform(get("/api/v1/tags/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(tagService).getAll();
    }

    @Test
    void getByKey() throws Exception {
        mockMvc.perform(get("/api/v1/tags/all/{key}", "car"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(tagService).getAllByKey(ArgumentMatchers.anyString());
    }

    @Test
    void getAllWithPage() throws Exception {
        mockMvc.perform(get("/api/v1/tags/pageable"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(tagService).getAllPageable(any());

    }

    @Test
    void createTag() throws Exception {
        mockMvc.perform(post("/api/v1/tags/add")
                        .content(asJsonString(new TagDTO().setKey("test_key").setValue("test_value")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(18L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.key").value("test_key"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.value").value("test_value"));
        verify(tagService).createTag(any());
    }

    @Test
    void updateTag() throws Exception {
        mockMvc.perform(put("/api/v1/tags/{id}", 17L)
                        .content(asJsonString(new TagDTO(17L, "laptop", "Venomancer", null)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(17L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.key").value("laptop"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.value").value("Venomancer"));

        verify(tagService).updateTag(anyLong(), any());

    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform( delete("/api/v1/tags/{id}", 17L) )
                .andExpect(status().isAccepted());
    }


}