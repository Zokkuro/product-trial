package com.back.producttrial.mapper;

import com.back.producttrial.CommonTest;
import com.back.producttrial.dto.ProductDTO;
import com.back.producttrial.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductMapperTest extends CommonTest {

    private final ProductMapper productMapper = new ProductMapperImpl();

    public ProductMapperTest() throws IOException {
        super.init();
    }

    @Test
    public void toDTOTest() {

        //THEN
        ProductDTO result = productMapper.toDTO(product);

        assertEquals(productDTO, result);
    }

    @Test
    public void toEntityTest() {

        //THEN
        Product result = productMapper.toEntity(productDTO);

        assertEquals(product, result);
    }

    @Test
    public void updateEntityTest() {

        //GIVEN
        ProductDTO updatedProductDTO = productDTO.toBuilder().build();
        updatedProductDTO.setRating(2);
        Product updatedProduct = productMapper.toEntity(updatedProductDTO);

        //THEN
        productMapper.update(updatedProduct,product);
        assertEquals(product, updatedProduct);
    }


}
