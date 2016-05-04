package com.jpm.sss.exception;

public class ZeroOrNegativePriceException extends Exception {
	private static final long serialVersionUID = 4311120644920701138L;

	public ZeroOrNegativePriceException() {
		super();
	}
	
	public ZeroOrNegativePriceException(String msg) {
		super(msg);
	}
}
