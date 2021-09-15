package com.catfish.sharding;

import com.catfish.sharding.jpa.dto.OrderItemRequest;
import com.catfish.sharding.jpa.dto.OrderRequest;
import com.catfish.sharding.jpa.dto.OrderResponse;
import com.catfish.sharding.jpa.rpt.OrderRpt;
import com.catfish.sharding.jpa.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiuxiaotong on 2021/9/4
 */
@Slf4j
public class OrderServiceTest extends Sharding4ApplicationTests{

    @Autowired
    private OrderRpt orderRpt;

    @Autowired
    private OrderService orderService;

    @Test
    public void testCreate(){

        List<OrderItemRequest> itemList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            itemList.add(OrderItemRequest.builder()
                    .userId(1L + i)
                    .status("finish")
                    .build());
        }

        final OrderRequest request = OrderRequest.builder()
                .userId(1L)
                .itemList(itemList)
                .mobile("19921400087")
                .status("finish")
                .build();

        final Long orderId = orderService.create(request);

        log.info("订单id={}", orderId);
    }

    @Test
    public void testGet(){

        //TODO: 通过代码植入的方式，设置数据库和表的分片"余数"（如 执行ds-1数据源，则设置为1；执行t_order_0表，则设置为0）
        //final HintManager hintManager = HintManager.getInstance();
        //hintManager.addDatabaseShardingValue("t_order", 1);
        //hintManager.addTableShardingValue("t_order", 0);

        final OrderResponse response = orderService.get(641224602462695424L, 1L);

        log.info("订单详情={}", response);
    }
}