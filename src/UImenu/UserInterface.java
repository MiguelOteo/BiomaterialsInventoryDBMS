package UImenu;

import java.io.*;
import db.jdbc.SQLManager;
import db.pojos.Client;

public class UserInterface {

	public static void main(String[] args) throws IOException {

		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		SQLManager manager = new SQLManager();
		boolean everything_ok = manager.Create_tables();
		
		if(everything_ok == true) {
			System.out.println("Connection and tables charged");
			
			System.out.println("Name of client: ");
			String name = console.readLine();
			System.out.println("Name of responsible: ");
			String responsible = console.readLine();
			System.out.println("Telephone: ");
			Integer telef = Integer.parseInt(console.readLine());
			System.out.println("Bank account: ");
			String bank_account = console.readLine();

			Client client = new Client(name, telef, bank_account, responsible);
			boolean insert_ok = manager.Inset_new_client(client);
			
			if(insert_ok == true) {
				System.out.println("Insertion done");
			} else {
				System.out.println("Insertion error accured");
			}
			manager.Close_connection();
		} else {
			System.out.println("Connection and tables charge failed");
		}
	}
}
