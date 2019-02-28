package UImenu;

import java.io.*;
import java.util.List;

import db.jdbc.SQLManager;
import db.pojos.Client;

public class UserInterface {

	public static void main(String[] args) throws IOException {
		
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		SQLManager manager = new SQLManager();
		boolean everything_ok = manager.Create_tables();
		
		if(everything_ok == true) {
			System.out.println("Connection and tables charged");
			
			System.out.print("\nName of client 1: ");
			String name = console.readLine();
			System.out.print("\nName of responsible: ");
			String responsible = console.readLine();
			System.out.print("\nTelephone: ");
			Integer telephone = Integer.parseInt(console.readLine());
			System.out.print("\nBank account: ");
			String bank_account = console.readLine();

			Client client_1 = new Client(name, telephone, bank_account, responsible);
			
			System.out.print("\n\nName of client 2: ");
			name = console.readLine();
			System.out.print("\nName of responsible: ");
			responsible = console.readLine();
			System.out.print("\nTelephone: ");
			telephone = Integer.parseInt(console.readLine());
			System.out.print("\nBank account: ");
			bank_account = console.readLine();
			
			Client client_2 = new Client(name, telephone, bank_account, responsible);
			
			boolean insert_ok = manager.Inset_new_client(client_1);
			insert_ok = manager.Inset_new_client(client_2);
			if(insert_ok == true) {
				System.out.println("\nInsertion done");
			} else {
				System.out.println("\nInsertion error accured");
			}
			
			System.out.print("\nSelect the client you wanna search: ");
			String client_name = console.readLine();
			List<Client> clients_list = manager.Search_stored_clients(client_name);
			for(Client object: clients_list) {
				System.out.print(object + "\n\n");
			}
			
			boolean close_ok = manager.Close_connection();
			if(close_ok == true) {
				System.out.println("\n\nProgram closed successfuly");
			} else {
				System.out.println("\n\nAn error has occured while closing the program");
			}
		} else {
			System.out.println("\n\nConnection and tables charge failed");
		}
		manager.Close_connection();
		System.exit(0);
	}
}
