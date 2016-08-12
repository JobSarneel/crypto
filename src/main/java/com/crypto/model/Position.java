package com.crypto.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Position {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	String label;
	String name;
	double total;
	double onOrders;
	double btcValue;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getOnOrders() {
		return onOrders;
	}
	public void setOnOrders(double onOrders) {
		this.onOrders = onOrders;
	}
	public double getBtcValue() {
		return btcValue;
	}
	public void setBtcValue(double btcValue) {
		this.btcValue = btcValue;
	}
	
	
		
	

}
