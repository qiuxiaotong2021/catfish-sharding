package com.catfish.sharding.jpa.rpt;

import com.catfish.sharding.jpa.entity.Address;
import com.catfish.sharding.jpa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by qiuxiaotong on 2021/7/7
 */
public interface AddressRpt extends JpaRepository<Address, Long>, JpaSpecificationExecutor<Address> {

    Address getById(Long id);
}