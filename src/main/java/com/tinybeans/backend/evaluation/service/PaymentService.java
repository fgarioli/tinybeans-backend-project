package com.tinybeans.backend.evaluation.service;

import java.util.Map;

import com.tinybeans.backend.evaluation.data.entity.Orders;

public interface PaymentService {

    public Map<String, Object> createCharge(Orders order) throws Exception;

    public Map<String, Object> getCheckoutData();
}
