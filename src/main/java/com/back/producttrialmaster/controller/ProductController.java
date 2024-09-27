package com.back.producttrialmaster.controller;

import com.back.producttrialmaster.dto.ProductDTO;
import com.back.producttrialmaster.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;

    @PostMapping
    ResponseEntity<Long> addProduct(@RequestBody @Validated(ProductDTO.OnPost.class) ProductDTO product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @GetMapping
    ResponseEntity<List<ProductDTO>> getProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    ResponseEntity<ProductDTO> getProduct(@PathVariable("id") long productId) {
        ProductDTO result = productService.getProduct(productId);
        return result !=null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") long productId, @RequestBody @Validated(ProductDTO.OnPatch.class) ProductDTO product) {
        ProductDTO result = productService.updateProduct(productId, product);
        return result !=null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteProduct(@PathVariable("id") long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
