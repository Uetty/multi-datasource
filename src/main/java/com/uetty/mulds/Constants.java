package com.uetty.mulds;

public class Constants {

    /**
     * 请求调用链第一层服务LogId（Spring Cloud Sleuth组件定义的第一层服务LogId）
     */
    public static final String MDC_TRACE_ID_KEY = "X-B3-TraceId";
    /**
     * 请求调用链当前服务LogId（Spring Cloud Sleuth组件定义的当前服务LogId）
     */
    public static final String MDC_SPAN_ID_KEY = "X-B3-SpanId";
    /**
     * 请求调用链上一层服务LogId（Spring Cloud Sleuth组件定义的上一层服务LogId）
     */
    public static final String MDC_PARENT_SPAN_ID_KEY = "X-B3-ParentSpanId";
}
