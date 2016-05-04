package com.jpm.sss.dao;

import java.util.Collection;
import java.util.List;

import com.jpm.sss.model.Stock;
import com.jpm.sss.model.Trade;

public interface DataSource {

	public void addStock(Stock stock);
	public Stock getStock(String stockSymbol);
	public Collection<Stock> getAllStock();
	
	public void addStockTrade(Trade trade);
	public List<Trade> getStockTrade(String stockSymbol);
}
