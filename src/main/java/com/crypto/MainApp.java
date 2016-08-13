package com.crypto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.crypto.model.Position;
import com.crypto.model.Trade;
import com.crypto.util.ParsePoloCsv;
import com.crypto.util.QueryPositions;
import com.crypto.util.QueryTrades;
import com.crypto.util.Round;
import com.crypto.util.Tijd;
import com.crypto.util.WriteToDB;

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

			// Here we save each line to the database, for the trades as well as the
			// positions:
			
			WriteToDB.trades(sessionFactory, trades);
			WriteToDB.positions(sessionFactory, positions);
			System.out.println("Data written to DB.");
			
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
			
			// Now let's do something a little more complicated. We know form the
			// positions table the currrent value of our positions, but let's see
			// if we can figure out a way calculating what we paid for them. Once we
			// got that we're all set to calculate p/l percentages. Let's first try
			// to do this for our FCT position. Let's write a query method that takes a
			// label as an argument and returns the corresponding valuation
			
			double fctPositionValue = QueryPositions.getValue(sessionFactory, "FCT");
			System.out.println("The valuation of our FCT position is " + fctPositionValue +
					" BTC.");
			
			// Now we need an ordered list containing our trades involving FCT. Then we
			// iterate through it, until buy/sell orders add up to our position (in qoute
			// currency), meanwhile keeping track of what we paid in BTC.
			// So, first let's try to get the list ordered by date, starting with the most
			// recent.
			
			List<Trade> fctTradesByDateAsc = QueryPositions.getTradesFct(sessionFactory);
			
			for (Trade result : fctTradesByDateAsc) {
				System.out.printf("%25s", "Datetime: ");
				System.out.printf("%15s", Tijd.dts(result.getDateTime()));
				System.out.println();
								
				System.out.printf("%25s", "Market: ");
				System.out.printf("%-15s", result.getMarket());
				System.out.println();
				
				System.out.printf("%25s", "Type: ");
				System.out.printf("%-15s", result.getType());
				System.out.println();
				
				System.out.printf("%25s", "Amount: ");
				System.out.printf("%-15s", result.getAmount());
				System.out.println();
				
				System.out.println("*********************");				
			}
			
			// Stuff was working fine after all, just returning an empty set, because
			// i asked 'where market = Exchange'. Market should be category. But that's
			// solved now. We're actually ready to start iterating. But first we need to
			// put some variables in place.
			
			// The actual amount of fct we're holding, we get that from our positions:
			
			double fctAmount = QueryPositions.getAmount(sessionFactory, "FCT");
			
			System.out.println("We got " + fctAmount + " FCT.");
			
			// Now let's figure out what we paid for it:
			
			double workingSum = 0;
			double paid = 0;
			
			for (int i = 0; workingSum<fctAmount; i++) {
				
				Trade trade = fctTradesByDateAsc.get(i);
				
				if (trade.getType().equals("Buy")) {
					workingSum += trade.getAmount();
					paid += trade.getTotal();
				}
				
				if (trade.getType().equals("Sell")) {
					workingSum -= trade.getAmount();
					paid -= trade.getTotal();
				}
				
				System.out.println(i);
				
			}
						
			System.out.println("We paid " + Round.fourDecimals(paid) + " BTC for " + 
					Round.twoDecimals(workingSum) + " FCT." );
			System.out.println("So on FCT our profit is about " + 
					(Round.fourDecimals(fctPositionValue/paid)*100 - 100) +
					" %.");
			
			

		} catch (HibernateException ex) {
			System.out.println("Zoek hier naar problemen: ");
			ex.printStackTrace();
		}

	}

}
