package az.company.orders.service.concrete;

import az.company.orders.client.PaymentClient;
import az.company.orders.client.ProductClient;
import az.company.orders.dao.repository.OrderRepository;
import az.company.orders.exception.NotFoundException;
import az.company.orders.model.client.request.ReduceQuantityRequest;
import az.company.orders.model.request.CreateOrderRequest;
import az.company.orders.model.response.OrderResponse;
import az.company.orders.service.abstraction.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static az.company.orders.mapper.OrderMapper.ORDER_MAPPER;
import static az.company.orders.mapper.PaymentMapper.PAYMENT_MAPPER;
import static az.company.orders.model.enums.ErrorMessage.ORDER_NOT_FOUND;
import static az.company.orders.model.enums.OrderStatus.APPROVED;
import static az.company.orders.model.enums.OrderStatus.REJECTED;
import static java.lang.String.format;
import static java.math.BigDecimal.valueOf;

@Service
@RequiredArgsConstructor
public class OrderServiceHandler implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final PaymentClient paymentClient;

    @Override
    @Transactional
    public void createOrder(CreateOrderRequest createOrderRequest) {
        var orderEntity = ORDER_MAPPER.buildOrderEntity(createOrderRequest);

        var productResponse =
                productClient.getProductById(createOrderRequest.getProductId());

        var totalAmount = productResponse.getPrice()
                .multiply(
                        valueOf(createOrderRequest.getQuantity())
                );
        orderEntity.setAmount(totalAmount);

        var reduceQuantityRequest = new ReduceQuantityRequest(
                createOrderRequest.getProductId(),
                createOrderRequest.getQuantity()
        );

        orderRepository.save(orderEntity);
        productClient.reduceQuantity(reduceQuantityRequest);

        try {
            paymentClient.pay(
                    PAYMENT_MAPPER.buildCreatePaymentRequest(
                            createOrderRequest,
                            orderEntity,
                            totalAmount)
            );
            orderEntity.setStatus(APPROVED);
        } catch (Exception exception) {
            orderEntity.setStatus(REJECTED);
        }
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        var orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                ORDER_NOT_FOUND.getMessage(),
                                id
                        )));

        var productResponse = productClient.getProductById(orderEntity.getProductId());

        var paymentResponse = paymentClient.getPaymentByOrderId(orderEntity.getId());
        return ORDER_MAPPER.buildOrderResponse(
                orderEntity,
                productResponse,
                paymentResponse
        );
    }
}
