package db.ConsoleMains;

import java.util.List;

import db.jdbc.SQLManager;
import db.jpa.JPAManager;
import db.pojos.Benefits;
import db.pojos.Category;
import db.pojos.Client;

public class AddCategory {

	public static void main(String[] args) {
		
		JPAManager manager = new JPAManager();
		SQLManager manager2 = new SQLManager();
		manager.Stablish_connection();
		manager2.Stablish_connection();
		manager2.Create_tables();
		
		// "None", 500, 0
		// "Bronze III", 1000, 501
		// "Bronze II", 2500, 1001
		// "Bronze I", 5000, 2501
		// "Silver III", 8000, 5001
		
		// (float) 0, 0
		// (float) 10, 1
		// (float) 20, 2
		// (float) 30, 3
		// (float) 40, 4
		
		Category cat = new Category("Silver III", 8000, 5001);
		Benefits ben = new Benefits((float) 40, 4);
		cat.setBenefits(ben);
		
		manager.Insert_new_benefit(ben);
		manager.Insert_new_category(cat);
		
		List<Client> list = manager2.List_all_clients();
		for(Client client: list) {
			client.setCategory(cat);
			manager2.Update_client_info(client);
		}
	}	
}
