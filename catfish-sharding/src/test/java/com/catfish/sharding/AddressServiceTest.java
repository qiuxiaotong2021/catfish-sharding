package com.catfish.sharding;

import com.catfish.sharding.jpa.entity.Address;
import com.catfish.sharding.jpa.rpt.AddressRpt;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by qiuxiaotong on 2021/9/4
 */
@Slf4j
public class AddressServiceTest extends Sharding4ApplicationTests{

    @Autowired
    private AddressRpt addressRpt;

    @Test
    public void testCreate(){

        for (int i = 0; i < 2; i++) {

            //如果不在address表里增加用户ID，则默认创建广播表的时候，会往所有的库和表里面，insert数据
            final Address address = addressRpt.save(new Address("地址的名称" + (i + 1)));

            log.info("地址id={}", address.getId());
        }
    }

    @Test
    public void testGet(){

        //查询广播表，如果没有用户ID，则默认随机选择一个数据库，作为查询的库。
        final Address address = addressRpt.getById(641229500264722432L);
        final Address address2 = addressRpt.getById(641229500226973697L);

        log.info("地址详情 id={}, name={}", address.getId(), address.getName());
        log.info("地址详情 id={}, name={}", address2.getId(), address2.getName());
    }
}