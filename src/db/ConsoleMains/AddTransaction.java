package db.ConsoleMains;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;

import db.jdbc.SQLManager;
import db.pojos.Biomaterial;
import db.pojos.Maintenance;
import db.pojos.Transaction;
import db.pojos.Utility;

public class AddTransaction {
	
	public static void main(String[] args) {
		
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		SQLManager manager = new SQLManager();
		boolean everything_ok = manager.Stablish_connection();

		boolean tables_exist = manager.Check_if_tables_exist();
		if(tables_exist == true) {
		} else {
			everything_ok = manager.Create_tables();
		}
		
		Utility utility = new Utility("cold", "yes", "no", (float)34.7, (float)65.85);
		manager.Insert_new_utility(utility);
		Maintenance maintenance = new Maintenance((float)34.6, "yes", "yes", 34, (float)54.7, "yes", "others");
		manager.Insert_new_maintenance(maintenance);
		Biomaterial biomaterial = new Biomaterial(1, 1, "Plastic", (float)34.2, 30, Date.valueOf("3424-06-03"));
		manager.Insert_new_biomaterial(biomaterial);
		Transaction transaction = new Transaction((float)23.654, 1, 45433, 1);
		manager.Insert_new_transaction(transaction);
	}
}
