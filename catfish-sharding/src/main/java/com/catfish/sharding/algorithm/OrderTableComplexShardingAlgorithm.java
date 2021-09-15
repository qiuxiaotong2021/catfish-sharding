package com.catfish.sharding.algorithm;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;

import java.util.*;

/**
 * 表维度的复合分片算法
 *
 * Created by qiuxiaotong on 2021/9/7
 */
@Slf4j
@NoArgsConstructor
public class OrderTableComplexShardingAlgorithm extends BaseShardingAlgorithm implements ComplexKeysShardingAlgorithm<Long> {

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
        return "OrderTableComplex";
    }

    /**
     * 复合分片字段的算法
     *
     * @param tableNames    业务表名称列表（所有数据源下的相同该名称的业务表, 如t_order_0、t_order_1等）
     * @param shardingValue 复合分片字段Map对象
     * @return
     */
    @Override
    public Collection<String> doSharding(Collection<String> tableNames, ComplexKeysShardingValue<Long> shardingValue) {

        final String tableName = shardingValue.getLogicTableName();

        final Long userId = findFirstValue(shardingValue, "user_id");
        final Long orderId = findFirstValue(shardingValue, "order_id");

        if(userId == 0 && orderId == 0) {
            log.warn("因没有user_id 和 order_id参数，默认执行[全表]的分片方式");
            return tableNames;
        }

        long modValue = (orderId > 0) ? (orderId % 2) : (userId % 2);

        List<String> tableList = new ArrayList<>();

        for (String name : tableNames) {
            if (name.equals(tableName + "_" + modValue)) {
                tableList.add(name);
            }
        }

        return tableList;
    }
}