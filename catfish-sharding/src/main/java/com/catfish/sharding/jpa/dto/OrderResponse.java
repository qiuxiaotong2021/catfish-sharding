package com.catfish.sharding.jpa.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiuxiaotong on 2021/7/7
 */
@Data
@Builder
public class OrderResponse {

    private Long orderId;

    private Long userId;

    private String status;

    private String mobile;

    private List<OrderItemResponse> itemList = new ArrayList<>();
}