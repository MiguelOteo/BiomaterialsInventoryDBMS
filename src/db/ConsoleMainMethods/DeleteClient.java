package db.ConsoleMainMethods;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import db.jdbc.SQLManager;
import db.model.UtilMethods;
import db.pojos.Client;

public class DeleteClient {
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
			System.out.println("\n-----> CLIENT LIST <-----\n");
			List<Client> clients_list = manager.List_all_clients();
			for (Client client : clients_list) {
				System.out.print(client + "\n\n");
			}
			System.out.print("Write 0 to cancel\n");
			System.out.print("Client id: ");
			Integer id = Integer.parseInt(console.readLine());
			boolean delete_ok = manager.Delete_stored_client(id);
			
			if(delete_ok) {
				System.out.println("\nClient deleted");
			} else {
				System.out.println("\nSomething went wrong");
			}
			
			System.out.println("\n-----> NEW CLIENT LIST <-----\n");
			clients_list = manager.List_all_clients();
			for (Client client : clients_list) {
				System.out.print(client + "\n\n");
			}
		}
	}
}
