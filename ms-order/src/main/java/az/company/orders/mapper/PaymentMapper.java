package az.company.orders.mapper;

import az.company.orders.dao.entity.OrderEntity;
import az.company.orders.model.client.request.CreatePaymentRequest;
import az.company.orders.model.request.CreateOrderRequest;

import java.math.BigDecimal;

import static java.util.UUID.randomUUID;

public enum PaymentMapper {
    PAYMENT_MAPPER;

    public CreatePaymentRequest buildCreatePaymentRequest(CreateOrderRequest createOrderRequest,
                                                          OrderEntity orderEntity,
                                                          BigDecimal totalAmount) {
        return CreatePaymentRequest.builder()
                .orderId(orderEntity.getId())
                .paymentType(createOrderRequest.getPaymentType())
                .amount(totalAmount)
                .referenceNumber(randomUUID().toString())
                .build();
    }
}
