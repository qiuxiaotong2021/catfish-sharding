package com.catfish.sharding.jpa.service;

import com.catfish.sharding.jpa.dto.OrderRequest;
import com.catfish.sharding.jpa.dto.OrderResponse;
import com.catfish.sharding.jpa.entity.Order;
import com.catfish.sharding.jpa.rpt.OrderRpt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 订单服务
 *
 * Created by qiuxiaotong on 2021/7/7
 */
@Service
@Transactional
@Slf4j
public class OrderService {

    @Autowired
    private OrderRpt orderRpt;

    /**
     * 创建订单
     *
     * @param request
     * @return
     */
    public Long create(OrderRequest request){

        final Order order = new Order(request);

        orderRpt.save(order);

        return order.getOrderId();
    }

    /**
     * 订单详情(提示：以下方法各自if else逻辑，仅为了不同case场景下，验证分库和分表的逻辑)
     *
     * @param orderId
     * @param userId
     * @return
     */
    public OrderResponse get(Long orderId, Long userId){

        Order order;

        if(orderId != null && userId != null) {

            order = orderRpt.getByIdAndUserId(orderId, userId);

            //TODO: JPA这里以对象持久化状态，修改信息，目前Sharding-jdbc会报错
            //order.setMobile("19921400087");
            //order.setStatus("ok");

        } else if(userId == null) {

            order = orderRpt.getById(orderId);

        } else {

            final List<Order> list = orderRpt.getByUserId(userId);

            //这里故意返回一个订单，仅做样例
            order = list.stream().findAny().orElse(null);
        }

        if(order == null) throw new RuntimeException("不存在的订单号");

        return order.makeOrderResponse();
    }
}