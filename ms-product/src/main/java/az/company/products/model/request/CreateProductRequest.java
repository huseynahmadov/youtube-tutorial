package az.company.products.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static az.company.products.model.constants.ApplicationConstants.DESCRIPTION_IS_REQUIRED;
import static az.company.products.model.constants.ApplicationConstants.NAME_IS_REQUIRED;
import static az.company.products.model.constants.ApplicationConstants.PRICE_IS_REQUIRED;
import static az.company.products.model.constants.ApplicationConstants.QUANTITY_IS_REQUIRED;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductRequest {
    @NotBlank(message = NAME_IS_REQUIRED)
    private String name;

    @NotBlank(message = DESCRIPTION_IS_REQUIRED)
    private String description;

    @NotNull(message = PRICE_IS_REQUIRED)
    private BigDecimal price;

    @NotNull(message = QUANTITY_IS_REQUIRED)
    private Integer quantity;
}