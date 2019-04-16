package db.ConsoleMains;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import db.jdbc.SQLManager;
import db.pojos.Client;
import db.pojos.Transaction;

public class DeleteTransaction  {

	
	public static void main (String args[]) throws IOException{
	SQLManager manager = new SQLManager();
	boolean everything_ok = manager.Stablish_connection();
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	boolean tables_exist = manager.Check_if_tables_exist();
	if(tables_exist == true) {
	} else {
		everything_ok = manager.Create_tables();
	}
	
	if (everything_ok) {
		
		System.out.println("\n-----> CLIENT LIST <-----\n");
		List<Client> clients_list = manager.List_all_clients();
		for (Client client : clients_list) {
			System.out.print(client + "\n\n");
        }
		System.out.println("SELECT CLIENT ID: ");
		Integer client_id = Integer.parseInt(br.readLine());
		Client client = manager.Search_client_by_id(client_id);
		
		System.out.println("-----------> TRANSACTION LIST OF CLIENT " + client_id +" <-----------");
		List<Transaction> transactions_list = manager.Search_stored_transactions(client);
		for (Transaction transaction : transactions_list) {
			System.out.print(transaction + "\n\n");
        }
		
		
		System.out.println("Delete a transaction. ID: ");
		Integer transaction_id = Integer.parseInt(br.readLine());
		Transaction transaction = manager.Search_transaction_by_id(transaction_id);
		manager.Delete_transaction_from_client(transaction);
		
		if (manager.Delete_transaction_from_client(transaction) == true) {
			System.out.println("Transaction deleted.");
		}
	
		
		
		System.out.println("-----------> TRANSACTION LIST OF CLIENT " + client_id +" <-----------");
		transactions_list = manager.Search_stored_transactions(client);
		for (Transaction t : transactions_list) {
			System.out.print(t + "\n\n");
        }
		
		
	}
}
}
