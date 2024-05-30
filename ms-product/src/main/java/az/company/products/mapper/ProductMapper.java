package az.company.products.mapper;

import az.company.products.dao.entity.ProductEntity;
import az.company.products.model.request.CreateProductRequest;
import az.company.products.model.response.ProductResponse;

public enum ProductMapper {
    PRODUCT_MAPPER;

    public ProductEntity buildProductEntity(CreateProductRequest request) {
        return ProductEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();
    }

    public ProductResponse buildProductResponse(ProductEntity productEntity) {
        return ProductResponse.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .quantity(productEntity.getQuantity())
                .build();
    }
}
