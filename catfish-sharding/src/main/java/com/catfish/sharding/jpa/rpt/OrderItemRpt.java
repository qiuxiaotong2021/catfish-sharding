package com.catfish.sharding.jpa.rpt;

import com.catfish.sharding.jpa.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by qiuxiaotong on 2021/7/7
 */
public interface OrderItemRpt extends JpaRepository<OrderItem, Long>, JpaSpecificationExecutor<OrderItem> {

    @Query("from OrderItem where orderItemId=?1 and order.orderId=?2 and userId=?3")
    OrderItem getById(Long id, Long orderId, Long userId);

    @Query("from OrderItem where order.orderId=?1")
    List<OrderItem> queryByOrderId(Long orderId);
}