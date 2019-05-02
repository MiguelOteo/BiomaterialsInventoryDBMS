package db.model;

import java.util.List;

import db.pojos.*;

public interface Interface {
	
	public boolean Stablish_connection();
	
	public boolean Create_tables();
	public boolean Check_if_tables_exist();
	
	public User Insert_new_user(String user_name, String password);
	public Director Insert_new_director(User user);
	public Worker Insert_new_worker(User user);
	public Client Insert_new_client(User user);
	public Integer Insert_new_benefits(Benefits benefits);
	public Integer Insert_new_utility(Utility utility);
	public Integer Insert_new_maintenance(Maintenance maintenance);
	public Integer Insert_new_transaction(Transaction transaction);
	public Integer Insert_new_biomaterial(Biomaterial biomaterial);
	public Integer Insert_new_category(Category category);
	
	public void Change_password(String password, Integer user_id);
	public boolean Update_director_info(Director director);
	public boolean Update_client_info(Client client);
	public boolean Update_category_info(Category category);
	public boolean Update_worker_info(Worker worker);
	public boolean Update_biomaterial_features(Biomaterial biomaterial);
	
	public User Search_stored_user(String name, String password);
	public Director Search_stored_director(User user);
	public Worker Search_stored_worker(User user);
	public Client Search_stored_client(User user);
	public Biomaterial Search_stored_biomaterial(Biomaterial biomaterial);
	public User Search_user_by_id(Integer user_id);
	public Client Search_client_by_id (Integer client_id);
	public Worker Search_worker_by_id (Integer worker_id);
	public Director Search_director_by_id(Integer director_id);
	public List<Transaction> Search_stored_transactions(Client client);
	public Transaction Search_transaction_by_id(Integer transaction_id);
	public Biomaterial Search_biomaterial_by_id (Integer biomaterial_id);
	public Maintenance Search_maintenance_by_id (Integer maintenance_id) ;
	public Utility Search_utility_by_id (Integer utility_id);
	
	public List<User> List_all_users();
	public List<Client> List_all_clients();
	public List<Transaction> List_all_transactions();
	public List<Category> List_all_categories();
	public List<Biomaterial> List_all_biomaterials();
	public List<Director> List_all_directors();
	public List<Worker> List_all_workers();
	public List<Utility> List_all_utilities();
	public List<Maintenance> List_all_maintenances();
	
	public boolean Delete_stored_user(Integer user_id);
	public boolean Delete_stored_category(Category category);
	public boolean Delete_transaction_from_client(Transaction transaction);
	public boolean Delete_biomaterial_by_id(Integer biomat_id);
	public boolean Close_connection();
	 
}
