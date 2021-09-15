package com.catfish.sharding.algorithm;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 数据库和表维度的Hint强制分片算法
 *
 * Created by qiuxiaotong on 2021/9/7
 */
@Slf4j
@NoArgsConstructor
public class OrderHintShardingAlgorithm extends BaseShardingAlgorithm implements HintShardingAlgorithm<Integer> {

    @Override
    public void init() {
    }

    /**
     * 自定义Hint强制分片类型
     *
     * @return
     */
    @Override
    public String getType() {
        return "OrderHint";
    }

    /**
     * 复合分片字段的算法
     *
     * @param dsNamesOrTableNames   所有数据源列表 或 业务表名称列表（当业务植入代码是add数据库时就是所有数据源列表，反之add表时就是业务表名称列表）
     * @param shardingValue         Hint强制分片植入参数对象
     * @return
     */
    @Override
    public Collection<String> doSharding(Collection<String> dsNamesOrTableNames, HintShardingValue<Integer> shardingValue) {

        List<String> tableList = new ArrayList<>();

        for (String name : dsNamesOrTableNames) {
            for (Integer dsOrTableShardingValue : shardingValue.getValues()) {
                if(name.endsWith(String.valueOf(dsOrTableShardingValue % 2))) {
                    tableList.add(name);
                }
            }
        }

        return tableList;
    }
}