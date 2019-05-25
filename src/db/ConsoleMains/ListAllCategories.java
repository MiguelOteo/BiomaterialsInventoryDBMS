package db.ConsoleMains;

import java.util.List;

import db.jdbc.SQLManager;
import db.jpa.JPAManager;
import db.pojos.Category;
import db.pojos.Client;

public class ListAllCategories {
	
	public static void main(String args[]) {
		
		JPAManager manager = new JPAManager();
		SQLManager manager2 = new SQLManager();
		boolean everything_ok = manager.Stablish_connection();
		manager2.Stablish_connection();
		
		if(everything_ok) {
				
			System.out.println("-----------> CATEGORIES LIST <-----------");
			List<Category> categories_list = manager.List_all_categories();
			for (Category category : categories_list) {
				System.out.print(category + "\n\n");
	        }
		
			manager.Close_connection();
		}
		
		List<Category> categories_list  = manager2.List_all_categories();
		for (Category category : categories_list) {
			System.out.print(category + "\n\n");
        }
		
		Client category = manager2.Search_client_by_id(2);
		System.out.println(category);
	} 
}
