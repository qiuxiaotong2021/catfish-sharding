package com.catfish.sharding.jpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Created by qiuxiaotong on 2021/8/22
 */
//@Component
//public class DatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Long> {
//
//    @Autowired
//    private Database0Config database0Config;
//
//    @Autowired
//    private Database1Config database1Config;
//
//    @Override
//    public String doEqualSharding(Collection<String> collection, ShardingValue<Long> shardingValue) {
//        Long value = shardingValue.getValue();
//        if (value <= 20L) {
//            return database0Config.getDatabaseName();
//        } else {
//            return database1Config.getDatabaseName();
//        }
//    }
//
//    @Override
//    public Collection<String> doInSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {
//        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
//        for (Long value : shardingValue.getValues()) {
//            if (value <= 20L) {
//                result.add(database0Config.getDatabaseName());
//            } else {
//                result.add(database1Config.getDatabaseName());
//            }
//        }
//        return result;
//    }
//
//    @Override
//    public Collection<String> doBetweenSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {
//        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
//        Range<Long> range = shardingValue.getValueRange();
//        for (Long value = range.lowerEndpoint(); value <= range.upperEndpoint(); value++) {
//            if (value <= 20L) {
//                result.add(database0Config.getDatabaseName());
//            } else {
//                result.add(database1Config.getDatabaseName());
//            }
//        }
//        return result;
//    }
//}