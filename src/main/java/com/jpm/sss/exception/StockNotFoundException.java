package com.jpm.sss.exception;

public class StockNotFoundException extends Exception {

	private static final long serialVersionUID = -4469087489092007563L;

	public StockNotFoundException() {
		super();
	}
	
	public StockNotFoundException(String msg) {
		super(msg);
	}
}
