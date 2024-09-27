package com.back.producttrialmaster;

import com.back.producttrialmaster.controller.ProductController;
import com.back.producttrialmaster.mapper.ProductMapper;
import com.back.producttrialmaster.repository.ProductRepository;
import com.back.producttrialmaster.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RequiredArgsConstructor
class ProductTrialMasterApplicationTests {

	private ProductService productService;
	private ProductRepository productRepository;
	private ProductController productController;
	private ProductMapper productMapper;

	@Autowired
	void ApplicationContextTest(ProductService productService, ProductRepository productRepository, ProductController productController, ProductMapper productMapper) {
		this.productService = productService;
		this.productRepository = productRepository;
		this.productController = productController;
		this.productMapper = productMapper;
	}

	@Test
	void contextLoads() {
		assertThat(productService).isNotNull();
		assertThat(productRepository).isNotNull();
		assertThat(productController).isNotNull();
		assertThat(productMapper).isNotNull();
	}

}
