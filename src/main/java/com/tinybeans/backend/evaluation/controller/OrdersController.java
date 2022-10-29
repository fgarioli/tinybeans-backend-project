package com.tinybeans.backend.evaluation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stripe.exception.StripeException;
import com.tinybeans.backend.evaluation.data.entity.Orders;
import com.tinybeans.backend.evaluation.data.repository.OrdersRepository;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersRepository ordersRepository;

    @GetMapping("/payment/{paymentId}")
    @ResponseBody
    public Orders getOrder(@PathVariable String paymentId) throws StripeException {
        return ordersRepository.findByPaymentId(paymentId);
    }
    
}
