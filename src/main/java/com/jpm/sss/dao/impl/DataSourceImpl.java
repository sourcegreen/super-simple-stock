package com.jpm.sss.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.jpm.sss.dao.DataSource;
import com.jpm.sss.model.Stock;
import com.jpm.sss.model.Trade;

public class DataSourceImpl implements DataSource {

	private HashMap<String, Stock> stocksMap = new HashMap<String, Stock>();
	private HashMap<String, List<Trade>> tradesMap = new HashMap<String, List<Trade>>();
	
	
	public void addStock(Stock stock) {
		this.stocksMap.put(stock.getSymbol(), stock);		
	}

	
	public Stock getStock(String stockSymbol) {
		return this.stocksMap.get(stockSymbol);
	}


	public Collection<Stock> getAllStock() {
		return this.stocksMap.values();
	}	
	
	public void addStockTrade(Trade trade) {
		List<Trade> tradesList = this.tradesMap.get(trade.getStockSymbol());
		if(tradesList == null) {
			tradesList = new ArrayList<Trade>();
		}
		tradesList.add(trade);
		tradesMap.put(trade.getStockSymbol(), tradesList);
	}
	
	
	public List<Trade> getStockTrade(String stockSymbol) {
		return this.tradesMap.get(stockSymbol);
	}
	
}