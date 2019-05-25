package db.ConsoleMains;

import db.jpa.JPAManager;
import db.pojos.Benefits;
import db.pojos.Category;

public class JPAConsole {

	public static void main (String args[]) {
		
		JPAManager jpa = new JPAManager();
		 
		
		
		boolean ok = jpa.Stablish_connection();
		
			if (ok == true) {
				//add a benefit
				Benefits benefit = new Benefits((float)0.03, 60);
				jpa.Insert_new_benefit(benefit);
				
				//add a category
				
				Category category = new Category("prueba", 100, 20);
				jpa.Insert_new_category(category);
					category.setCategory_name("updated Category");
				jpa.Update_category_info(category);
				System.out.println(jpa.Search_category_by_id(category.getCategory_id()));
				
				//we delete the used category to show the functionality
				jpa.Delete_stored_category(category);
				
				
			
		}
		
		jpa.Close_connection();
	}
	
	
}
