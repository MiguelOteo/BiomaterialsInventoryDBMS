package UImenu;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import pojos.Client;

public class UserInterface {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    SQLManager manager;
	
	manager = new SQLManager();
	System.out.println("id of client: ");
	Integer id = br.read();
	System.out.println("Name of client: ");
	String name = br.readLine();
	System.out.println("name of responsible: ");
	String responsible = br.readLine();
	System.out.println("Telephone: ");
	Integer telef = br.read();
	System.out.println("Bank account: ");
	String bank_account = br.readLine();
	
	Client client = new Client(id, name, telef, bank_account,  responsible);
	manager.insert(client);


}
