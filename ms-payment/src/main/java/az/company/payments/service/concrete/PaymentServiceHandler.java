package az.company.payments.service.concrete;

import az.company.payments.dao.repository.PaymentRepository;
import az.company.payments.exception.NotFoundException;
import az.company.payments.model.request.CreatePaymentRequest;
import az.company.payments.model.response.PaymentResponse;
import az.company.payments.service.abstraction.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static az.company.payments.mapper.PaymentMapper.PAYMENT_MAPPER;
import static az.company.payments.model.enums.ErrorMessage.PAYMENT_NOT_FOUND;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class PaymentServiceHandler implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Override
    public PaymentResponse pay(CreatePaymentRequest createPaymentRequest) {
        var paymentEntity = PAYMENT_MAPPER.buildPaymentEntity(createPaymentRequest);
        paymentRepository.save(paymentEntity);
        return PAYMENT_MAPPER.buildPaymentResponse(paymentEntity);
    }

    @Override
    public PaymentResponse getPaymentByOrderId(Long orderId) {
        return paymentRepository.findByOrderId(orderId)
                .map(PAYMENT_MAPPER::buildPaymentResponse)
                .orElseThrow(() -> new NotFoundException(
                        format(PAYMENT_NOT_FOUND.getMessage(), orderId)
                ));
    }
}
