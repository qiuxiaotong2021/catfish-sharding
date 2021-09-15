package com.catfish.sharding.jpa.entity;

import com.catfish.sharding.jpa.dto.OrderItemRequest;
import com.catfish.sharding.jpa.dto.OrderItemResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by qiuxiaotong on 2021/7/7
 */
@Entity
@Table(name = "t_order_item")
@Data
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    private Long userId;

    private String status;

    private Date createTime;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem(Order order, OrderItemRequest request){
        this.order = order;
        this.userId = request.getUserId();
        this.status = request.getStatus();
        this.createTime = new Date();
    }

    public OrderItemResponse makeResponse(){
        return OrderItemResponse.builder()
                .orderItemId(orderItemId)
                .status(status)
                .userId(userId)
                .build();
    }
}