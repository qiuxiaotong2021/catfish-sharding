package com.catfish.sharding;

import com.catfish.sharding.jpa.entity.OrderItem;
import com.catfish.sharding.jpa.rpt.OrderItemRpt;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by qiuxiaotong on 2021/9/4
 */
@Slf4j
public class OrderItemServiceTest extends Sharding4ApplicationTests{

    @Autowired
    private OrderItemRpt orderItemRpt;

    @Test
    public void testQueryOrderItem(){

        //1.根据用户id，走分库策略
        //2.根据订单id，走分表策略
        //3.根据itemID，查具体item对象
        //反之，如果没有指定用户id、订单id，则默认走所有的库和所有的表，最后根据itemID做聚合出一个item对象
        final OrderItem item = orderItemRpt.getById(640871497246158849L,640871497007083520L, 1L);
        if(item != null) {
            log.info("orderItem -> {}, ", item.makeResponse());
        }

        //根据根据订单id，走分表策略，因没有指定userId，默认走所有的库查询。
        final List<OrderItem> list = orderItemRpt.queryByOrderId(640873895385608192L);
        list.stream().forEach(orderItem -> log.info("orderItem ---> {}", orderItem.makeResponse()));
    }
}