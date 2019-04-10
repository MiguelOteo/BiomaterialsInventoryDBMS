package db.model;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.time.LocalDate;

import db.jdbc.SQLManager;
import db.pojos.Benefits;
import db.pojos.Category;
import db.pojos.Client;
import db.pojos.Transaction;

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
			float lost_points = client.getCategory().getPenalization();	
		} //falta entender como funcionan las penalizaciones
		
		if (client.getPoints() < client.getCategory().getMinimum()) {
			
			ArrayList<Category> cat_list = client.getCategory().getCategories_list();

			for (i=0; i<cat_list.size(); i++) {

				if (cat_list.get(i) == client.getCategory() && cat_list.get(0) != client.getCategory()) {
					break;
				}
			}
			client.setCategory(cat_list.get(i-1));
		}		
	}

}
