package com.uetty.mulds;

import com.uetty.mulds.base.RespCode;
import lombok.Data;

@Data
public class BusinessException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -7924159809458281464L;

	protected RespCode errorCode;

	protected String localizedMessage;

	protected Object[] i18nParams;

	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public BusinessException(String msg) {
		super(msg);
	}

	public BusinessException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public BusinessException(String msg, Object data) {
		super(msg);
		this.data = data;
	}

	private String getDefaultLocaleMessage() {
		if (errorCode == null) {
			return null;
		}
		return errorCode.getDescription();
	}


	@Override
	public String getLocalizedMessage() {
		if (localizedMessage != null) {
			return localizedMessage;
		}
		String defaultLocaleMessage = getDefaultLocaleMessage();
		if (defaultLocaleMessage != null) {
			return defaultLocaleMessage;
		}
		return getMessage();
	}

	public BusinessException(RespCode errorCode) {
		super("error code " + errorCode.getCode());
		this.errorCode = errorCode;
	}

	public BusinessException(RespCode errorCode, Object... i18nParams) {
		super("error code " + errorCode.getCode());
		this.errorCode = errorCode;
		this.i18nParams = i18nParams;
	}

	public BusinessException(RespCode errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public BusinessException(RespCode errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}
}
