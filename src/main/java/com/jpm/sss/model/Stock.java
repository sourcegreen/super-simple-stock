package com.jpm.sss.model;

import com.jpm.sss.exception.StockTypeNotDefinedException;
import com.jpm.sss.exception.ZeroOrNegativePriceException;

public class Stock {
	
	private String symbol;			// symbol is unique, and can be used as primary key also
									// but for indexing purpose id has been setup as primary key
	private StockType type;		
	private long lastDividend; 		// value in pennies
	private double fixedDividend;	// value in pennies
	private long parValue;			// value in pennies
	
	
	public Stock(String symbol, StockType stockType, long lastDividend, long parValue) {
		this.setSymbol(symbol);
		this.setType(stockType);
		this.setLastDividend(lastDividend);
		this.setParValue(parValue);
	}
	
	public Stock(String symbol, StockType stockType, long lastDividend, double fixedDividend, long parValue) {
		this.setSymbol(symbol);
		this.setType(stockType);
		this.setLastDividend(lastDividend);
		this.setFixedDividend(fixedDividend);
		this.setParValue(parValue);
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String symbol) {
		if(symbol == null || symbol.isEmpty()) {
			throw new IllegalArgumentException("Stock symbol cannot be empty or null");
		}
		if(symbol.length() != 3) {
			throw new IllegalArgumentException("Stock symbol must be of three characters");
		}
		this.symbol = symbol;
	}
	
	public StockType getType() {
		return type;
	}
	
	public void setType(StockType type) {
		if(type == null) {
			throw new IllegalArgumentException("Stock type cannot be null");
		}
		this.type = type;
	}
	
	public long getLastDividend() {
		return lastDividend;
	}
	
	public void setLastDividend(long lastDividend) {
		this.lastDividend = lastDividend;
	}
	
	public double getFixedDividend() {
		return fixedDividend;
	}
	
	public void setFixedDividend(double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}
	
	public long getParValue() {
		return parValue;
	}
	
	public void setParValue(long parValue) {
		this.parValue = parValue;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
			.append("{")
			.append("Symbol: ")			.append(this.symbol)				.append(", ")
			.append("Type: ")			.append(this.type.getTypeName())	.append(", ")
			.append("LastDividend: ")	.append(this.lastDividend)			.append(", ")
			.append("FixedDividend: ")	.append(this.fixedDividend)			.append(", ")
			.append("Par Value: ")		.append(this.parValue)
			.append("}")
		.toString();
	}

	
	/**
	 * Calculates Dividend Yield 
	 * 
	 * @param price
	 * @return Dividend yield
	 * @throws ZeroOrNegativePriceException - if price less than or equal to zero 
	 * @throws StockTypeNotDefinedException - if Stock type is other than Common or Preferred 
	 */
	public double getDividendYield(double price) throws StockTypeNotDefinedException, ZeroOrNegativePriceException {
		if(price <= 0) {
			throw new ZeroOrNegativePriceException("Price cannot be zero or negative");
		}
		
		if (this.getType().isCommon()) {
			return this.getLastDividend() / price;

		} else if (this.getType().isPreferred()) {
			return (this.getFixedDividend() * this.getParValue()) / price;

		} else {
			throw new StockTypeNotDefinedException("Stock Type is not defined.");
		}	
	}
	
	
	/**
	 * Calculates P/E (Price Earning) ratio
	 * Formula = price / dividend 
	 * (Note: The formula doesn't describe if dividend is LastDividend or FixedDividend
	 * So it is supposed as it is dividendYield .......)
	 * 
	 * @return PE Ratio
	 * @throws ZeroOrNegativePriceException 
	 * @throws StockTypeNotDefinedException 
	 */
	public double getPERatio(double price) throws ZeroOrNegativePriceException, StockTypeNotDefinedException {
		
		double dividendYield = this.getDividendYield(price);
		
		if(dividendYield > 0) {
			return price / dividendYield;
		
		} else {
			throw new ZeroOrNegativePriceException("P/E Ratio cannot be calculated as divided yield=0");
		}
	}
	
}























