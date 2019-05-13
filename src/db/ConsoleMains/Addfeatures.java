package db.ConsoleMains;

import db.jdbc.SQLManager;
import db.pojos.Maintenance;
import db.pojos.Utility;

public class Addfeatures {

	
	public static void main(String args[]) {
		
		SQLManager manager = new SQLManager();
		boolean everything_ok = manager.Stablish_connection();

		boolean tables_exist = manager.Check_if_tables_exist();
		if(tables_exist == true) {
		} else {
			everything_ok = manager.Create_tables();
		}
		
		
		//utility = new Utility(heat_cold, flexibility, resistance, pressure, strength)
		//maintenance = new Maintenance(pressure, o2_supply, light, humidity, temperature, compatibility, others)
		Utility utility = new Utility("cold", "yes", "no", (float)34.7, (float)65.85);
		Integer utility_id = manager.Insert_new_utility(utility);
		
		Maintenance maintenance = new Maintenance((float)34.6, "yes", "yes", 34, (float)54.7, "yes", "others");
		Integer maintenance_id = manager.Insert_new_maintenance(maintenance);
		
		
		Utility utility2 = new Utility("heat", "no", "no", (float)73.1, (float)87.06);
		Integer utility2_id = manager.Insert_new_utility(utility2);
		
		Maintenance maintenance2 = new Maintenance((float)34.6, "yes", "yes", 34, (float)54.7, "no", "others");
		Integer maintenance2_id = manager.Insert_new_maintenance(maintenance2);
		
		Utility utility3 = new Utility("heat", "yes", "yes", (float) 50.3, (float) 100.2);
		Integer utility3_id = manager.Insert_new_utility(utility3);
		
		Maintenance maintenance3 = new Maintenance((float)50.3, "no", "no", 20, (float)20.9, "yes", "others");
		Integer maintenance3_id = manager.Insert_new_maintenance(maintenance3);
		
	}
	
}
