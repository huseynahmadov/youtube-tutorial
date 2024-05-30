package az.company.orders.model.request;

import az.company.orders.model.enums.PaymentType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static az.company.orders.model.constants.ApplicationConstants.PRODUCT_ID_IS_REQUIRED;
import static az.company.orders.model.constants.ApplicationConstants.QUANTITY_IS_REQUIRED;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderRequest {

    @NotNull(message = PRODUCT_ID_IS_REQUIRED)
    private Long productId;

    @NotNull(message = QUANTITY_IS_REQUIRED)
    private Integer quantity;

    private PaymentType paymentType;
}
