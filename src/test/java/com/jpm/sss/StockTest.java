package com.jpm.sss;

import org.junit.Before;
import org.junit.Test;

import com.jpm.sss.exception.StockTypeNotDefinedException;
import com.jpm.sss.exception.ZeroOrNegativePriceException;
import com.jpm.sss.model.Stock;
import com.jpm.sss.model.StockType;

public class StockTest {

	private Stock stock;
	
	@Before
	public void setup() {
		stock = new Stock("Sym", new StockType("typeName"), 0, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNullStockSymbol() {
		new Stock(null, new StockType("typeName"), 0, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNullStockType() {
		new Stock("Stock symbol", null, 0, 0);
	}

	@Test(expected = ZeroOrNegativePriceException.class)
	public void testGetDividendYieldZeroPrice() throws StockTypeNotDefinedException, ZeroOrNegativePriceException {
		stock.getDividendYield(0);
	}
}
