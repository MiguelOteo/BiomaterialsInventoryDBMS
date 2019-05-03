package db.ConsoleMains;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import db.jdbc.SQLManager;
import db.pojos.Biomaterial;

public class DeleteBiomaterial {

public static void main(String[] args) throws NumberFormatException, IOException {
		
		SQLManager manager = new SQLManager();
		boolean everything_ok = manager.Stablish_connection();

		boolean tables_exist = manager.Check_if_tables_exist();
		if(tables_exist == true) {
		} else {
			everything_ok = manager.Create_tables();
		}
		
		
		System.out.println("-----------> BIOMATERIALS LIST <-----------");
		List<Biomaterial> biomaterials_list = manager.List_all_biomaterials();
		for (Biomaterial biomaterial : biomaterials_list) {
			System.out.print(biomaterial + "\n\n");
        }
		
		System.out.println("ID to delete: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer biomaterial_id = Integer.parseInt(br.readLine()); 
		System.out.println(manager.Delete_biomaterial_by_id(biomaterial_id));
		
	}
	
	
}
