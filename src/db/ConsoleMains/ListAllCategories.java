package db.ConsoleMains;

import java.util.List;

import db.jpa.JPAManager;
import db.pojos.Benefits;
import db.pojos.Category;

public class ListAllCategories {
	
	public static void main(String args[]) {
		
		JPAManager manager = new JPAManager();
		manager.Stablish_connection();
			
		System.out.println("-----------> CATEGORIES LIST <-----------");
		List<Category> categories_list = manager.List_all_categories();
		for (Category category : categories_list) {
			System.out.print(category + "\n\n");
	    }
		
		/*for(Category category: categories_list) {
			manager.Delete_stored_category(category);
		}*/
		
		
		List<Benefits> benefits_list = manager.List_all_benefits();
		for(Benefits b: benefits_list) {
			System.out.println(b);
		}
	} 
}
