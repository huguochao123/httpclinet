package com.shls.config;

import com.shls.db.OracleDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


/**
 * Created by song on 24/01/2018.
 */
@Configuration
@MapperScan(basePackages = {"com.shls.db.dao.product"}, sqlSessionTemplateRef = "productSqlSessionTemplate")
public class ProductMybatisConfig
{
    @Bean(name = "productDataSource", destroyMethod = "close")
    @ConfigurationProperties(prefix = "product.datasource")
    @Primary
    public DataSource productDataSource() {
        //return DataSourceBuilder.create().build();
        return new OracleDataSource();
    }

    @Bean(name = "productSqlSessionFactory")
    @Primary
    public SqlSessionFactory productSqlSessionFactory(@Qualifier("productDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/shls/db/mapping/product/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "productTransactionManager")
    @Primary
    public DataSourceTransactionManager productTransactionManager(@Qualifier("productDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "productSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate productSqlSessionTemplate(@Qualifier("productSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}