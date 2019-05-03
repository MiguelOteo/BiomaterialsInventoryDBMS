package db.ConsoleMains;

import java.sql.Date;

import db.jdbc.SQLManager;
import db.pojos.Biomaterial;

public class AddBiomaterial {
	
	public static void main(String[] args) {
		
		SQLManager manager = new SQLManager();
		boolean everything_ok = manager.Stablish_connection();

		boolean tables_exist = manager.Check_if_tables_exist();
		if(tables_exist == true) {
		} else {
			everything_ok = manager.Create_tables();
		}
		
		
		
		Biomaterial biomaterial = new Biomaterial(null, null, "Plastic", 34, 30, Date.valueOf("2424-06-03"));
		Integer biomaterial_id = manager.Insert_new_biomaterial(biomaterial);
		biomaterial = manager.Search_biomaterial_by_id(biomaterial_id);
		
	}
}
