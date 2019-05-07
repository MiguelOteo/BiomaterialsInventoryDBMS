package db.ConsoleMains;

import db.jdbc.SQLManager;
import db.jpa.JPAManager;
import db.pojos.Category;

public class JPAConsole {

	private static JPAManager jpa;
	
	public static void main (String args[]) {
		
		SQLManager sql = new SQLManager();
		boolean everything_ok = sql.Stablish_connection();

		boolean tables_exist = sql.Check_if_tables_exist();
		if(tables_exist == true) {
		} else {
			everything_ok = sql.Create_tables();
		}
		
		jpa = new JPAManager();
		
		//add a category
		Category category = new Category("prueba", 100, 0);
		jpa.Insert_new_category(category);
		System.out.println(category);
		
	}
	
	
}
