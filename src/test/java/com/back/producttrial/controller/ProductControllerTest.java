package com.back.producttrial.controller;

import com.back.producttrial.dto.ProductDTO;
import com.back.producttrial.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    private ProductDTO productDTO;

    @BeforeEach
    void setUp() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("product.json");
        if (inputStream == null) {
            throw new FileNotFoundException("Fichier non trouvé : product.json");
        }
        productDTO = objectMapper.readValue(inputStream, ProductDTO.class);
    }

    @Test
    public void getProducts() throws Exception
    {
        //GIVEN
        List<ProductDTO> products = Collections.singletonList(productDTO);

        //WHEN
        when(productService.getAllProducts()).thenReturn(products);

        //THEN
        mvc.perform(MockMvcRequestBuilders
                        .get("/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(products)))
                .andExpect(status().isOk());

        verify(productService).getAllProducts();
    }
}
