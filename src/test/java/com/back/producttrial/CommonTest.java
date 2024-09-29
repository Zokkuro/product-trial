package com.back.producttrial;

import com.back.producttrial.dto.ProductDTO;
import com.back.producttrial.entity.Product;
import com.back.producttrial.enumeration.InventoryReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.OffsetDateTime;


public abstract class CommonTest {

    protected ObjectMapper objectMapper;
    protected ProductDTO productDTO;
    protected Product product;

    protected void init() throws IOException {
        initDTO();
        initEntity();
    }

    private void initDTO() throws IOException {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("product.json");
        if (inputStream == null) {
            throw new FileNotFoundException("Fichier non trouvé : product.json");
        }
        productDTO = objectMapper.readValue(inputStream, ProductDTO.class);
    }

    private void initEntity() {
        product = new Product();
        product.setId(1L);
        product.setName("IPhone");
        product.setCode("S-0001");
        product.setDescription("Voici le nouvelle IPhone");
        product.setImage("image 1");
        product.setCategory("Smartphone");
        product.setPrice(new BigDecimal("900.00"));
        product.setInternalReference("INTERNAL-0001");
        product.setShellId(1L);
        product.setInventoryReference(InventoryReference.INSTOCK);
        product.setRating(5);
        product.setCreatedAt(OffsetDateTime.parse("2024-09-29T11:29:33.464Z"));
        product.setUpdatedAt(OffsetDateTime.parse("2024-09-29T11:29:33.464Z"));
    }
}
