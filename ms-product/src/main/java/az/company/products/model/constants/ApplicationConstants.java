package az.company.products.model.constants;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class ApplicationConstants {
    public static final String PRODUCT_ID_IS_REQUIRED = "Product id is required";
    public static final String NAME_IS_REQUIRED ="Name is required";
    public static final String DESCRIPTION_IS_REQUIRED = "Description is required";
    public static final String PRICE_IS_REQUIRED = "Price is required";
    public static final String QUANTITY_IS_REQUIRED = "Quantity is required";
}
