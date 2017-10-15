package com.tinyurl.exception;

public class TinyUrlSystemException extends TinyUrlException {

	private String errorCode;
	private String errorMessage;

	public TinyUrlSystemException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}
