package com.catfish.sharding.algorithm;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 数据库维度的复合分片算法
 *
 * Created by qiuxiaotong on 2021/9/7
 */
@Slf4j
@NoArgsConstructor
public class OrderDbComplexShardingAlgorithm extends BaseShardingAlgorithm implements ComplexKeysShardingAlgorithm<Long> {

    @Override
    public void init() {
    }

    /**
     * 自定义复合分片类型
     *
     * @return
     */
    @Override
    public String getType() {
        return "OrderDbComplex";
    }

    /**
     * 复合分片字段的算法
     *
     * @param datasourceNames   所有数据源列表
     * @param shardingValue     复合分片字段Map对象
     * @return
     */
    @Override
    public Collection<String> doSharding(Collection<String> datasourceNames, ComplexKeysShardingValue<Long> shardingValue) {

        final Long userId = findFirstValue(shardingValue, "user_id");
        final Long orderId = findFirstValue(shardingValue, "order_id");

        if(userId == 0 && orderId == 0) {
            log.warn("因没有user_id 和 order_id参数，默认执行[所有的数据源]分片方式");
            return datasourceNames;
        }

        long modValue = (userId > 0) ? (userId % 2) : (orderId % 2);

        List<String> tableList = new ArrayList<>();

        for (String name : datasourceNames) {
            if (name.equals("ds-" + modValue)) {
                tableList.add(name);
            }
        }

        return tableList;
    }
}