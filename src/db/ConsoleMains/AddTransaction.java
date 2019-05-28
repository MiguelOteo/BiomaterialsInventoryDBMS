package db.ConsoleMains;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import db.jdbc.SQLManager;
import db.pojos.Biomaterial;
import db.pojos.Client;
import db.pojos.Maintenance;
import db.pojos.Transaction;
import db.pojos.Utility;

public class AddTransaction {
	
	public static void main(String[] args) {
		
		SQLManager manager = new SQLManager();
		boolean everything_ok = manager.Stablish_connection();
		
		Utility utility = new Utility("cold", "yes", "no", (float)34.7, (float)65.85);
		Integer utility_id = manager.Insert_new_utility(utility);
		utility = manager.Search_utility_by_id(utility_id);
		
		Maintenance maintenance = new Maintenance((float)34.6, "yes", "yes", 34, (float)54.7, "yes", "others");
		Integer maintenance_id = manager.Insert_new_maintenance(maintenance);
		maintenance = manager.Search_maintenance_by_id(maintenance_id);
		
		List<Biomaterial> biomaterial_list = new LinkedList<Biomaterial>();
		Biomaterial biomaterial = new Biomaterial(utility, maintenance, "Plastic", (float)34, 35, Date.valueOf("3424-06-03"));
		Biomaterial biomaterial2 = new Biomaterial(utility, maintenance, "Metal", (float)4, 10, Date.valueOf("3424-06-03"));
		Biomaterial biomaterial3 = new Biomaterial(utility, maintenance, "KK", (float)344, 30, Date.valueOf("3424-06-03"));
		Integer biomaterial_id = manager.Insert_new_biomaterial(biomaterial);
		Integer biomaterial_id2 = manager.Insert_new_biomaterial(biomaterial2);
		Integer biomaterial_id3 = manager.Insert_new_biomaterial(biomaterial3);
		biomaterial = manager.Search_biomaterial_by_id(biomaterial_id);
		biomaterial2 = manager.Search_biomaterial_by_id(biomaterial_id2);
		biomaterial3 = manager.Search_biomaterial_by_id(biomaterial_id3);
		biomaterial_list.add(biomaterial);
		biomaterial_list.add(biomaterial2);
		biomaterial_list.add(biomaterial3);
		
		//Transaction(Float gain, Integer client_id, Integer units, Integer product_id);
		Client client = manager.Search_client_by_id(2);
		Transaction transaction = new Transaction((float)10, 34243, biomaterial_list, client);
		System.out.println(transaction);
		System.out.println(transaction.getBiomaterial_list());
		manager.Insert_new_transaction(transaction);
		
		System.out.println("\n\n");
		
		List<Transaction> transaction_list = manager.List_all_transactions();
		for(Transaction transaction2: transaction_list) {
			System.out.println(transaction2);
			for(Biomaterial biomaterial4: transaction2.getBiomaterial_list()) {
				System.out.println(biomaterial4);
			}
		}
	}
} 
