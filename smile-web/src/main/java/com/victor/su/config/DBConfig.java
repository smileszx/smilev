package com.victor.su.config;

import com.victor.su.dao.DBTypeEnum;
import com.victor.su.dao.DynamicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public class DBConfig implements TransactionManagementConfigurer{

    @Bean(name="dboneDataSource")
    @Qualifier("dboneDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.dbone")
    public DataSource dbOne() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="dbtwoDataSource")
    @Qualifier("dbtwoDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.dbtwo")
    public DataSource dbTwo() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="routingDataSource")
    public DynamicDataSource roundRobinDataSourceProxy() {
        DynamicDataSource proxy = new DynamicDataSource();

        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DBTypeEnum.dbone.getValue(), dbOne());
        targetDataSource.put(DBTypeEnum.dbtwo.getValue(), dbTwo());
        proxy.setDefaultTargetDataSource(dbOne());
        proxy.setTargetDataSources(targetDataSource);
        return proxy;
    }

    /**
     * 事务配置，考虑多数据源情况
     * @return
     */
    @Bean
    public PlatformTransactionManager txManager () {
        return new DataSourceTransactionManager(roundRobinDataSourceProxy());
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return txManager();
    }
}
