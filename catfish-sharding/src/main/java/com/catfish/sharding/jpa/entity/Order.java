package com.catfish.sharding.jpa.entity;

import com.catfish.sharding.jpa.dto.OrderItemRequest;
import com.catfish.sharding.jpa.dto.OrderRequest;
import com.catfish.sharding.jpa.dto.OrderResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单
 *
 * Created by qiuxiaotong on 2021/8/22
 */
@Entity
@Table(name = "t_order")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Long userId;

    private String status;

    //该字段在application-sharding02.properties中配置了加密字段，默认会以mobile_cipher作为密文替代使用
    private String mobile;

    private Date createTime;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderItem> itemList = new ArrayList<>();

    public Order(OrderRequest request) {

        this.userId = request.getUserId();
        this.status = request.getStatus();
        this.mobile = request.getMobile();
        this.createTime = new Date();

        for (OrderItemRequest itemRequest : request.getItemList()) {
            this.itemList.add(new OrderItem(this, itemRequest));
        }
    }

    public OrderResponse makeOrderResponse(){
        return OrderResponse.builder()
                .orderId(orderId)
                .userId(userId)
                .status(status)
                .mobile(mobile)
                .itemList(itemList.stream().map(OrderItem::makeResponse).collect(Collectors.toList()))
                .build();
    }
}