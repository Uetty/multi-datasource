package com.uetty.mulds.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Autowired
    DatasourceSwitcher datasourceSwitcher;

    @Override
    protected Object determineCurrentLookupKey() {
        // 打印当前执行的方法
        log.info("current method:{}", Thread.currentThread().getStackTrace()[2].getMethodName());
        log.info("current datasource:{}", datasourceSwitcher.getDatasourceKey());
        return datasourceSwitcher.getDatasourceKey();
    }
}
