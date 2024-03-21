package cz.tul.sti2024.cv.services;

import cz.tul.sti2024.cv.model.Payment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component
public class PaymentProcessingHandler {
    private HashMap<String, IPaymentService> paymentProcessingHandler;
    private final PaymentTransformations paymentTransformations;

    public PaymentProcessingHandler(CardPaymentService cardPaymentService, CashPaymentService cashPaymentService,
                                    PaymentTransformations paymentTransformations){
        this.paymentTransformations = paymentTransformations;
        paymentProcessingHandler = new HashMap<>();
        paymentProcessingHandler.put("CASH",cashPaymentService);
        paymentProcessingHandler.put("CARD", cardPaymentService);
    }

    public void processPayment(String payload) throws IOException {
        Payment payment = paymentTransformations.transformJsonIntoPayment(payload);
        paymentProcessingHandler.get(payment.getPaymentType()).processPayment(payment);
    }
}
