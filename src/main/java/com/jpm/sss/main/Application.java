package com.jpm.sss.main;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import com.jpm.sss.dao.DataSource;
import com.jpm.sss.dao.impl.DataSourceImpl;
import com.jpm.sss.exception.StockNotFoundException;
import com.jpm.sss.exception.StockTradesNotFoundException;
import com.jpm.sss.exception.StockTypeNotDefinedException;
import com.jpm.sss.exception.ZeroOrNegativePriceException;
import com.jpm.sss.model.Stock;
import com.jpm.sss.model.StockType;
import com.jpm.sss.model.Trade;
import com.jpm.sss.service.StockService;
import com.jpm.sss.service.impl.StockServiceImpl;

public class Application {

	public static void main(String[] args) {
		
		// Initialise the datasource and service
		DataSource dataSource = new DataSourceImpl();
		StockService stockService = new StockServiceImpl(dataSource);
		
		// Define StockType
		StockType common = new StockType("Common");
		StockType preferred = new StockType("Preferred");
		
		// Record Stock data
		stockService.addStock(new Stock("TEA", common, 0, 100));
		stockService.addStock(new Stock("POP", common, 8, 100));
		stockService.addStock(new Stock("ALE", common, 23,  60));
		stockService.addStock(new Stock("GIN", preferred, 8, 0.2, 100));  
		stockService.addStock(new Stock("JOE", common, 13, 250));

		// Retrieve stock data and perform the required operations.
		Collection<Stock> stockList = stockService.getAllStock();
		Iterator<Stock> iterator = stockList.iterator();
		Random random = new Random(Integer.MAX_VALUE);
		
		while (iterator.hasNext()) {
			Stock stock = (Stock) iterator.next();
			double price = Math.abs(random.nextInt());
			
			System.out.println("\n"+ stock+"\n===========================");
			
			// Get dividend yield
			try {
				System.out.println("Dividend yield: " + stock.getDividendYield(price));
			
			} catch (StockTypeNotDefinedException | ZeroOrNegativePriceException e) {
				System.out.println(e.getMessage());
			}
			
			
			// Get P/E Ratio 
			try {
				System.out.println("P/E Rato: " + stock.getPERatio(price));
			
			} catch (StockTypeNotDefinedException | ZeroOrNegativePriceException e) {
				System.out.println(e.getMessage());
			}
			
			
			// Record a few trades for the stock and calculate volume weighted stock price
			System.out.println("Random Trades added:");
			for(int i = 0; i < 5; i++) {
				long quantity = Math.abs(random.nextInt());
				double tradePrice = Math.abs(random.nextInt());
				boolean buyOrSell = random.nextBoolean();
				Trade aTrade = new Trade(stock.getSymbol(), new Date(), quantity, tradePrice, buyOrSell);
				stockService.addStockTrade(aTrade);
				System.out.println(aTrade);
			}
			
			
			// 	Volume weighted stock price 
			try {
				System.out.print("Volume weighted stock price: ");
				System.out.println(stockService.getVolumeWeightedStockPrice(stock.getSymbol()));
				
			} catch (StockTradesNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
		
		// Determine GBCE All Share Index
		System.out.println("\nGBCE All Share Index \n===============");
		try {
			System.out.println(stockService.getGBCEAllShareIndex());
			
		} catch (StockNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
	}
}

























