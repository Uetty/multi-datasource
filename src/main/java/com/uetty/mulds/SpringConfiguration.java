package com.uetty.mulds;

import com.uetty.mulds.db.DatasourceSwitcher;
import com.uetty.mulds.db.DynamicDataSource;
import com.uetty.mulds.properties.DataSourceProperties;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
public class SpringConfiguration {

    @Bean
    public DatasourceSwitcher datasourceSwitcher(Environment env) {
        return new DatasourceSwitcher("default");
    }

    @Bean(name = "defaultDatasource")
    public HikariDataSource defaultDatasource(Environment env) {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(env.getProperty("spring.datasource.url"));
        ds.setUsername(env.getProperty("spring.datasource.username"));
        ds.setPassword(env.getProperty("spring.datasource.password"));
        ds.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        ds.setConnectionTimeout(20000);
        ds.setMaximumPoolSize(10);
        ds.setMinimumIdle(3);
        ds.setMaxLifetime(600000);
        ds.setConnectionTestQuery("select 1");

        return ds;
    }

    public Map<String, HikariDataSource> datasourceMap(DataSourceProperties dataSourceProperties) {
        Map<String, DataSourceProperties.DataSourceConfig> dataSourceConfigMap = dataSourceProperties.getDatasources();
        if (dataSourceConfigMap == null) {
            dataSourceConfigMap = new HashMap<>();
        }

        return dataSourceConfigMap.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> {
                            DataSourceProperties.DataSourceConfig dataSourceConfig = entry.getValue();

                            HikariDataSource ds = new HikariDataSource();
                            ds.setJdbcUrl(entry.getValue().getUrl());
                            ds.setUsername(entry.getValue().getUsername());
                            ds.setPassword(entry.getValue().getPassword());
                            ds.setDriverClassName(entry.getValue().getDriverClassName());
                            ds.setConnectionTimeout(entry.getValue().getConnectionTimeout());
                            ds.setMaximumPoolSize(entry.getValue().getMaximumPoolSize());
                            ds.setMinimumIdle(entry.getValue().getMinimumIdle());
                            ds.setMaxLifetime(entry.getValue().getMaxLifetime());
                            ds.setConnectionTestQuery(entry.getValue().getConnectionTestQuery());

                            return ds;
                        }
                ));
    }

    @Primary
    @Bean
    public DynamicDataSource routingDataSource(ConfigurableBeanFactory beanFactory, HikariDataSource defaultDatasource,
                                               DataSourceProperties dataSourceProperties) {

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(defaultDatasource);

        Map<String, HikariDataSource> dataSourceMap = datasourceMap(dataSourceProperties);

        dataSourceMap.forEach(beanFactory::registerSingleton);


        HashMap<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("default", defaultDatasource);
        for (Map.Entry<String, HikariDataSource> dataSourceEntry : dataSourceMap.entrySet()) {
            if ("defaultDatasource".equals(dataSourceEntry.getKey())) {
                targetDataSources.put("default", dataSourceEntry.getValue());;
            } else {
                targetDataSources.put(dataSourceEntry.getKey(), dataSourceEntry.getValue());
            }
        }
        dynamicDataSource.setTargetDataSources(targetDataSources);

        return dynamicDataSource;
    }

}
