package az.company.products.service.concrete;

import az.company.products.dao.repository.ProductRepository;
import az.company.products.exception.InsufficientQuantityException;
import az.company.products.exception.NotFoundException;
import az.company.products.model.request.CreateProductRequest;
import az.company.products.model.request.ReduceQuantityRequest;
import az.company.products.model.response.ProductResponse;
import az.company.products.service.abstraction.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static az.company.products.mapper.ProductMapper.PRODUCT_MAPPER;
import static az.company.products.model.enums.ErrorMessage.INSUFFICIENT_QUANTITY;
import static az.company.products.model.enums.ErrorMessage.PRODUCT_NOT_FOUND;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ProductServiceHandler implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public void createProduct(CreateProductRequest createProductRequest) {
        productRepository.save(PRODUCT_MAPPER.buildProductEntity(createProductRequest));
    }

    @Override
    public ProductResponse getProductById(Long id) {
        return productRepository.findById(id)
                .map(PRODUCT_MAPPER::buildProductResponse)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                PRODUCT_NOT_FOUND.getMessage(),
                                id
                        )
                ));
    }

    @Override
    public void reduceQuantity(ReduceQuantityRequest reduceQuantityRequest) {
        var productEntity =
                productRepository.findById(reduceQuantityRequest.getProductId())
                        .orElseThrow(() -> new NotFoundException(
                                format(
                                        PRODUCT_NOT_FOUND.getMessage(),
                                        reduceQuantityRequest.getProductId()
                                )
                        ));

        if (productEntity.getQuantity() < reduceQuantityRequest.getQuantity()) {
            throw new InsufficientQuantityException(
                    format(
                            INSUFFICIENT_QUANTITY.getMessage(),
                            reduceQuantityRequest.getProductId()
                    ));
        }

        productEntity.setQuantity(
                productEntity.getQuantity() - reduceQuantityRequest.getQuantity()
        );
        productRepository.save(productEntity);
    }
}
