package com.victor.su.config;


import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.enums.DBType;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.victor.su.dao.DynamicDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;

@Configuration
@Import({DataSourceConfig.class})
@MapperScan("com.victor.su.dao*")
@EnableTransactionManagement
public class MyBatisPlusConfig {

    @Resource(name = "routingDataSource")
    private DynamicDataSource routingDataSource;

    @Bean
    public PaginationInterceptor paginationInterceptor () {
        return new PaginationInterceptor();
    }

    @Bean
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean () throws Exception {
        MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
        mybatisPlus.setDataSource(routingDataSource);

        //全局配置，更多内容进入类看注释
        GlobalConfiguration globalConfig = new GlobalConfiguration();
        globalConfig.setDbType(DBType.MYSQL.name());
        globalConfig.setIdType(0);
        globalConfig.setFieldStrategy(FieldStrategy.NOT_EMPTY.getKey());
        mybatisPlus.setGlobalConfig(globalConfig);

        //Mybatis配置
        MybatisConfiguration mc = new MybatisConfiguration();
        mc.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        mc.setMapUnderscoreToCamelCase(true);
        mc.setCacheEnabled(true);
        mc.setDefaultStatementTimeout(60);
        mc.setLazyLoadingEnabled(true);
        mc.setMultipleResultSetsEnabled(true);
        mybatisPlus.setConfiguration(mc);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        mybatisPlus.setMapperLocations(resolver.getResources("classpath:/mybatis/mapper/*/*.xml"));
        mybatisPlus.setTypeAliasesPackage("com.victor.su.entity");

        mybatisPlus.setPlugins(new Interceptor[]{
            paginationInterceptor()
        });
        return mybatisPlus;
    }
}
