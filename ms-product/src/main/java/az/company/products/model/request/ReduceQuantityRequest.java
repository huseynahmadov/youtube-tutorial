package az.company.products.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static az.company.products.model.constants.ApplicationConstants.PRODUCT_ID_IS_REQUIRED;
import static az.company.products.model.constants.ApplicationConstants.QUANTITY_IS_REQUIRED;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReduceQuantityRequest {
    @NotNull(message = PRODUCT_ID_IS_REQUIRED)
    private Long productId;

    @NotNull(message = QUANTITY_IS_REQUIRED)
    private Integer quantity;
}
