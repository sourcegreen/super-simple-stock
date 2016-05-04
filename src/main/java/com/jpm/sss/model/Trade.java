package com.jpm.sss.model;

import java.util.Date;

public class Trade {
	
	private Date timeStamp;
	private long quantity;
	private double price;
	private boolean buyOrSell; 		// boolean value - true if buying, false otherwise
	private String stockSymbol;
	
	public Trade() {
	}
	
	public Trade(String stockSymbol, Date timeStamp, long quantity, double price, boolean BuyOrSell) {
		this.setStockSymbol(stockSymbol);
		this.setTimeStamp(timeStamp);
		this.setQuantity(quantity);
		this.setPrice(price);
		this.setBuyOrSell(BuyOrSell);
	}
	
	public String getStockSymbol() {
		return this.stockSymbol;
	}

	public void setStockSymbol(String symbol) {
		if(symbol == null || symbol.isEmpty()) {
			throw new IllegalArgumentException("Stock symbol cannot be empty or null");
		}
		this.stockSymbol = symbol;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}
	
	public void setTimeStamp(Date timeStamp) {
		Date now = new Date();
		if(timeStamp == null || timeStamp.getTime() > now.getTime()) {
			throw new IllegalArgumentException("Time stamp cannot be null or a future date.");
		}
		this.timeStamp = timeStamp;
	}
	
	public long getQuantity() {
		return quantity;
	}
	
	public void setQuantity(long quantity) {
		if(quantity <= 0) {
			throw new IllegalArgumentException("Quantity cannot be zero or negative quantity");
		}
		this.quantity = quantity;
	}
	
	public boolean isBuyOrSell() {
		return buyOrSell;
	}
	
	public void setBuyOrSell(boolean buyOrSell) {
		this.buyOrSell = buyOrSell;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		if(price <= 0) {
			throw new IllegalArgumentException("Trade price cannot be zero or negative");
		}
		this.price = price;
	}
	
	@Override	
	public String toString() {
		return new StringBuilder()
			.append("{")
			.append("Stock symobol: ")	.append(this.stockSymbol)	.append(", ")
			.append("Quantity: ")		.append(this.quantity)		.append(", ")
			.append("Trade Price: ")	.append(this.price)			.append(", ")
			.append("BuyOrSell: ")		.append(this.buyOrSell)		.append(", ")
			.append("Time stamp: ")		.append(this.timeStamp)
			.append("}")
		.toString();
	}
}
