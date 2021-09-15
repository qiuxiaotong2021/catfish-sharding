package com.catfish.sharding.jpa.controller;

import com.catfish.sharding.jpa.dto.OrderItemRequest;
import com.catfish.sharding.jpa.dto.OrderRequest;
import com.catfish.sharding.jpa.dto.OrderResponse;
import com.catfish.sharding.jpa.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 订单Controller
 *
 * Created by qiuxiaotong on 2021/8/22
 */
@RestController
@RequestMapping("/jpa/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     *
     * @return
     */
    @GetMapping("/create")
    public Long create(Long nextId){

        final OrderRequest request = OrderRequest.builder()
                .userId(nextId)
                .status("success")
                .mobile("19921400087")
                .itemList(Arrays.asList(OrderItemRequest.builder()
                        .userId(nextId)
                        .status("success")
                        .build()))
                .build();

        final Long orderId = orderService.create(request);

        return orderId;
    }

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