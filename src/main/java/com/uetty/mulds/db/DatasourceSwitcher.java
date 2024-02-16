package com.uetty.mulds.db;

public class DatasourceSwitcher {

    private String defaultDatasource;

    private ThreadLocal<String> dataSourceKey = new ThreadLocal<>();

    public DatasourceSwitcher(String defaultDatasource) {
        this.defaultDatasource = defaultDatasource;
    }

    public DatasourceSwitcher() {
    }

    public void switchDataSource(String dataSourceKey) {
        DatasourceSwitcher.this.dataSourceKey.set(dataSourceKey);
    }

    public void clearSession() {
        DatasourceSwitcher.this.dataSourceKey.remove();
    }

    public String getDatasourceKey() {
        return DatasourceSwitcher.this.dataSourceKey.get();
    }
}
