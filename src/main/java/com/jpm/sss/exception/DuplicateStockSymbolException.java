package com.jpm.sss.exception;

public class DuplicateStockSymbolException extends Exception {
	private static final long serialVersionUID = -6969298878217044083L;

	public DuplicateStockSymbolException() {
		super();
	}
	
	public DuplicateStockSymbolException(String msg) {
		super(msg);
	}
}
