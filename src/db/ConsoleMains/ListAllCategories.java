package db.ConsoleMains;

import java.util.List;

import db.jdbc.SQLManager;
import db.pojos.Category;

public class ListAllCategories {
	
	public static void main(String args[]) {
		
		SQLManager manager = new SQLManager();
		boolean everything_ok = manager.Stablish_connection();

		boolean tables_exist = manager.Check_if_tables_exist();
		if(tables_exist == true) {
		} else {
			everything_ok = manager.Create_tables();
		}
		
		if(everything_ok) {
				
			System.out.println("-----------> CATEGORIES LIST <-----------");
			List<Category> categories_list = manager.List_all_categories();
			for (Category category : categories_list) {
				System.out.print(category + "\n\n");
	        }
		
		}
	}
}
