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
			
			System.out.print("\nName of client: ");
			String name = console.readLine();
			System.out.print("\nName of responsible: ");
			String responsible = console.readLine();
			System.out.print("\nTelephone: ");
			Integer telef = Integer.parseInt(console.readLine());
			System.out.print("\nBank account: ");
			String bank_account = console.readLine();

			Client client = new Client(name, telef, bank_account, responsible);
			
			boolean insert_ok = manager.Inset_new_client(client);
			if(insert_ok == true) {
				System.out.println("\nInsertion done");
			} else {
				System.out.println("Insertion error accured");
			}
			
			boolean close_ok = manager.Close_connection();
			if(close_ok == true) {
				System.out.println("Program closed successfuly");
			} else {
				System.out.println("An error has occured while closing the program");
			}
		} else {
			System.out.println("Connection and tables charge failed");
		}
	}
}
