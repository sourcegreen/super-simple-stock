package com.jpm.sss.exception;

public class StockTypeNotDefinedException extends Exception {

	private static final long serialVersionUID = -4469087489092007563L;

	public StockTypeNotDefinedException() {
		super();
	}
	
	public StockTypeNotDefinedException(String msg) {
		super(msg);
	}
}
