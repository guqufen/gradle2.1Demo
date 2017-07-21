package net.fnsco.withhold.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = "net.fnsco.withhold.service", sqlSessionFactoryRef = "masterSqlSessionFactory")
public class DataSourceConfig {

    // 精确到 master 目录，以便跟其他数据源隔离
    static final String PACKAGE = "net.fnsco.withhold.server";
    //static final String MAPPER_LOCATION = "classpath:mapper/master/*.xml";
    static final String MAPPER_LOCATION_2 = "classpath:mapper/master/**/*.xml";
    @Value("${master.datasource.url}")
    private String url;

    @Value("${master.datasource.username}")
    private String user;

    @Value("${master.datasource.password}")
    private String password;

    @Value("${master.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "masterDataSource")
    @Primary
    public DataSource masterDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "masterTransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(masterDataSource());
    }

    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
         
        PathMatchingResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
        //Resource[] resource1 = resourceLoader.getResources(MasterDataSourceConfig.MAPPER_LOCATION);
        //Resource[] resource2 = resourceLoader.getResources(MasterDataSourceConfig.MAPPER_LOCATION_2);
        //Resource[] resource = new Resource[resource1.length+resource2.length];
        //System.arraycopy(resource1, 0, resource, 0, resource1.length); 
        //System.arraycopy(resource2, 0, resource, resource1.length, resource2.length); 
        //sessionFactory.setMapperLocations(resource2);
        return sessionFactory.getObject();
    }
}