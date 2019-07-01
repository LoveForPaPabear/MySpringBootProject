package com.example.myspringproject.rocketmq.producer;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class OrderPaidEvent implements Serializable {

    private static final long serialVersionUID = -6319251531150333470L;

    private String orderId;

    private BigDecimal paidMoney;
}