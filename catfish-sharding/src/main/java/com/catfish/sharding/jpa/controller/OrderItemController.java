package com.catfish.sharding.jpa.controller;

import com.catfish.sharding.jpa.dto.OrderItemRequest;
import com.catfish.sharding.jpa.dto.OrderRequest;
import com.catfish.sharding.jpa.dto.OrderResponse;
import com.catfish.sharding.jpa.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * 订单Controller
 *
 * Created by qiuxiaotong on 2021/8/22
 */
@RestController
@RequestMapping("/jpa/item")
public class OrderItemController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单详情
     *
     * @return
     */
    @GetMapping("/get")
    public OrderResponse get(Long orderId, Long userId){

        final OrderResponse response = orderService.get(orderId, userId);

        return response;
    }
}