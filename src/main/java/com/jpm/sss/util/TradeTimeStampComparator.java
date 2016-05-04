package com.jpm.sss.util;

import java.util.Comparator;

import com.jpm.sss.model.Trade;

public class TradeTimeStampComparator implements Comparator<Trade> {

	public int compare(Trade t1, Trade t2) {
		return t1.getTimeStamp().compareTo(t2.getTimeStamp());
	}

}
