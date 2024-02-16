package com.uetty.mulds.base;

import com.uetty.mulds.Constants;
import lombok.Data;
import org.slf4j.MDC;

@Data
public class RespBody<T> {

	private int code;
	private String message;
	private String requestId;
	private T data;

	public RespBody() {}

	public RespBody(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
		try {
			setRequestId(MDC.get(Constants.MDC_TRACE_ID_KEY));
		} catch (Exception ignore) {}
	}

	public static <T> RespBody<T> success() {
		return new RespBody<>(RespCode.SUCCESS.getCode(), null, null);
	}

	public static <T> RespBody<T> success(T data) {
		return new RespBody<>(RespCode.SUCCESS.getCode(), null, data);
	}

	public static <T> RespBody<T> fail(String message) {
		return new RespBody<>(RespCode.ERROR.getCode(), message, null);
	}

}
