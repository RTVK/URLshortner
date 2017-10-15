package com.tinyurl.exception;

public class TinyUrlBusinessException extends TinyUrlException {

	private String errorCode;
	private String errorMessage;

	public TinyUrlBusinessException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}
