package com.crypto;

import java.io.IOException;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.crypto.model.Position;
import com.crypto.model.Trade;
import com.crypto.util.ParsePoloCsv;
import com.crypto.util.QueryPositions;
import com.crypto.util.QueryTrades;

public class MainApp {

	public static void main(String[] args) throws IOException {
		// Some variables. Now we only have one: the ArrayList with the Trades
		ArrayList<Trade> trades = new ArrayList<Trade>();
		// And we add a second, with our positions:
		ArrayList<Position> positions = new ArrayList<Position>();

		// Set input file location and call parser method. First for the trades:
		String inputPath = "/home/back-up zone/workspaceJava/crypto-app/csv/test.csv";
		trades = ParsePoloCsv.readTrades(inputPath);
		
		// And then for the positions:
		String inputPath2 = "/home/back-up zone/workspaceJava/crypto-app/csv/posities.csv";
		positions = ParsePoloCsv.readPositions(inputPath2);

		// Ask Hibernate to read config
		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = sessionFactory.openSession();

			session.beginTransaction();

			// Here we save each line to the database, for the trades as well as the
			// positions:
			
			for (Trade trade : trades)
				session.save(trade);
			
			for (Position position : positions)
				session.save(position);
			
			// commit all trades to the database
			session.getTransaction().commit();
			session.close();
			
			// This is our first query. It fetches all buy orders in the ETC/BTC 
			// market. It takes the sessionFactory as an argument, and return type
			// is void for now. It just prints out it results. We may consider have
			// the method return its results in the form of an ArrayList later. Before
			// we're going to do stuff like that, first we'd like to code some other 
			// queries. So for now, we quote this one out.

			// QueryTrades.etcBuy(sessionFactory);
			
			// What else could we query for? Now that we have our positions imported,
			// let's query for the total BTC value of the account:
			
			QueryPositions.getAccountValue(sessionFactory);
			
			
			

		} catch (HibernateException ex) {
			System.out.println("Zoek hier naar problemen: ");
			ex.printStackTrace();
		}

	}

}
