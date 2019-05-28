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
		
		Category cat = new Category("None", 250, 0);
		Benefits ben = new Benefits((float) 0.0, 0);
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
