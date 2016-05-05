package com.jpm.sss;

import java.util.Date;
import org.junit.Test;
import com.jpm.sss.model.Trade;

public class TradeTest {


	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNullStockSymbol() {
		new Trade(null, new Date(), 24, 345, true);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorNullDate() {
		new Trade("POP", null, 10, 23, false);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorZeroPrice() {
		new Trade("POP", new Date(), 900, 0, true);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorNegativePrice() {
		new Trade("POP", new Date(), 900,-10, true);
	}
	
	
}
