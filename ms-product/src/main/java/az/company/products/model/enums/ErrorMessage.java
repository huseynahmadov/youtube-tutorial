package az.company.products.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessage {
    PRODUCT_NOT_FOUND("Product not found with id: %s"),
    INSUFFICIENT_QUANTITY("Insufficient quantity for product with id: %s"),
    SERVER_ERROR("Unexpected error occurred");

    private final String message;
}
