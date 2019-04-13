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

		for (Transaction transaction : transactions_list) {
			points_gains = points_gains + (int) (transaction.getGain() / difficulty);
		}
		return points_gains;
	}

	// -----> CATEGORY LEVELS METHODS <-----

	public void Categories_of_cients(SQLManager manager) {
		Integer None_ok = manager.Insert_new_category(new Category("None", 79, 0));
		Integer Bronze3_ok = manager.Insert_new_category(new Category("Bronze 3", 89, 80));
		Integer Bronze2_ok = manager.Insert_new_category(new Category("Bronze 2", 99, 90));
		Integer Bronze1_ok = manager.Insert_new_category(new Category("Bronze 1", 149, 100));
		Integer Silver2_ok = manager.Insert_new_category(new Category("Silver 2", 279, 150));
		Integer Silver1_ok = manager.Insert_new_category(new Category("Silver 1", 399, 280));
		Integer Gold2_ok = manager.Insert_new_category(new Category("Gold 2", 449, 400));
		Integer Gold1_ok = manager.Insert_new_category(new Category("Gold 1", 549, 450));
		Integer Diamond2_ok = manager.Insert_new_category(new Category("Diamond 2", 919, 820));
		Integer Diamond1_ok = manager.Insert_new_category(new Category("Diamond 1", 999, 920));
		Integer Platinum2_ok = manager.Insert_new_category(new Category("Platinum 2", 1299, 1000));
		Integer Platinum1_ok = manager.Insert_new_category(new Category("Platinum 1", 1599, 1300));
	}

	public void Benefits_Of_Category(SQLManager manager) {
		Integer None_ok = manager.Insert_new_benefits(new Benefits((float) 0, 0));
		Integer Bronze3_ok = manager.Insert_new_benefits(new Benefits((float) 0, 20));
		Integer Bronze2_ok = manager.Insert_new_benefits(new Benefits((float) 0, 40));
		Integer Bronze1_ok = manager.Insert_new_benefits(new Benefits((float) 0, 80));
		Integer Silver2_ok = manager.Insert_new_benefits(new Benefits((float) 0.025, 100));
		Integer Silver1_ok = manager.Insert_new_benefits(new Benefits((float) 0.05, 120));
		Integer Gold2_ok = manager.Insert_new_benefits(new Benefits((float) 0.1, 140));
		Integer Gold1_ok = manager.Insert_new_benefits(new Benefits((float) 0.15, 160));
		Integer Diamond2_ok = manager.Insert_new_benefits(new Benefits((float) 0.2, 200));
		Integer Diamond1_ok = manager.Insert_new_benefits(new Benefits((float) 0.2, 220));
		Integer Platinum2_ok = manager.Insert_new_benefits(new Benefits((float) 0.25, 250));
		Integer Platinum1_ok = manager.Insert_new_benefits(new Benefits((float) 0.3, 300));
	}

	public boolean Assign_category_to_client(Client client, SQLManager manager) {

		List<Category> categories_list = manager.List_all_categories();

		int sum_points = Sum_all_client_points(client);
		for (Category category : categories_list) {

			if (sum_points <= category.getMaximum() && sum_points >= category.getMinimum()) {
				client.setCategory(category);
			}
		}
		boolean client_updated = manager.Update_client_info(client);
		return client_updated;
	}

	public boolean Determine_limit_date(Client client) {
		// Transaction list
		List<Transaction> limiting_date_list = client.getTransactions_list();
		Transaction limiting_transaction = limiting_date_list.get(limiting_date_list.size() - 1);
		// Dates
		Date limiting_date = limiting_transaction.getTransaction_date();
		LocalDate current = LocalDate.now().minusMonths(3);
		Date current_date = Date.valueOf(current);

		if (limiting_date.before(current_date) == true) {
			return true;
		} else {
			return false;
		}
	}

	public void Set_penalization_to_client(Client client, SQLManager manager) {
		int i = 0;

		if (Determine_limit_date(client) == true) {

			client.getCategory().setPenalization(client.getCategory().getMinimum() / 4);
			int lost_points = client.getCategory().getPenalization();
		}

		if (client.getPoints() < client.getCategory().getMinimum()) {

			List<Category> cat_list = manager.List_all_categories();

			for (i = 0; i < cat_list.size(); i++) {

				if (cat_list.get(i).equals(client.getCategory()) && !cat_list.get(0).equals(client.getCategory())) {
					break;
				}
			}
			client.setCategory(cat_list.get(i - 1));
		}
	}

	public static void Creation_one_biomaterial(SQLManager manager) {

		String withoutTime = "2030-01-10";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate exp_date = LocalDate.parse(withoutTime, formatter);

		Maintenance maintenance = new Maintenance((float) 3.4, "Medium", "Not direct", 36, (float) 22, "Low", "");
		manager.Insert_new_maintenance(maintenance);
		Utility utility = new Utility("Hot", "None", "High", (float) 1, (float) 302);
		manager.Insert_new_utility(utility);
		manager.Insert_new_biomaterial(new Biomaterial(utility, maintenance, "Polysulphur", (float) 100, 1000, Date.valueOf(exp_date)));

	}

	/*
	 * MAIN DE PRUEBA DE METODOS
	 * 
	 * 
	 * - DA ERROR NULL EN INSERT_NEW_CLIENT() DE SQLManager
	 */

	public static void main(String args[]) throws NumberFormatException, IOException {

		SQLManager manager = new SQLManager();
		manager.Stablish_connection();

		if (manager.Check_if_tables_exist() == false) {
			manager.Create_tables();
		} else {

			Creation_one_biomaterial(manager);

			// User u = new User();
			manager.Insert_new_user("ant03", "1234");

			manager.Insert_new_client(manager.Insert_new_user("ant03", "1234"));
			List<Transaction> lista = new ArrayList<Transaction>();
			Client c = new Client("Antonio", 0001, "", "");
			c.setTransactions_list(lista);
			manager.Update_client_info(c);

			System.out.println(c);

			manager.List_all_biomaterials();

			System.out.println("Elegir id producto: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Client client = manager.Search_client_by_id(Integer.parseInt(br.readLine()));
			//Transaction t = new Transaction((float) 1000, 10, client);
			//lista.add(t);
			manager.Close_connection();

		}
	}
}
