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

    @Test
    public void getProductById_Ok() throws Exception
    {
        //WHEN
        when(productService.getProduct(anyLong())).thenReturn(productDTO);

        //THEN
        mvc.perform(MockMvcRequestBuilders
                        .get("/products/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(productDTO)))
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
        when(productService.updateProduct(anyLong(),any(ProductDTO.class))).thenReturn(productDTO);

        //THEN
        mvc.perform(MockMvcRequestBuilders
                        .patch("/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productService).updateProduct(1L, productDTO);
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
                        .content(objectMapper.writeValueAsString(productDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(productService).updateProduct(2L, productDTO);
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
                        .content(objectMapper.writeValueAsString(productDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productService).addProduct(productDTO);
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

    @Test
    public void postProduct_BadRequest_RatingMax() throws Exception
    {
        //GIVEN
        productDTO.setRating(10);

        //THEN
        mvc.perform(MockMvcRequestBuilders
                        .post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("{\"rating\":\"must be less than or equal to 5\"}"));

        verify(productService, times(0)).addProduct(productDTO);
    }

    @Test
    public void postProduct_BadRequest_InventoryReference() throws Exception
    {
        //GIVEN
        String jsonRequest = objectMapper.writeValueAsString(productDTO).replace("INSTOCK", "BADVALUE");

        //THEN
        mvc.perform(MockMvcRequestBuilders
                        .post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(productService, times(0)).addProduct(productDTO);
    }

    @Test
    public void postProduct_BadRequest_TypeOfValue() throws Exception
    {
        //GIVEN
        String jsonRequest = objectMapper.writeValueAsString(productDTO).replace("5", "\"test\"");

        //THEN
        mvc.perform(MockMvcRequestBuilders
                        .post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(productService, times(0)).addProduct(productDTO);
    }
}
