package com.back.producttrial.mapper;

import com.back.producttrial.dto.ProductDTO;
import com.back.producttrial.repository.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy  = NullValuePropertyMappingStrategy.IGNORE  )
public interface ProductMapper {
    ProductDTO toDTO(Product product);
    Product toEntity(ProductDTO destination);
    void update( Product newProduct, @MappingTarget Product oldProduct);
}