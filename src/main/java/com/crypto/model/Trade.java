package com.crypto.model;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.joda.time.LocalDateTime;

@Entity
public class Trade {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	LocalDateTime dateTime;
	String market;
	String category;
	String type;
	double price;
	double amount;
	double total;
	double fee;
	double feeBase;
	double feeQuote;
	BigInteger orderNumber;
	double baseTotalLessFee;
	double quoteTotalLessFee;
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public double getFeeBase() {
		return feeBase;
	}
	public void setFeeBase(double feeBase) {
		this.feeBase = feeBase;
	}
	public double getFeeQuote() {
		return feeQuote;
	}
	public void setFeeQuote(double feeQuote) {
		this.feeQuote = feeQuote;
	}
	public BigInteger getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(BigInteger orderNumber) {
		this.orderNumber = orderNumber;
	}
	public double getBaseTotalLessFee() {
		return baseTotalLessFee;
	}
	public void setBaseTotalLessFee(double baseTotalLessFee) {
		this.baseTotalLessFee = baseTotalLessFee;
	}
	public double getQuoteTotalLessFee() {
		return quoteTotalLessFee;
	}
	public void setQuoteTotalLessFee(double quoteTotalLessFee) {
		this.quoteTotalLessFee = quoteTotalLessFee;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	

}
