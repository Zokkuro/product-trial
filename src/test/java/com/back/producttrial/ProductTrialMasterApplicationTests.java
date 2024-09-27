package com.back.producttrial;

import com.back.producttrial.controller.ProductController;
import com.back.producttrial.mapper.ProductMapper;
import com.back.producttrial.repository.ProductRepository;
import com.back.producttrial.service.ProductService;
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
