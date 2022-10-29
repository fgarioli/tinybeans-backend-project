package com.tinybeans.backend.evaluation.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.tinybeans.backend.evaluation.data.entity.Orders;

@Service
public class StripeService implements PaymentService {

    @Value("${stripe.api.private_key}")
    private String privateApiKey;

    @Value("${stripe.api.public_key}")
    private String publicApiKey;

    public Integer getPaymentIntentOrderId(String paymentIntentId) throws StripeException {
        PaymentIntent intent = PaymentIntent.retrieve(paymentIntentId);
        return Integer.parseInt(intent.getMetadata().get("order_id"));
    }

    public Map<String, Object> createCharge(Orders order) throws StripeException {
        Stripe.apiKey = this.privateApiKey;

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(order.getFinalPrice().longValue())
                .setCurrency("usd")
                .addPaymentMethodType("card")
                .putMetadata("order_id", order.getId().toString())
                .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);

        order.setPaymentId(paymentIntent.getId());

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("client_secret", paymentIntent.getClientSecret());

        return data;
    }

    @Override
    public Map<String, Object> getCheckoutData() {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("public_key", this.publicApiKey);

        return data;
    }
}
