package com.tinybeans.backend.evaluation.data.dto;

import java.util.HashMap;
import java.util.Map;

import com.tinybeans.backend.evaluation.data.entity.Orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDTO {

    private Orders orders;
    private Map<String, Object> data = new HashMap<>();

    public void addData(String key, Object value) {
        this.data.put(key, value);
    }
}
