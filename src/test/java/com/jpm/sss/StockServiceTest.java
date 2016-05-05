package com.jpm.sss;

import org.junit.Before;
import org.junit.Test;

import com.jpm.sss.dao.DataSource;
import com.jpm.sss.dao.impl.DataSourceImpl;
import com.jpm.sss.exception.DuplicateStockSymbolException;
import com.jpm.sss.exception.StockNotFoundException;
import com.jpm.sss.exception.StockTradesNotFoundException;
import com.jpm.sss.model.Stock;
import com.jpm.sss.model.StockType;
import com.jpm.sss.service.StockService;
import com.jpm.sss.service.impl.StockServiceImpl;

public class StockServiceTest {

	StockType common;
	DataSource dataSource;
	StockService stockService ;

	@Before
	public void setUp() {
		common = new StockType("Common");
		dataSource = new DataSourceImpl();
		stockService = new StockServiceImpl(dataSource);
	}

	@Test(expected=DuplicateStockSymbolException.class)
	public void testStocksDuplicateCreation() throws DuplicateStockSymbolException {
		stockService.addStock(new Stock("TEA", common, 0, 100));
		stockService.addStock(new Stock("TEA", common, 8, 100));	
	}

	@Test(expected = StockNotFoundException.class)
	public void testStockNotFound() throws DuplicateStockSymbolException, StockNotFoundException {
		stockService.addStock(new Stock("TEA", common, 0, 100));
		stockService.addStock(new Stock("POP", common, 8, 100));
		stockService.getStock("GIN");
	}

	@Test(expected = StockTradesNotFoundException.class)
	public void testVolumeWeightedStockPriceWithNoTrades() throws StockTradesNotFoundException {
		stockService.getVolumeWeightedStockPrice("POP");
	}
}
