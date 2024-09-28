package com.back.producttrial.controller;

import com.back.producttrial.CommonTest;
import com.back.producttrial.dto.ProductDTO;
import com.back.producttrial.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@WebMvcTest(ProductController.class)
public class ProductControllerTest extends CommonTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    public ProductControllerTest() throws IOException {
        super.init();
    }

    @Test
    public void getProducts() throws Exception
    {
        //GIVEN
        List<ProductDTO> products = Collections.singletonList(productDto1);

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

    @Test
    public void getProductById_Ok() throws Exception
    {
        //WHEN
        when(productService.getProduct(anyLong())).thenReturn(productDto1);

        //THEN
        mvc.perform(MockMvcRequestBuilders
                        .get("/products/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(productDto1)))
                .andExpect(status().isOk());

        verify(productService).getProduct(1L);
    }

    @Test
    public void getProductById_Not_Found() throws Exception
    {
        //WHEN
        when(productService.getProduct(anyLong())).thenReturn(null);

        //THEN
        mvc.perform(MockMvcRequestBuilders
                        .get("/products/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(productService).getProduct(2L);
    }

    @Test
    public void updateProduct_Ok() throws Exception
    {
        //WHEN
        when(productService.updateProduct(anyLong(),any(ProductDTO.class))).thenReturn(productDto2);

        //THEN
        mvc.perform(MockMvcRequestBuilders
                        .patch("/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto2))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productService).updateProduct(1L, productDto2);
    }

    @Test
    public void updateProduct_Not_Found() throws Exception
    {
        //WHEN
        when(productService.updateProduct(anyLong(),any(ProductDTO.class))).thenReturn(null);

        //THEN
        mvc.perform(MockMvcRequestBuilders
                        .patch("/products/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto2))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(productService).updateProduct(2L, productDto2);
    }

    @Test
    public void postProduct() throws Exception
    {
        //WHEN
        when(productService.addProduct(any(ProductDTO.class))).thenReturn(1L);

        //THEN
        mvc.perform(MockMvcRequestBuilders
                        .post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto1))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productService).addProduct(productDto1);
    }

    @Test
    public void deleteProduct() throws Exception
    {
        //THEN
        mvc.perform(MockMvcRequestBuilders
                        .delete("/products/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productService).deleteProduct(anyLong());
    }
}
