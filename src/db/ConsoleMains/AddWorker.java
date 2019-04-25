package db.ConsoleMains;

import db.pojos.User;
import db.jdbc.SQLManager;

public class AddWorker {

	
	public static void main (String args[]) {

			SQLManager manager = new SQLManager();
			boolean everything_ok = manager.Stablish_connection();

			boolean tables_exist = manager.Check_if_tables_exist();
			if(tables_exist == true) {
			} else {
				everything_ok = manager.Create_tables();
			}
			
			if(everything_ok) {
				//manager.Delete_stored_worker(1);
				User u = manager.Insert_new_user("noa", "1234");
				manager.Insert_new_worker(u);
			}
		
	}
}
