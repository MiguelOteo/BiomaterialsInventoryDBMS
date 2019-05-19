package db.ConsoleMains;

import java.sql.Date;

import db.jdbc.SQLManager;
import db.pojos.Biomaterial;

public class AddBiomaterial {
	
	public static void main(String[] args) {
		
		SQLManager manager = new SQLManager();
		boolean everything_ok = manager.Stablish_connection();

		Biomaterial biomaterial = new Biomaterial(null, null, "Metal bar", (float)34, 30, Date.valueOf("2424-06-03"));
		biomaterial.setInformation("none");
		Integer biomaterial_id = manager.Insert_new_biomaterial(biomaterial);
		biomaterial = manager.Search_biomaterial_by_id(biomaterial_id);
		
	}
}
