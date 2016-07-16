package com.crypto.model;

import org.joda.time.DateTime;

public class Transactie {
	
	// homeCurrency is at this point either BTC or ETH
	private String homeCurrency;
	
	// foreignCurrency is the currency we are trading
	private String foreignCurrency;
	
	// buySell indates whether we are buying or selling the foreign currency
	private String buySellFC;
	
	// price indicates the price at which we are buying/selling (price in HC)
	private double price;
	
	// the amoount we are trading (in FC)
	private double amount;
	
	// the fee we have to pay for the transaction
	private double feeInHC;
	
	// Total cost, inc. fee
	private double totalInHC;
	
	// Date and time of the transaction
	private DateTime time;
	
	public String getHomeCurrency() {
		return homeCurrency;
	}
	public void setHomeCurrency(String homeCurrency) {
		this.homeCurrency = homeCurrency;
	}
	public String getForeignCurrency() {
		return foreignCurrency;
	}
	public void setForeignCurrency(String foreignCurrency) {
		this.foreignCurrency = foreignCurrency;
	}
	public String getBuySellFC() {
		return buySellFC;
	}
	public void setBuySellFC(String buySellFC) {
		this.buySellFC = buySellFC;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getFeeInHC() {
		return feeInHC;
	}
	public void setFeeInHC(double feeInHC) {
		this.feeInHC = feeInHC;
	}
	public double getTotalInHC() {
		return totalInHC;
	}
	public void setTotalInHC(double totalInHC) {
		this.totalInHC = totalInHC;
	}
	public DateTime getTime() {
		return time;
	}
	public void setTime(DateTime time) {
		this.time = time;
	}
	
	

}
