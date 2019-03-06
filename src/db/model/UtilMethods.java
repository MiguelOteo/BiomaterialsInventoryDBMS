package db.model;

import java.util.List;

import db.jdbc.SQLManager;
import db.pojos.Client;
import db.pojos.Transaction;

public class UtilMethods {
	
	private static Integer difficulty = 1000;

	public UtilMethods() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// -----> SUM ALL GAINS METHOD <-----
	
	// Gets a list calling Search_stored_transactions and sum all the gains from them
	public int Sum_all_client_points(SQLManager manager, Client client) {	
		int points_gains = 0;
		List<Transaction> transactions_list = manager.Search_stored_transactions(client);
		for(Transaction transaction: transactions_list) { 
		     points_gains = points_gains + (int)(transaction.getGain()/difficulty);	
		}
        return points_gains;
	}
}
