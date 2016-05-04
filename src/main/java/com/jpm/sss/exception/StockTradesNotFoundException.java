package com.jpm.sss.exception;

public class StockTradesNotFoundException extends Exception {

	private static final long serialVersionUID = -4469087489092007563L;

	public StockTradesNotFoundException() {
		super();
	}
	
	public StockTradesNotFoundException(String msg) {
		super(msg);
	}
}
