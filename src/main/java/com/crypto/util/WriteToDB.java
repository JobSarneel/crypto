package com.crypto.util;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.crypto.model.Position;
import com.crypto.model.Trade;

public class WriteToDB {

	public static void trades(SessionFactory sessionFactory, ArrayList<Trade> trades) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		for (Trade trade : trades)
			session.save(trade);
		
		session.getTransaction().commit();
		session.close();
		
	}

	public static void positions(SessionFactory sessionFactory, ArrayList<Position> positions) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		for (Position position : positions)
			session.save(position);
		
		session.getTransaction().commit();
		session.close();
		
	}

	

}
