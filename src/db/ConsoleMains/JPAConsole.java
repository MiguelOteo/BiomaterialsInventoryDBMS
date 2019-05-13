package db.ConsoleMains;

import javax.persistence.Persistence;

import db.jdbc.SQLManager;
import db.jpa.JPAManager;
import db.pojos.Category;
import db.pojos.Client;
import db.pojos.User;

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
		
		
		if(everything_ok) {
			
		//user is jdbc type
		/*User u = new User("Username", "x");
		User user = sql.Insert_new_user(u.getUserName(), u.getPassword());*/
			User user = sql.Search_stored_user("Username", "x");
		System.out.println(user);
		
		jpa = new JPAManager();
		boolean ok = jpa.Stablish_connection();
		
		Client client = new Client("pepe", 123, "bank", "x");
		jpa.Insert_new_client(user);
		//jpa.Update_client_info(client);
		System.out.println(jpa.Search_stored_client(user));
		
		/*
		//create a client
			Client client = new Client(user.getUserName(), user);
			//Client update_client = new Client("Santiago", 1253644, "bank account", "responsible" );
		jpa.Insert_new_client(user);
		
		//jpa.Update_client_info(client);
		
		//System.out.println(jpa.Search_stored_client(user));
		*/
		
		
		/*
		//add a category
		Category category = new Category("prueba", 100, 0);
		jpa.Insert_new_category(category);
		System.out.println(category);
		*/
		
		}
	}
	
	
}
