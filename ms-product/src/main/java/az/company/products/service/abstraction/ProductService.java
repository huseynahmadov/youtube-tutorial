package az.company.products.service.abstraction;

import az.company.products.model.request.CreateProductRequest;
import az.company.products.model.request.ReduceQuantityRequest;
import az.company.products.model.response.ProductResponse;

public interface ProductService {
    void createProduct(CreateProductRequest createProductRequest);

    ProductResponse getProductById(Long id);

    void reduceQuantity(ReduceQuantityRequest reduceQuantityRequest);
}
