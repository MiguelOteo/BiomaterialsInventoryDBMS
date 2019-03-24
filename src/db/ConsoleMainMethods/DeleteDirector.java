package db.ConsoleMainMethods;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import db.jdbc.SQLManager;
import db.model.UtilMethods;
import db.pojos.Director;

public class DeleteDirector {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		SQLManager manager = new SQLManager();
		UtilMethods methods = new UtilMethods();
		boolean everything_ok = manager.Stablish_connection();

		boolean tables_exist = manager.Check_if_tables_exist();
		if (tables_exist == true) {
		} else {
			everything_ok = manager.Create_tables();
		}

		if (everything_ok) {
			System.out.println("\n-----> DIRECTOR LIST <-----\n");
			List<Director> directors_list = manager.List_all_directors();
			for (Director director : directors_list) {
				System.out.print(director + "\n\n");
			}
			System.out.print("Write 0 to cancel\n");
			System.out.print("Director id: ");
			Integer id = Integer.parseInt(console.readLine());
			boolean delete_ok = manager.Delete_stored_director(id);
			
			if(delete_ok) {
				System.out.println("\nDirector deleted");
			} else {
				System.out.println("\nSomething went wrong");
			}
			
			System.out.println("\n-----> NEW DIRECTOR LIST <-----\n");
			directors_list = manager.List_all_directors();
			for (Director director : directors_list) {
				System.out.print(director + "\n\n");
			}
		}
	}
}
