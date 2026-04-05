package com.soumya._stWebFluxApplication.sec08.mapper;

import com.soumya._stWebFluxApplication.sec08.dto.ProductDto;
import com.soumya._stWebFluxApplication.sec08.entity.Product;

public class ProductMapper {
    public static Product mapProductDtoToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.id());
        product.setDescription(productDto.description());
        product.setPrice(product.getPrice());
        return product;
    }

    public static ProductDto mapProductToProductDto(Product product) {
        return new ProductDto(product.getId(), product.getDescription(), product.getPrice());
    }
}
