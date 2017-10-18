package com.shearf.cloud.apps.user.center.web.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author xiahaihu2009@gmail.com
 */
@Configuration
@PropertySource("classpath:database.properties")
@MapperScan(sqlSessionFactoryRef = "sqlSessionFactory", basePackages = "com.shearf.cloud.apps.user.center.dal.mapper", annotationClass = Repository.class)
public class DruidDatasourceConfiguration implements EnvironmentAware {

    private Environment environment;

    @Bean(initMethod = "init", destroyMethod = "close")
    public DruidDataSource dataSource() {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(environment.getProperty("database.driver-class-name"));
        dataSource.setUrl(environment.getProperty("database.url"));
        dataSource.setUsername(environment.getProperty("database.username"));
        dataSource.setPassword(environment.getProperty("database.password"));
        dataSource.setName(environment.getProperty("database.name"));

        try {
            dataSource.setFilters(environment.getProperty("database.filters"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dataSource.setMaxActive(Integer.parseInt(environment.getProperty("database.maxActive")));
        dataSource.setInitialSize(Integer.parseInt(environment.getProperty("database.initialSize")));
        dataSource.setMaxWait(Long.parseLong(environment.getProperty("database.maxWait")));
        dataSource.setMinIdle(Integer.parseInt(environment.getProperty("database.minIdle")));
        dataSource.setTimeBetweenEvictionRunsMillis(
                Long.parseLong(environment.getProperty("database.timeBetweenEvictionRunsMillis")));
        dataSource.setMinEvictableIdleTimeMillis(
                Long.parseLong(environment.getProperty("database.minEvictableIdleTimeMillis")));
        dataSource.setValidationQuery(environment.getProperty("database.validationQuery"));
        dataSource.setTestOnBorrow(Boolean.parseBoolean(environment.getProperty("database.testOnBorrow")));
        dataSource.setTestWhileIdle(Boolean.parseBoolean(environment.getProperty("database.testWhileIdle")));
        dataSource.setTestOnReturn(Boolean.parseBoolean(environment.getProperty("database.testOnReturn")));
        dataSource.setPoolPreparedStatements(
                Boolean.parseBoolean(environment.getProperty("database.poolPreparedStatements")));
        dataSource.setMaxOpenPreparedStatements(
                Integer.parseInt(environment.getProperty("database.maxOpenPreparedStatements")));

        return dataSource;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSourceTransactionManager txManager(DataSource dataSource) {
        DataSourceTransactionManager txManager = new DataSourceTransactionManager();
        txManager.setDataSource(dataSource);
        return txManager;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:/dal/mapper/*Mapper.xml"));
        sessionFactory.setTypeAliasesPackage("com.shearf.cloud.apps.user.center.domain.model");
        return sessionFactory;
    }
}
