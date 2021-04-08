package com.jonm.config.dbs;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.mybatis.spring.annotation.MapperScan;


import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.jonm.dao", sqlSessionTemplateRef  = "myblogSqlSessionTemplate", sqlSessionFactoryRef = "myblogSqlSessionFactory")
public class myblogDataSourceConfiguration {
    public static final String MAPPER_XML_LOCATION = "classpath*:com/jonm/mapper/*.xml";

    public myblogDataSourceConfiguration(){
        System.out.println("AccountDataSourceConfiguration init---------------------------");
    }

    @Autowired
    @Qualifier("myblogDataSource")
    DataSource myblogDataSource;

    @Primary
    @Bean
    public SqlSessionFactory myblogSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(myblogDataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_XML_LOCATION));
        return bean.getObject();
    }

    @Primary
    @Bean(name = "myblogTransactionManager")
    public DataSourceTransactionManager myblogTransactionManager() {
        return new DataSourceTransactionManager(myblogDataSource);
    }

    @Primary
    @Bean
    public SqlSessionTemplate myblogSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(myblogSqlSessionFactory());
    }

}
