package com.jonm.config.dbs;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties
@EnableTransactionManagement(proxyTargetClass = true)
public class MybatisConfiguration {
    // 数据源前缀
    final static String OA_PREFIX = "spring.datasource.druid";


    //@Primary  //配置默认数据源
    @Bean(name = "myblogDataSource")
    @ConfigurationProperties(prefix = OA_PREFIX)
    public DataSource accountDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

}
