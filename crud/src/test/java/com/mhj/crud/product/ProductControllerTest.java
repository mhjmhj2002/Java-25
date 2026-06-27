package com.mhj.crud.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldCreateProduct() throws Exception {

        ProductRequest request = new ProductRequest(
                "Notebook",
                "Dell Latitude",
                BigDecimal.valueOf(3500)
        );

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Notebook"))
                .andExpect(jsonPath("$.description").value("Dell Latitude"))
                .andExpect(jsonPath("$.price").value(3500));
    }

    @Test
    void shouldReturn404WhenProductDoesNotExist() throws Exception {

        mockMvc.perform(get("/api/products/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldValidateRequest() throws Exception {

        ProductRequest request = new ProductRequest(
                "",
                "Produto inválido",
                BigDecimal.ZERO
        );

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn404WhenUpdatingProductDoesNotExist() throws Exception {
        ProductRequest request = new ProductRequest(
                "Notebook Atualizado",
                "Latitude Atualizado",
                BigDecimal.valueOf(4500)
        );

        mockMvc.perform(put("/api/products/999999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteProduct() throws Exception {

        mockMvc.perform(delete("/api/products/999"))
                .andExpect(status().isNotFound());
    }

}