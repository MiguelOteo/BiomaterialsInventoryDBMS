package db.model;

import java.util.List;

import db.jdbc.SQLManager;
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
	public int Sum_all_client_points(SQLManager manager, Client client) {	
		int points_gains = 0;
		List<Transaction> transactions_list = manager.Search_stored_transactions(client);
		for(Transaction transaction: transactions_list) { 
		     points_gains = points_gains + (int)(transaction.getGain()/difficulty);	
		}
        return points_gains;
	}
	
	// -----> CATEGORY LEVELS METHODS <-----

	public boolean Categories_of_cients(SQLManager manager){
		boolean None_ok = manager.Insert_new_category(new Category("None", 0, 799, 0));
		boolean Bronze3_ok = manager.Insert_new_category(new Category("Bronze 3", 80/4, 89, 80));
		boolean Bronze2_ok = manager.Insert_new_category(new Category("Bronze 2", 90/4, 99, 90));
		boolean Bronze1_ok = manager.Insert_new_category(new Category("Bronze 1", 100/4, 149, 100));
		boolean Silver2_ok = manager.Insert_new_category(new Category("Silver 2", 150/4, 279, 150));
		boolean Silver1_ok = manager.Insert_new_category(new Category("Silver 1", 280/4, 399, 280));
		boolean Gold2_ok = manager.Insert_new_category(new Category("Gold 2", 400/4, 449, 400));
		boolean Gold1_ok = manager.Insert_new_category(new Category("Gold 1", 450/4, 549, 450));
		boolean Diamond2_ok = manager.Insert_new_category(new Category("Diamond 2", 820/4, 919, 820));
		boolean Diamond1_ok = manager.Insert_new_category(new Category("Diamond 1", 920/4, 1000, 920));
		boolean Platinum2_ok = manager.Insert_new_category(new Category("Platinum 2", 550/4, 649, 550));
		boolean Platinum1_ok = manager.Insert_new_category(new Category("Platinum 1", 650/4, 819, 650));
		
		if(None_ok| Bronze3_ok| Bronze2_ok| Bronze1_ok| Silver2_ok| Silver1_ok| Gold2_ok| Gold1_ok| Diamond2_ok| Diamond1_ok| Platinum2_ok| Platinum1_ok) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean Assign_category_to_client(SQLManager manager, Client client) {
		List<Category> categories_list = manager.List_all_categories();
		int sum_points = Sum_all_client_points(manager, client) ;
		for(Category category : categories_list) {
			
			if(sum_points <= category.getMaximum() &&  sum_points >= category.getMinimum()) {
				client.setCategory(category.getCategory_name());
			}
		}	
		boolean client_updated = manager.Update_client_info(client); 
		return client_updated;
	}
}






