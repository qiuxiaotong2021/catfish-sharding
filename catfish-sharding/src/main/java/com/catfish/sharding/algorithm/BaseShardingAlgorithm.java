package com.catfish.sharding.algorithm;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;
import java.util.Map;

/**
 * Created by qiuxiaotong on 2021/9/7
 */
public abstract class BaseShardingAlgorithm {

    /**
     * 取出集合中的第一个元素值
     *
     * @param shardingValue 复合分片字段Map对象
     * @param columnName    分片的字段名(如 user_id 或 order_id 等)
     * @return  返回0: 表示没有该分片字段参数; >0: 表示有具体的分片字段参数值
     */
    protected Long findFirstValue(ComplexKeysShardingValue<Long> shardingValue, String columnName){

        final Map<String, Collection<Long>> columnNameAndShardingValues = shardingValue.getColumnNameAndShardingValuesMap();
        final Collection<Long> list = columnNameAndShardingValues.get(columnName);

        return CollectionUtils.isEmpty(list) ? 0L : list.stream().findFirst().get();
    }
}