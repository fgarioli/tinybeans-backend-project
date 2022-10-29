package com.tinybeans.backend.evaluation.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tinybeans.backend.evaluation.data.dto.OrdersDTO;
import com.tinybeans.backend.evaluation.data.entity.Item;
import com.tinybeans.backend.evaluation.data.entity.Orders;
import com.tinybeans.backend.evaluation.data.repository.OrdersRepository;
import com.tinybeans.backend.evaluation.service.PaymentService;
import com.tinybeans.backend.evaluation.service.StripeService;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private BeanFactory beans;

    @Autowired
    private OrdersRepository ordersRepository;

    private PaymentService getPaymentService() {
        return this.beans.getBean(StripeService.class);
    }

    @GetMapping("")
    @ResponseBody
    public Map<String, Object> getCheckoutData() {
        return this.getPaymentService().getCheckoutData();
    }

    @PostMapping("")
    @ResponseBody
    public OrdersDTO checkout(@RequestBody List<Item> items) throws Exception {
        Orders order = new Orders(items);
        order.setItems(items);
        ordersRepository.save(order);

        Map<String, Object> data = this.getPaymentService().createCharge(order);

        ordersRepository.flush();

        OrdersDTO dto = new OrdersDTO();
        dto.setOrders(order);
        dto.setData(data);

        return dto;
    }
}
