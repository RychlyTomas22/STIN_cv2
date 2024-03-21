package cz.tul.sti2024.cv.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import cz.tul.sti2024.cv.model.Payment;

import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class PaymentTransformations {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final XmlMapper xmlMapper = new XmlMapper();

/*
    public PaymentTransformations(ObjectMapper objectMapper, XmlMapper xmlMapper ){
        this.objectMapper = objectMapper;
        this.xmlMapper = xmlMapper;
    }
*/
    public Payment transformJsonIntoPayment(String payload) throws IOException {
        return objectMapper.readValue(payload, Payment.class);
    }

    public String transformXMLFromPayment(Payment payment) throws JsonProcessingException{
        return xmlMapper.writeValueAsString(payment);
    }
}