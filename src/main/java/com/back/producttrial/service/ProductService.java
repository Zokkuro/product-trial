package com.back.producttrial.service;

import com.back.producttrial.dto.ProductDTO;
import com.back.producttrial.mapper.ProductMapper;
import com.back.producttrial.repository.ProductRepository;
import com.back.producttrial.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Long addProduct(ProductDTO productDTO) {
        Product product = repository.save(mapper.toEntity(productDTO));
        return product.getId();
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = repository.findAll();
        return products.stream().map(mapper::toDTO).collect(Collectors.toCollection(ArrayList::new));
    }

    public ProductDTO getProduct(long productId) {
        return mapper.toDTO(repository.findById(productId).orElse(null));
    }

    public ProductDTO updateProduct(long productId, ProductDTO newProductDTO) {
        Optional<Product> oldProduct = repository.findById(productId);
        if(oldProduct.isPresent()) {
            Product product = oldProduct.get();
            mapper.update(mapper.toEntity(newProductDTO), product);
            repository.save(product);
            return newProductDTO;
        }
        return null;
    }

    public void deleteProduct(long productId) {
        repository.deleteById(productId);
    }
}
