package com.catfish.sharding.jpa.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Created by qiuxiaotong on 2021/7/7
 */
@Data
@Builder
public class OrderItemRequest {

    private Long userId;

    private String status;
}