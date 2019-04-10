package db.model;

import java.util.List;

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
	
	public /*boolean*/ void Determine_limiting_date (Client client) {
		//1 >> we have to get last transaction stored in arrayList<Transaction> with FOR
		
		//2>> Access to date of the last transaction with getTransaction_date()
		
		//3>> Compare with if() month with 3 months of difference
		
	}
	
	
	public void Set_penalization_to_client(Client client) {
		SQLManager manager = new SQLManager();
		 
		/*check if the client can be or not penalized calling method Determine_limiting_date()
			use if(boolean)
		
		/*if true, client.setpenalization(client.getMinimum()/4);
			lost_points = client.getPenalization()	
			category	
		
		if (client.getPoints() < category.getMinimum()) CHANGE category from ArrayList<Category> cat_list with a for-each
		then client.setCategory(cat_list[i])
		
		else nothing
		
		
		
		
		*/
		
	}
	
}






