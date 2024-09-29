package com.back.producttrial.service;

import com.back.producttrial.CommonTest;
import com.back.producttrial.dto.ProductDTO;
import com.back.producttrial.entity.Product;
import com.back.producttrial.mapper.ProductMapper;
import com.back.producttrial.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTest extends CommonTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    public ProductServiceTest() throws IOException {
        super.init();
    }

    @Test
    public void getProducts() {

        //WHEN
        when(productRepository.findAll()).thenReturn(Collections.singletonList(product));
        when(productMapper.toDTO(product)).thenReturn(productDTO);

        //THEN
        List<ProductDTO> result = productService.getAllProducts();
        assertEquals(Collections.singletonList(productDTO), result);
    }

    @Test
    public void getProducts_Empty() {

        //WHEN
        when(productRepository.findAll()).thenReturn(Collections.emptyList());

        //THEN
        List<ProductDTO> result = productService.getAllProducts();
        assertEquals(0, result.size());
    }

    @Test
    public void getProductById() {

        //WHEN
        when(productRepository.findById(1L)).thenReturn(Optional.of(new Product()));
        when(productMapper.toDTO(any(Product.class))).thenReturn(productDTO);

        //THEN
        ProductDTO result = productService.getProduct(1L);
        assertEquals(productDTO, result);
    }

    @Test
    public void updateProduct() {

        //WHEN
        when(productRepository.findById(1L)).thenReturn(Optional.of(new Product()));
        when(productMapper.toEntity(productDTO)).thenReturn(new Product());

        //THEN
        productService.updateProduct(1L, productDTO);
        verify(productMapper).update(any(Product.class), any(Product.class));
    }

    @Test
    public void updateProduct_NotExist() {

        //WHEN
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        when(productMapper.toDTO(any(Product.class))).thenReturn(null);

        //THEN
        productService.updateProduct(1L, productDTO);
        verify(productMapper, times(0)).update(any(Product.class), any(Product.class));
    }

    @Test
    public void addProduct() {

        //GIVEN
        Product product1 = new Product();
        product1.setId(1L);

        //WHEN
        when(productMapper.toEntity(productDTO)).thenReturn(new Product());
        when(productRepository.save(any(Product.class))).thenReturn(product1);

        //THEN
        Long result = productService.addProduct(productDTO);
        assertEquals(1L, result);
    }

    @Test
    public void deleteProduct() {

        //THEN
        productService.deleteProduct(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }
}
