package com.catfish.sharding.jpa.config;

import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.infra.config.properties.ConfigurationPropertyKey;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.keygen.KeyGenerateStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by qiuxiaotong on 2021/8/22
 */
//@Configuration
//public class DataSourceConfig {
//
//    @Autowired
//    private Database0Config database0Config;
//
//    @Autowired
//    private Database1Config database1Config;
//
////    @Autowired
////    private DatabaseShardingAlgorithm databaseShardingAlgorithm;
////
////    @Autowired
////    private TableShardingAlgorithm tableShardingAlgorithm;
//
//    /**
//     * 创建数据源
//     *
//     * @return
//     * @throws SQLException
//     */
//    @Primary
//    @Bean
//    public DataSource dataSource() throws SQLException {
//
//        // 数据源Map集合
//        Map<String, DataSource> dataSourceMap = new HashMap<>();
//        dataSourceMap.put(database0Config.getDatabaseName(), database0Config.createDataSource());
//        dataSourceMap.put(database1Config.getDatabaseName(), database1Config.createDataSource());
//
//
//        // 分片规则配置
//        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
//
//        // 分片表（t_order）规则配置
//        ShardingTableRuleConfiguration orderTableRuleConfig = new ShardingTableRuleConfiguration("t_order", "ds${0..1}.t_order_${0..1}");
//
//        // 分片表（t_order）的 分库+分表 策略
//        //【说明】：
//        //1、如果指定了库和表，则分别映射到库、表中查询；
//        //2、如果指定了库，没有指定表，则映射的库和表都相同，以库的策略为准；
//        //3、如果没有指定库，指定了表，则查询所有库的该特定表；
//        orderTableRuleConfig.setDatabaseShardingStrategy(new StandardShardingStrategyConfiguration("user_id", "dbShardingAlgorithm"));
//        orderTableRuleConfig.setTableShardingStrategy(new StandardShardingStrategyConfiguration("id", "tableShardingAlgorithm"));
//
//        orderTableRuleConfig.setKeyGenerateStrategy(new KeyGenerateStrategyConfiguration("id", "snowflakeName"));
//
//        // 分片表（t_order）规则配置，添加到 分配规则配置中
//        shardingRuleConfig.getTables().add(orderTableRuleConfig);
//
//
//        // 省略配置 t_order_item 表规则...
//        // ...
//
//
//        // 分库算法
//        Properties dbShardingAlgorithmrProps = new Properties();
//        dbShardingAlgorithmrProps.setProperty("algorithm-expression", "ds${user_id % 2}");
//        shardingRuleConfig.getShardingAlgorithms().put("dbShardingAlgorithm", new ShardingSphereAlgorithmConfiguration("INLINE", dbShardingAlgorithmrProps));
//
//        // 分表算法
//        Properties tableShardingAlgorithmrProps = new Properties();
//        tableShardingAlgorithmrProps.setProperty("algorithm-expression", "t_order_${id % 2}");
//        shardingRuleConfig.getShardingAlgorithms().put("tableShardingAlgorithm", new ShardingSphereAlgorithmConfiguration("INLINE", tableShardingAlgorithmrProps));
//
//        // 分布式snowflake ID算法配置
//        shardingRuleConfig.getKeyGenerators().put("snowflakeName", new ShardingSphereAlgorithmConfiguration("SNOWFLAKE", getProperties()));
//
//
//        // 创建 ShardingSphereDataSource
//        DataSource dataSource = ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, Collections.singleton(shardingRuleConfig), getProperties());
//
//        return dataSource;
//    }
//
//    /**
//     * 扩展属性配置
//     *
//     * @return
//     */
//    private Properties getProperties() {
//
//        Properties result = new Properties();
//
//        result.setProperty(ConfigurationPropertyKey.SQL_SHOW.getKey(), "true");
//        result.setProperty(ConfigurationPropertyKey.EXECUTOR_SIZE.getKey(), "4");
//        result.setProperty(ConfigurationPropertyKey.MAX_CONNECTIONS_SIZE_PER_QUERY.getKey(), "5");
//
//        result.setProperty("worker-id", "1020");
//
//        return result;
//    }
//}