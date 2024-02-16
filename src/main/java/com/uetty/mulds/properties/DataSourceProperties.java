package com.uetty.mulds.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Data
@ConfigurationProperties(prefix = "spring")
public class DataSourceProperties {

    private Map<String, DataSourceConfig> datasources;

    @Data
    public static class DataSourceConfig {

        private String url;

        private String username;

        private String password;

        private String driverClassName = "com.mysql.cj.jdbc.Driver";

        private long connectionTimeout = 20000;

        private int maximumPoolSize = 5;

        private int minimumIdle = 1;

        private int maxLifetime = 600000;

        private String connectionTestQuery = "SELECT 1";
    }

}
