package com.jpm.sss.service;

import java.util.Collection;
import java.util.List;

import com.jpm.sss.exception.StockNotFoundException;
import com.jpm.sss.exception.StockTradesNotFoundException;
import com.jpm.sss.model.Stock;
import com.jpm.sss.model.Trade;

public interface StockService {

	public Stock getStock(String stockSymbol) throws StockNotFoundException;
	public void addStock(Stock stock);
	public Collection<Stock> getAllStock();
	
	public void addStockTrade(Trade trade);
	public List<Trade> getStockTrade(String stockSymbol);
	
	public double getVolumeWeightedStockPrice(String stockSymbol) throws StockTradesNotFoundException;
	public double getGBCEAllShareIndex() throws StockNotFoundException;
	
}
