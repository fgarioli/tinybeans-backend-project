package com.tinybeans.backend.evaluation.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tinybeans.backend.evaluation.data.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    @Transactional(readOnly = true)
    public Orders findByPaymentId(String paymentId);
}
