package com.back.producttrialmaster.mapper;

import com.back.producttrialmaster.dto.ProductDTO;
import com.back.producttrialmaster.repository.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy  = NullValuePropertyMappingStrategy.IGNORE  )
public interface ProductMapper {
    ProductDTO toDTO(Product product);
    Product toEntity(ProductDTO destination);
    void update( Product newProduct, @MappingTarget Product oldProduct);
}