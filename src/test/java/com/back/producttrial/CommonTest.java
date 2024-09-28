package com.back.producttrial;

import com.back.producttrial.dto.ProductDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public abstract class CommonTest {

    protected ObjectMapper objectMapper;
    protected ProductDTO productDto1;
    protected ProductDTO productDto2;

    protected void init() throws IOException {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("product.json");
        if (inputStream == null) {
            throw new FileNotFoundException("Fichier non trouvé : product.json");
        }
        productDto1 = objectMapper.readValue(inputStream, ProductDTO.class);
        inputStream = getClass().getClassLoader().getResourceAsStream("product.json");
        if (inputStream == null) {
            throw new FileNotFoundException("Fichier non trouvé : product.json");
        }
        productDto2 = objectMapper.readValue(inputStream, ProductDTO.class);
    }
}
