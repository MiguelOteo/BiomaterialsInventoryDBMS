package UImenu;

import java.io.*;
import java.util.List;

import db.jdbc.SQLManager;
import db.pojos.Client;

public class UserInterface {

	public static void main(String[] args) throws IOException {

		try {
			BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
			SQLManager manager = new SQLManager();
			boolean everything_ok = manager.Create_tables();

			if (everything_ok == true) {
				System.out.println("Connection and tables charged");
				while (true) {
					System.out.print("\n\nSelection: ");
					char selection = '0';
					selection = console.readLine().charAt(0);
					switch (selection) {
					case '1': {
						System.out.println("Inset client option\n\n");

						System.out.print("\nName of client 1: ");
						String name = console.readLine();
						System.out.print("\nName of responsible: ");
						String responsible = console.readLine();
						System.out.print("\nTelephone: ");
						Integer telephone = Integer.parseInt(console.readLine());
						System.out.print("\nBank account: ");
						String bank_account = console.readLine();

						Client client = new Client(name, telephone, bank_account, responsible);
						boolean insert_ok = manager.Inset_new_client(client);

						if (insert_ok == true) {
							System.out.println("\nInsertion done");
						} else {
							System.out.println("\nInsertion error accured");
						}
						break;
					}
					case '2': {
						System.out.print("Search clients option\n\n");

						System.out.print("\nSelect the client you wanna search: ");
						String client_name = console.readLine();
						List<Client> clients_list = manager.Search_stored_clients(client_name);
						for (Client object : clients_list) {
							System.out.print(object + "\n\n");
						}
						break;
					}
					case '3': {
						System.out.print("List clients option\n\n");

						List<Client> clients_list = manager.List_all_clients();
						for (Client object : clients_list) {
							System.out.print(object + "\n\n");
						}
						break;
					}
					case '4': {
						System.out.print("Nuke all clients option\n\n");
						
						System.out.print("\nSelect the client you wanna delete: ");
						Integer client_id = Integer.parseInt(console.readLine());
						boolean delete_ok = manager.Delete_stored_client(client_id);
						if(delete_ok == true) {
							System.out.print("Client deleted");
						} else {
							System.out.print("Something went wrong");
						}
						break;
					}
					case '5': {
						boolean close_ok = manager.Close_connection();
						if (close_ok == true) {
							System.out.println("\n\nProgram closed successfuly");
						} else {
							System.out.println("\n\nAn error has occured while closing the program");
						}
						System.exit(0);
					}
					}
				}
			} else {
				System.out.println("\n\nConnection and tables charge failed");
			}
		} catch (IOException critical_error) {
			critical_error.printStackTrace();
			System.exit(0);
		}
	}
}
