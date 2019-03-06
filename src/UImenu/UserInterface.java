package UImenu;

import java.io.*;
import java.sql.Date;
import java.util.List;

import db.jdbc.SQLManager;
import db.model.UtilMethods;
import db.pojos.Client;
import db.pojos.Transaction;

public class UserInterface {

	public static void main(String[] args) throws IOException {

		try {
			BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
			SQLManager manager = new SQLManager();
			UtilMethods methods = new UtilMethods();
			boolean everything_ok = manager.Stablish_connection();

			boolean tables_exist = manager.Check_if_tables_exist();
			if(tables_exist == true) {
				System.out.print("\n\nTables are already charged");
			} else {
				System.out.print("\n\nCharging tables");
				everything_ok = manager.Create_tables();
			}
			
			if (everything_ok == true) {
				System.out.println("\n\nConnection and tables charged");
				while (true) {
					System.out.print("\n\nSelection: ");
					char selection = '0';
					selection = console.readLine().charAt(0);
					switch (selection) {
					case '1': {
						System.out.println("Inset client option\n\n");

						System.out.print("\nName of client: ");
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
							System.out.println("\nInsertion error occurred");
						}
						break;
					}
					case '2': {
						System.out.print("Search clients option\n\n");

						System.out.print("\nSelect the client you wanna search: ");
						String client_name = console.readLine();
						System.out.print("\n");
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
					case '4':{
						System.out.print("List transactions option\n\n");
						List<Transaction> transaction_list =manager.List_all_transactions();
						for (Transaction object: transaction_list) {
							System.out.print(object+"\n\n");
						}
				        break;
					}
					case '5': {
						System.out.print("Delete client option\n\n");

						System.out.print("\nSelect the client you wanna delete: ");
						Integer client_id = Integer.parseInt(console.readLine());
						boolean delete_ok = manager.Delete_stored_client(client_id);

						if (delete_ok == true) {
							System.out.print("Client deleted");
						} else {
							System.out.print("Something went wrong");
						}
						break;
					}
					case '6': {
						System.out.print("Insert transaction option\n\n");
						List<Client> clients_list = manager.List_all_clients();
						for (Client object : clients_list) {
							System.out.print(object + "\n\n");
						}
						
						System.out.print("\nSelect one client base on his Client ID: ");
						Integer client_id = Integer.parseInt(console.readLine());
						System.out.print("\nTransaction Nº: ");
						Integer transaction_id = Integer.parseInt(console.readLine());
						System.out.print("\nTransaction gain: ");
						Float gain = Float.parseFloat(console.readLine());
						System.out.print("\nUnits: ");
						Integer units = Integer.parseInt(console.readLine());
						System.out.print("\nProduct id: ");
						Integer product_id = Integer.parseInt(console.readLine()); 
						System.out.print("\nExpiration Date (yyyy-mm-dd): ");
						String date = console.readLine();
						Date transaction_date = Date.valueOf(date);
						
						Transaction transaction = new Transaction(transaction_id, gain, client_id, units, product_id, transaction_date);
						boolean insert_ok = manager.Insert_new_transaction(transaction);
						if (insert_ok == true) {
							System.out.println("\nInsertion done");
						} else {
							System.out.println("\nInsertion error occurred");
						}
						break;
					}
					case '7': {
						System.out.println("\nAtribute list option");
						System.out.println("\nSelect the atribute to list");
						String selections= console.readLine();
						List<Client> clients_list = manager.List_all_clients();
						for (Client object : clients_list) {
							if(selections.equals("name")) {
							String atributes=object.getName();
							System.out.println("\n"+atributes);
						   }
							if(selections.equals("telephone")) {
								Integer atributes=object.getTelephone();
								System.out.println("\n"+atributes);
							   }
							if(selections.equals("bank_account")) {
								String atributes=object.getBank_account();
								System.out.println("\n"+atributes);
							   }
							if(selections.equals("responsible")) {
								String atributes=object.getResponsible();
								System.out.println("\n"+atributes);
							   }
						}
						break;
					}
					case '8': {
						System.out.println("\nInset new biomaterial");
						
					}
					case '9': {
						
						System.out.println("\nGains of transactions");
						System.out.print("\nClient ID: ");
						int client_id = Integer.parseInt(console.readLine());
						List<Client> clients_list = manager.List_all_clients();
						Client selected_client = null;
						for(Client client: clients_list) {
							if(client.getClient_id() == client_id) {
								selected_client = client;
								break;
							}
						}
						Integer gains = methods.Sum_all_client_points(manager, selected_client);
						System.out.println("Gains:" + gains);
						break;
					}
					default: {
						boolean close_ok = manager.Close_connection();
						if (close_ok == true) {
							System.out.println("\n\nProgram closed successfuly");
						} else {
							System.out.println("\n\nAn error has occurred while closing the program");
						}
						System.exit(0);
					}
					}
				}
			} else {
				System.out.println("\n\nConnection and tables charge failed");
				manager.Close_connection();
			}
		} catch (IOException | NumberFormatException critical_error) {
			critical_error.printStackTrace();
			System.exit(0);
		}
	}
}
