package db.ConsoleMains;

import db.jdbc.SQLManager;
import db.jpa.JPAManager;
import db.pojos.Benefits;
import db.pojos.Category;
import db.pojos.Client;
import db.pojos.User;

public class JPAConsole {

	public static void main (String args[]) {
		
		SQLManager sql = new SQLManager();
		JPAManager jpa = new JPAManager();
		boolean everything_ok = sql.Stablish_connection();
		
		if(everything_ok) {
			
		//user is jdbc type
		User u = new User("Username", "x");
		User user = sql.Insert_new_user(u.getUserName(), u.getPassword());
		System.out.println(user);
		
		boolean ok = jpa.Stablish_connection();
		
			if (ok == true) {
				
			
				//add a benefit
				/*ERROR == The sequence table information is not complete.*/
				Benefits benefit = new Benefits((float)0.03, 60);
				jpa.Insert_new_benefit(benefit);
				
				//add a category
				
				/*ERROR == The sequence table information is not complete.*/
				Category category = new Category("prueba", 100, 0);
				jpa.Insert_new_category(category);
					category.setCategory_name("updated Category");
				jpa.Update_category_info(category);
				System.out.println(jpa.Search_category_info(category));
				
				
				//add a client with his category and all his methods available in JPA
				jpa.List_all_clients();
				
				Client client = new Client("pepe", 123, "bank", "x");
				jpa.Insert_new_client(user);
					client.setCategory(category);
					client.setEmail("x@hotmail.com");
				jpa.Update_client_info(client);
				
				System.out.println("Searched client by its id :  " + jpa.Search_client_by_id(client.getClient_id()));
				System.out.println("Searched client by its user :  " + jpa.Search_stored_client(user));
				
				//we delete the used category to show the functionality
				jpa.Delete_stored_category(category);
				sql.Delete_stored_user(user.getUserId());
				
				
			}
		}
		
		jpa.Close_connection();
		sql.Close_connection();
	}
	
	
}
