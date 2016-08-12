package com.crypto.util;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.crypto.model.Trade;

public class QueryTrades {

	public static void etcBuy(SessionFactory sessionFactory) {
		// And now we can start querying
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		TypedQuery<Trade> query = session.createQuery("from Trade where market="
				+ "'ETC/BTC' AND type='Buy'", Trade.class);
		List<Trade> theTrades = query.getResultList();

		System.out.println("List 'theTrades' has length " + theTrades.size());

		int n = 0;

		double sumPrices = 0;
		double totalETC = 0;
		double totalPaid = 0;

		for (Trade trade : theTrades) {
			n += 1;
			totalETC += trade.getAmount();
			sumPrices += trade.getPrice();
			totalPaid += trade.getTotal();
			System.out.println("resultaat " + n + ": ");
			System.out.printf("%20s", Tijd.dts(trade.getDateTime()));
			System.out.printf("%10s", trade.getType());
			System.out.printf("%15s", trade.getAmount());
			System.out.printf("%15s", trade.getPrice());
			System.out.printf("%15s", trade.getBaseTotalLessFee());
			System.out.println();				
		}		

		System.out.println("Dus totaal ETC gekocht: " + totalETC + " ETC.");
		System.out.println("En totaal betaald: " + totalPaid + " BTC.");
		System.out.println("Gemiddelde prijs moet dan zijn: " + totalPaid/totalETC);			

		session.close();
	}

}
