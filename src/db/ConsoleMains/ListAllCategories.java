package db.ConsoleMains;

import java.util.List;

import db.jdbc.SQLManager;
import db.jpa.JPAManager;
import db.pojos.Benefits;
import db.pojos.Category;

public class ListAllCategories {
	
	public static void main(String args[]) {
		
		JPAManager manager = new JPAManager();
		SQLManager manager2 = new SQLManager();
		boolean everything_ok = manager.Stablish_connection();
		manager2.Stablish_connection();
			
		System.out.println("-----------> CATEGORIES LIST <-----------");
		List<Category> categories_list = manager.List_all_categories();
		for (Category category : categories_list) {
			System.out.print(category + "\n\n");
	    }
		
		List<Category> categories_list2  = manager2.List_all_categories();
		for (Category category : categories_list2) {
			System.out.print(category + "\n\n");
        }
		
		List<Benefits> benefits_list = manager.List_all_benefits();
		for(Benefits b: benefits_list) {
			System.out.println(b);
		}
	} 
}
