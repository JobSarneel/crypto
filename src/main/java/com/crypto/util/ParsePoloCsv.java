package com.crypto.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.crypto.model.Position;
import com.crypto.model.Trade;

public class ParsePoloCsv {

	// Ok, what are we going to do first? I'm not sure....
	// We disovered crypto currency trading website poloniex.com provides a
	// .csv file containing the complete trading history of the account.

	// Our first plan was to just copy from the browser and paste into excel,
	// but parsing a .csv is better. (why is it better?) begging the question
	// but let's go! Obviously the first thing we need to do is read in the
	// file.

	// See? That's funny. You started with stating not being sure of what to do
	// first,
	// and you ended with 'obviously the first thing we need to do is ...'!
	// Although
	// seemingly I was procrastinating, apparently I was thinking about how to
	// start the
	// code beneath the surface nonetheless. Ok. Bravo for you. Now start your
	// stupid
	// code.

	public static ArrayList<Trade> readTrades(String path) {

		String csvFile = path;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		ArrayList<Trade> trades = new ArrayList<Trade>();

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] tradeArray = line.split(cvsSplitBy);

				// initiate new Trade object
				Trade trade = new Trade();

				// Start with date/time field
				// We need to parse the string to a IodaTime datetime object
				// to do that, first we need the format pattern to set a
				// formatter
				DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

				// with the formatter we get the DateTime object
				LocalDateTime dt = dtf.parseLocalDateTime(tradeArray[0]);
				trade.setDateTime(dt);
				
				System.out.printf("%25s", "Datetime: ");
				System.out.printf("%15s", Tijd.dts(trade.getDateTime()));
				System.out.println();

				// Market is much easier:
				trade.setMarket(tradeArray[1]);
				System.out.printf("%25s", "Market: ");
				System.out.printf("%-15s", trade.getMarket());
				System.out.println();

				// Category: exchange, lending, margin...
				trade.setCategory(tradeArray[2]);
				System.out.printf("%25s", "Category: ");
				System.out.printf("%-15s", trade.getCategory());
				System.out.println();
				
				// Type is either buy or sell
				trade.setType(tradeArray[3]);
				System.out.printf("%25s","Type: ");
				System.out.printf("%-15s", trade.getType());
				System.out.println();
				
				// And for the parsing we first do the order number number now,
				// we don't need to process the elements of the tradeArray in order
				// I think.
				trade.setOrderNumber(new BigInteger(tradeArray[8]));
				System.out.printf("%25s", "Order Number: ");
				System.out.printf("%-12d", trade.getOrderNumber());
				System.out.println();
				
				// Next we do de fee, because it is a percentage, not an amount, 
				// which needs a little different formatting
				String fee = tradeArray[7].substring(0, tradeArray[7].length() - 1);
				trade.setFee(Double.parseDouble(fee));
				System.out.printf("%25s", "Fee: ");
				System.out.printf("%.2f", trade.getFee());
				System.out.print(" %");
				System.out.println();				

				// Now we get to the numbers. First the price, denoted in base currency
				trade.setPrice(Double.parseDouble(tradeArray[4]));
				System.out.printf("%25s", "Price: ");
				System.out.printf("%11.6f", trade.getPrice());
				System.out.println();				
				
				// Here we have the amount, denoted in de quote currency. This the 
				// amount before the fee is paid.
				trade.setAmount(Double.parseDouble(tradeArray[5]));
				System.out.printf("%25s", "Amount (before fee): ");
				System.out.printf("%11.6f",trade.getAmount());
				System.out.println();
				
				// Then we want to calculate the fee in the quote currency
				trade.setFeeQuote(trade.getAmount()*trade.getFee()/100);
				System.out.printf("%25s", "Fee (in quote currency): ");
				System.out.printf("%11.6f", trade.getFeeQuote());
				System.out.println();
				
				// And then we want the amount after the fee is paid.
				trade.setQuoteTotalLessFee(Double.parseDouble(tradeArray[10]));
				System.out.printf("%25s", "Quote Total Less Fee: ");
				System.out.printf("%11.6f", trade.getQuoteTotalLessFee());
				System.out.println();
				
				// The same for the base notations: first total before fee, then fee,
				// and then total after fee.

				trade.setTotal(Double.parseDouble(tradeArray[6]));
				System.out.printf("%25s", "Total: ");
				System.out.printf("%11.6f", trade.getTotal());
				System.out.println();
				
				//feeBase has to be calculated
				trade.setFeeBase(trade.getTotal()*trade.getFee()/100);
				System.out.printf("%25s", "feeBase calculated: ");
				System.out.printf("%11.6f", trade.getFeeBase());
				System.out.println();							

				// And finally base total after fee
				trade.setBaseTotalLessFee(Double.parseDouble(tradeArray[9]));
				System.out.printf("%25s","Total (without fee): ");
				System.out.printf("%11.6f", trade.getBaseTotalLessFee());
				System.out.println();				
				
				//And add the trade to the ArrayList
				trades.add(trade);
				System.out.println("********************************");

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return trades;

	}
	
	public static ArrayList<Position> readPositions(String path) {

		String csvFile = path;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		ArrayList<Position> positions = new ArrayList<Position>();

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] positionArray = line.split(cvsSplitBy);

				// initiate new Position object
				Position position = new Position();
				
				// Id is set automatically, so our first field is label
				position.setLabel(positionArray[0]);
				
				// Next is name
				position.setName(positionArray[1]);
				
				// Then we get total
				position.setTotal(Double.parseDouble(positionArray[2]));
				
				// onOrders
				position.setOnOrders(Double.parseDouble(positionArray[3]));
				
				// And finally the BTC value
				position.setBtcValue(Double.parseDouble(positionArray[4]));		
				
				//And add the trade to the ArrayList
				positions.add(position);
				System.out.println("********************************");

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return positions;

	}

}
