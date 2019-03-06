package db.model;

import java.util.List;

import db.jdbc.SQLManager;
import db.pojos.Client;
import db.pojos.Transaction;

public class UtilMethods {

	// -----> SUM ALL GAINS METHOD <-----
	
	// Gets a list calling Search_stored_transactions and sum all the gains from them
	public int Sum_all_client_points(SQLManager manager, Client client) {
		
		int points_gains = 0;
		List<Transaction> transactions_list = manager.Search_stored_transactions(client);
		for(Transaction transaction: transactions_list) { 
		     points_gains = points_gains + points_function(transaction.getGain());	
		}
        return points_gains;
	}
	
	public int points_function(float gain) {
		int points = 0;
		return 0;
	}
}
