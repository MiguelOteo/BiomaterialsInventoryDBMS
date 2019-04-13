package db.model;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import db.jdbc.SQLManager;
import db.pojos.Benefits;
import db.pojos.Biomaterial;
import db.pojos.Category;
import db.pojos.Client;
import db.pojos.Maintenance;
import db.pojos.Transaction;
import db.pojos.Utility;

public class UtilMethods {
	
	private static Integer difficulty = 1000;
	

	public UtilMethods() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// -----> SUM ALL GAINS METHOD <-----
	
	// Gets a list calling Search_stored_transactions and sum all the gains from them
	public int Sum_all_client_points(Client client) {
		SQLManager manager = new SQLManager();
		int points_gains = 0;
		List<Transaction> transactions_list = manager.Search_stored_transactions(client);
		
		for(Transaction transaction: transactions_list) { 
		     points_gains = points_gains + (int)(transaction.getGain()/difficulty);	
		}
        return points_gains;
	}
	
	// -----> CATEGORY LEVELS METHODS <-----

	public boolean Categories_of_cients(SQLManager manager){
		boolean None_ok = manager.Insert_new_category(new Category("None", 79, 0));
		boolean Bronze3_ok = manager.Insert_new_category(new Category("Bronze 3", 89, 80));
		boolean Bronze2_ok = manager.Insert_new_category(new Category("Bronze 2", 99, 90));
		boolean Bronze1_ok = manager.Insert_new_category(new Category("Bronze 1", 149, 100));
		boolean Silver2_ok = manager.Insert_new_category(new Category("Silver 2", 279, 150));
		boolean Silver1_ok = manager.Insert_new_category(new Category("Silver 1", 399, 280));
		boolean Gold2_ok = manager.Insert_new_category(new Category("Gold 2", 449, 400));
		boolean Gold1_ok = manager.Insert_new_category(new Category("Gold 1", 549, 450));
		boolean Diamond2_ok = manager.Insert_new_category(new Category("Diamond 2", 919, 820));
		boolean Diamond1_ok = manager.Insert_new_category(new Category("Diamond 1", 999, 920));
		boolean Platinum2_ok = manager.Insert_new_category(new Category("Platinum 2", 1299, 1000));
		boolean Platinum1_ok = manager.Insert_new_category(new Category("Platinum 1", 1599, 1300));
		
		if(None_ok| Bronze3_ok| Bronze2_ok| Bronze1_ok| Silver2_ok| Silver1_ok| Gold2_ok| Gold1_ok| Diamond2_ok| Diamond1_ok| Platinum2_ok| Platinum1_ok) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean Benefits_Of_Category(SQLManager manager){
		boolean None_ok = manager.Insert_new_benefits(new Benefits((float) 0, 0));
		boolean Bronze3_ok = manager.Insert_new_benefits(new Benefits((float) 0, 20));
		boolean Bronze2_ok = manager.Insert_new_benefits(new Benefits((float) 0, 40));
		boolean Bronze1_ok = manager.Insert_new_benefits(new Benefits((float) 0, 80));
		boolean Silver2_ok = manager.Insert_new_benefits(new Benefits((float) 0.025, 100));
		boolean Silver1_ok = manager.Insert_new_benefits(new Benefits((float) 0.05, 120));
		boolean Gold2_ok = manager.Insert_new_benefits(new Benefits((float) 0.1, 140));
		boolean Gold1_ok = manager.Insert_new_benefits(new Benefits((float) 0.15, 160));
		boolean Diamond2_ok = manager.Insert_new_benefits(new Benefits((float) 0.2, 200));
		boolean Diamond1_ok = manager.Insert_new_benefits(new Benefits((float) 0.2, 220));
		boolean Platinum2_ok = manager.Insert_new_benefits(new Benefits((float) 0.25, 250));
		boolean Platinum1_ok = manager.Insert_new_benefits(new Benefits((float) 0.3, 300));
	
		if(None_ok| Bronze3_ok| Bronze2_ok| Bronze1_ok| Silver2_ok| Silver1_ok| Gold2_ok| Gold1_ok| Diamond2_ok| Diamond1_ok| Platinum2_ok| Platinum1_ok) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean Assign_category_to_client(Client client) {
		SQLManager manager = new SQLManager();
		
		List<Category> categories_list = manager.List_all_categories();
		
		int sum_points = Sum_all_client_points(client) ;
		for(Category category : categories_list) {
			
			if(sum_points <= category.getMaximum() &&  sum_points >= category.getMinimum()) {
				client.setCategory(category);
			}
		}	
		boolean client_updated = manager.Update_client_info(client); 
		return client_updated;
	}
	
	public boolean Determine_limit_date (Client client) {
		//Transaction list
		ArrayList <Transaction> limiting_date_list = client.getTransactions_list();
		Transaction limiting_transaction = limiting_date_list.get(limiting_date_list.size()-1);
		//Dates	
		Date limiting_date = limiting_transaction.getTransaction_date();
		LocalDate current = LocalDate.now().minusMonths(3);
		Date current_date = Date.valueOf(current);
		
		if(limiting_date.before(current_date) == true) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void Set_penalization_to_client(Client client) {
		
		SQLManager manager = new SQLManager();
		int i = 0;
		
		if(Determine_limit_date(client)==true){
			
			client.getCategory().setPenalization(client.getCategory().getMinimum()/4);
			int lost_points = client.getCategory().getPenalization();	
		}
		
		if (client.getPoints() < client.getCategory().getMinimum()) {
			
			ArrayList<Category> cat_list = client.getCategory().getCategories_list();

			for (i=0; i<cat_list.size(); i++) {

				if (cat_list.get(i).equals(client.getCategory()) && !cat_list.get(0).equals(client.getCategory())) {
					break;
				}
			}
			client.setCategory(cat_list.get(i-1));
		}		
	}
	
	
	public static void Creation_one_biomaterial(SQLManager manager) {
		
		String withoutTime = "2030-01-10";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate exp_date = LocalDate.parse(withoutTime, formatter);
		
		manager.Insert_new_biomaterial(new Biomaterial(1, 1, "Polysulphur", (float)100, 1000, Date.valueOf(exp_date)));
		manager.Insert_new_maintenance(new Maintenance((float) 3.4, "Medium", "Not direct", 36, (float) 22, "Low", ""));
		manager.Insert_new_utility(new Utility("Hot", "None", "High", (float)1, (float)302));
		
		
	}
	
	
	/*MAIN DE PRUEBA DE METODOS
	 * 
	 * 
	 * - DA ERROR NULL EN INSERT_NEW_CLIENT() DE SQLManager*/
	
	public static void main (String args[]) throws NumberFormatException, IOException {
		
		
		SQLManager manager = new SQLManager();
		manager.Stablish_connection();
		
		if (manager.Check_if_tables_exist() ==false) {
			manager.Create_tables();
		} else {
		
		Creation_one_biomaterial(manager);
		
		//User u = new User();
		manager.Insert_new_user("ant03", "1234");
		
		manager.Insert_new_client(manager.Insert_new_user("ant03", "1234"));
		ArrayList<Transaction> lista = new ArrayList<Transaction>();
		Client c = new Client("Antonio", 0001, "", "");
		c.setTransactions_list(lista);
		manager.Update_client_info(c);
		
		
		System.out.println(c);
		
		manager.List_all_biomaterials();
		
		System.out.println("Elegir id producto: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int id = Integer.parseInt(br.readLine());
		
		
		
		Transaction t = new Transaction((float)1000, c.getClient_id(), 10, id);
		lista.add(t);
		manager.Close_connection();
				

	}
	
	}

}
