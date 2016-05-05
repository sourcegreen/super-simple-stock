package com.jpm.sss.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.jpm.sss.dao.DataSource;
import com.jpm.sss.exception.DuplicateStockSymbolException;
import com.jpm.sss.exception.StockNotFoundException;
import com.jpm.sss.exception.StockTradesNotFoundException;
import com.jpm.sss.model.Stock;
import com.jpm.sss.model.Trade;
import com.jpm.sss.service.StockService;
import com.jpm.sss.util.TradeTimeStampComparator;

public class StockServiceImpl implements StockService {

	private DataSource dataSource;
	
	public StockServiceImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void addStock(Stock stock) throws DuplicateStockSymbolException {
		try {
			this.getStock(stock.getSymbol());
			throw new DuplicateStockSymbolException("Stock exists already with symbol "+stock.getSymbol());
			
		} catch (StockNotFoundException e) {
			dataSource.addStock(stock);
		}
	}

	public Stock getStock(String stockSymbol) throws StockNotFoundException {
		Stock stock = dataSource.getStock(stockSymbol);
		if(stock == null) {
			throw new StockNotFoundException("Stock not found with Symbol: "+stockSymbol);
		}
		return stock;
	}	
	
	public Collection<Stock> getAllStock() {
		return dataSource.getAllStock();
	}
	
	
	public void addStockTrade(Trade trade) {
		dataSource.addStockTrade(trade);
	}
	

	public List<Trade> getStockTrade(String stockSymbol) {
		return dataSource.getStockTrade(stockSymbol);

	}
	/**
	 * Calculates volume weighted Stock Price
	 * 
	 * @return volume weighted Stock Price - value in double
	 * @throws StockTradesNotFoundException - if no trades found for the stock in last 5 minutes. 
	 */
	public double getVolumeWeightedStockPrice(String stockSymbol) throws StockTradesNotFoundException {
		
		// Get trades recorded for the stock
		List<Trade> stockTrades = this.getStockTrade(stockSymbol);
		if(stockTrades == null) {
			throw new StockTradesNotFoundException("No trades found for stock symbol: " + stockSymbol);
		}
		
		// sort the tradesList according to the time descending
		Collections.sort(stockTrades, Collections.reverseOrder(new TradeTimeStampComparator()));
		
 		long sigmaQuantity = 0;
		double sigmaTradePriceQuntity = 0.0;
		Date fiveMinutes = new Date(new Date().getTime() - 5 * 60 * 1000);
				
		// if trades count more than zero
		Iterator<Trade> itr = stockTrades.iterator();
		while (itr.hasNext()) {
			Trade trade = itr.next();
			if(trade.getTimeStamp().after(fiveMinutes)) {
				sigmaTradePriceQuntity += trade.getPrice() * trade.getQuantity();
				sigmaQuantity 		   += trade.getQuantity();
			} else {
				break;
			}
		}
			
		// No trades found in last five minutes
		if(sigmaQuantity == 0) {
			throw new StockTradesNotFoundException("No trade found in last 5 minutes for stock symbol: "+ stockSymbol);

		// if some trades found, return volume Weighted Stock Price
		} else { 
			return sigmaTradePriceQuntity / sigmaQuantity;
		}
	}
	
	
	/**
	 * Calculates GCBE All share index
	 * 
	 * @throws StockNotFoundException 
	 */
	public double getGBCEAllShareIndex() throws StockNotFoundException {
		
		// Retrieve all stock symbols to iterate them through
		Collection<Stock> stockList = this.getAllStock();
		
		// if stock list is empty
		if(stockList.size() == 0) {
			throw new StockNotFoundException("GBCE All Share Index cannot determined as no stocks found.");
		}

		Iterator<Stock> itr = stockList.iterator();
		double geomMean = 1;
		while(itr.hasNext()) {
			try {
				geomMean = geomMean * this.getVolumeWeightedStockPrice(itr.next().getSymbol());
			
			} catch (StockTradesNotFoundException e) {
				// No Stock trades found - continue;
			}
		}
		return Math.pow(geomMean, 1.0/stockList.size());
	}
}
