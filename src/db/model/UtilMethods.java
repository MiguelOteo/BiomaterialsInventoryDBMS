package db.model;

import java.util.List;

import db.jdbc.SQLManager;
import db.pojos.Client;
import db.pojos.Transaction;

public class UtilMethods {

	// -----> SUM ALL GAINS METHOD <-----
	
	// Gets a list calling Search_stored_transactions and sum all the gains from them
	public float Sum_all_client_gains(SQLManager manager, Client client) {
		
		float transactions_gains = 0;
		List<Transaction> transactions_list = manager.Search_stored_transactions(client);
		for(Transaction transaction: transactions_list) {
		     transactions_gains = transactions_gains + transaction.getGain();	
		}
        return transactions_gains;
	}
}
