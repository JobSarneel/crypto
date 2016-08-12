package com.crypto.util;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.crypto.model.Position;

public class QueryPositions {

	public static void getAccountValue(SessionFactory sessionFactory) {
		// And now we can start querying
		Session session = sessionFactory.openSession();
		session.beginTransaction();

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

}
