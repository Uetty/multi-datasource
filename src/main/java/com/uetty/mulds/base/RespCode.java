package com.uetty.mulds.base;

import com.uetty.mulds.BusinessException;
import lombok.Getter;

@Getter
public enum RespCode {

    /**
     * 成功
     */
    SUCCESS(20000, "成功返回code"),

    /**
     * 通用失败code
     */
    ERROR(30000, "通用失败返回code"),

    ;
    private final int code;
    private final String description;

    RespCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public BusinessException newException() {
        return new BusinessException(this);
    }

    public BusinessException newException(Object... params) {
        return new BusinessException(this, params);
    }

}
