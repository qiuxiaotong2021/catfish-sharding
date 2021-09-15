package com.catfish.sharding.jpa.rpt;

import com.catfish.sharding.jpa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.List;

/**
 * Created by qiuxiaotong on 2021/7/7
 */
public interface OrderRpt extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    @Query("from Order where orderId=?1")
    Order getById(Long id);

    @Query("from Order where orderId=?1 and userId=?2")
    Order getByIdAndUserId(Long id, Long userId);

    @Query("from Order where userId=?1")
    List<Order> getByUserId(Long userId);
}