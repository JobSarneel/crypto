package com.crypto.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.crypto.model.Position;
import com.crypto.model.Trade;

public class QueryPositions {

	public static void getAccountValue(SessionFactory sessionFactory) {
		// And now we can start querying
		Session session = sessionFactory.openSession();

		TypedQuery<Position> query = session.createQuery("from Position", Position.class);
		List<Position> thePositions = query.getResultList();

		System.out.println("List 'thePositions' has length " + thePositions.size());	

		int n = 0;
		double totalBTCValue = 0;

		for (Position position : thePositions) {
			n += 1;			
			totalBTCValue += position.getBtcValue();
			System.out.println("Positie " + n + ": ");
			System.out.printf("%20s", position.getName() + ":");
			System.out.printf("%12.4f", position.getTotal());
			System.out.printf("%6s", position.getLabel());
			System.out.printf("%10.4f", position.getBtcValue());
			System.out.printf("%6s", "BTC");
			System.out.printf("%10.6f", position.getImpliedPrice());
			System.out.printf("%6s", "BTC");
			System.out.println();				
		}		

		System.out.println("Totale accountwaarde: " + totalBTCValue + " BTC.");


		session.close();
	}
	
	public static double getAmount(SessionFactory sessionFactory, String coin) {
		// This should not be hard	
		Session session = sessionFactory.openSession();

		TypedQuery<Position> query = session.createQuery("from Position where label = "
				+ ":code", Position.class);
		query.setParameter("code", coin);

		Position fctPosition = query.getSingleResult();
		session.close();
		return fctPosition.getTotal();		
	}

	public static double getValue(SessionFactory sessionFactory, String coin) {
		// TODO Auto-generated method stub
		// And now we can start querying
		Session session = sessionFactory.openSession();

		TypedQuery<Position> query = session.createQuery("from Position where label = "
				+ ":code", Position.class);
		query.setParameter("code", coin);

		Position fctPosition = query.getSingleResult();
		session.close();
		return fctPosition.getBtcValue();
	}

	public static List<Trade> getTradesFct(SessionFactory sessionFactory) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();

		TypedQuery<Trade> query = session.createQuery("from Trade where market = "
				+ ":market and category = :category order by dateTime desc", Trade.class);
		query.setParameter("market", "FCT/BTC");		
		query.setParameter("category", "Exchange");
		List<Trade> resultset = query.getResultList();
		session.close();
		return resultset;
	}

}
