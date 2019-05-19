package db.ConsoleMains;

import java.util.List;

import db.jpa.JPAManager;
import db.pojos.Category;

public class ListAllCategories {
	
	public static void main(String args[]) {
		
		JPAManager manager = new JPAManager();
		boolean everything_ok = manager.Stablish_connection();
		
		if(everything_ok) {
				
			System.out.println("-----------> CATEGORIES LIST <-----------");
			List<Category> categories_list = manager.List_all_categories();
			for (Category category : categories_list) {
				System.out.print(category + "\n\n");
	        }
		
			manager.Close_connection();
		}
	} 
}
