package db.jpa;

import java.util.List;

import db.model.Interface;
import db.pojos.*;

import javax.persistence.*;

public class JPAManager implements Interface {

	private static final String PERSISTENCE_PROVIDER = "project-provider";
	private static EntityManager em;
	
	/*  ---------------   CREATE METHODS JPA   ------------------*/
	
	
	public Integer Insert_new_category(Category category) {
		try{
			Category cat = new Category();
			
			em.getTransaction().begin();
			
			cat.setCategory_name(category.getCategory_name());
			cat.setMaximum(category.getMaximum());
			cat.setMinimum(category.getMinimum());
			cat.setPenalization(category.getMinimum()/4);
			
			em.persist(category);
			em.getTransaction().commit();
			em.close();
						
			return cat.getCategory_id();
		} catch(EntityNotFoundException new_category_error) {
			new_category_error.printStackTrace();
			return null;
		}
	}
	
	
	
	public Client Insert_new_client(User user) {
		try {
			em.getTransaction().begin();
			
			Client client = new Client();
				client.setUser(user);
				client.setName(user.getUserName());
			// Insert client into the DB
			em.persist(client);
			em.getTransaction().commit();

			
			em.close();
			return client;
			
		} catch (EntityNotFoundException new_client_account_error) {
			new_client_account_error.printStackTrace();
			return null;
		}
	}
	
	
	public Integer Insert_new_benefits(Benefits benefits) {
		try{
			Benefits benefit = new Benefits();
			
			em.getTransaction().begin();
			
			benefit.setExtra_units(benefits.getExtra_units());
			benefit.setPercentage(benefits.getPercentage());
			
			em.persist(benefits);
			em.getTransaction().commit();
			em.close();
						
			return benefit.getBenefits_id();
		} catch(EntityNotFoundException new_category_error) {
			new_category_error.printStackTrace();
			return null;
		}
	}
	
	public User Insert_new_user(String user_name, String password) {
		System.out.println("Not implemented");
		return null;
	}
	public Director Insert_new_director(User user){
		System.out.println("Not implemented");
		return null;
	}
	public Worker Insert_new_worker(User user) {
		System.out.println("Not implemented");
		return null;
	}
	public Integer Insert_new_utility(Utility utility) {
		System.out.println("Not implemented");
		return null;
	}
	public Integer Insert_new_maintenance(Maintenance maintenance) {
		System.out.println("Not implemented");
		return null;
	}
	public Integer Insert_new_transaction(Transaction transaction) {
		System.out.println("Not implemented");
		return null;
	}
	public Integer Insert_new_biomaterial(Biomaterial biomaterial) {
		System.out.println("Not implemented");
		return null;
	}
	
	
	
	/*  ---------------   LIST METHODS JPA   ------------------*/
	
	public List<Client> List_all_clients() {
		
		try {
			Query q1 = em.createNativeQuery("SELECT * FROM client", Client.class);
			
			@SuppressWarnings("unchecked")
			List<Client> clients = q1.getResultList();
			// Print the clients
				for (Client client : clients) {
					System.out.println(client);
				}
			em.close();
		return clients;
		
		} catch (EntityNotFoundException List_all_clients_error) {
			List_all_clients_error.printStackTrace();
			return null;
		}
	}
	
	public List<Category> List_all_categories() {
		
		try {
			Query q1 = em.createNativeQuery("SELECT * FROM category", Category.class);
			@SuppressWarnings("unchecked")
			List<Category> categories = q1.getResultList();
			// Print the clients
				for (Category category : categories) {
					System.out.println(category);
				}
			em.close();
		return categories;
		
		} catch (EntityNotFoundException List_all_categories_error) {
			List_all_categories_error.printStackTrace();
			return null;
		}
	}
	
	public List<User> List_all_users(){
		System.out.println("Not implemented");
		return null;
	}
	public List<Transaction> List_all_transactions(){
		System.out.println("Not implemented");
		return null;
	}
	public List<Biomaterial> List_all_biomaterials(){
		System.out.println("Not implemented");
		return null;
	}
	public List<Director> List_all_directors(){
		System.out.println("Not implemented");
		return null;
	}
	public List<Worker> List_all_workers(){
		System.out.println("Not implemented");
		return null;
	}
	public List<Utility> List_all_utilities(){
		System.out.println("Not implemented");
		return null;
	}
	public List<Maintenance> List_all_maintenances() {
		System.out.println("Not implemented");
		return null;
	}
	
	
	/*  ---------------   UPDATE METHODS JPA   ------------------*/
	
	
	public boolean Update_client_info(Client client) {
		try {
			
			Query q = em.createNativeQuery("SELECT * FROM client WHERE client_id = ?", Client.class);
			q.setParameter(1, client.getClient_id());
			Client c = (Client) q.getSingleResult();
			
			em.getTransaction().begin();
				c.setName(client.getName());
				c.setResponsible(client.getResponsible());
				c.setTelephone(client.getTelephone());
				c.setBank_account(client.getBank_account());
			
			em.getTransaction().commit();
			em.close();
			return true;
			
		} catch (EntityNotFoundException update_client_error) {
			update_client_error.printStackTrace();
			return false;
		}
	}
	
	public boolean Update_category_info(Category category) {
		try {
			
			Query q = em.createNativeQuery("SELECT * FROM category WHERE category_id = ?", Category.class);
			q.setParameter(1, category.getCategory_id());
			
			Category c = (Category) q.getSingleResult();
			
			em.getTransaction().begin();
			
			c.setCategory_name(category.getCategory_name());
			c.setMaximum(category.getMaximum());
			c.setMinimum(category.getMinimum());
			c.setPenalization(category.getMinimum()/4);
			
			
			em.getTransaction().commit();
			em.close();
			return true;
			
		} catch (EntityNotFoundException update_category_error) {
			update_category_error.printStackTrace();
			return false;
		}
	}
	
	public boolean Update_director_info(Director director) {
		System.out.println("Not implemented");
		return false;
	}
	public boolean Update_worker_info(Worker worker) {
		System.out.println("Not implemented");
		return false;
	}
	public void Update_biomaterial_features(Biomaterial biomaterial) {
		System.out.println("Not implemented");
	}
	public boolean Update_biomaterial_units(Biomaterial biomaterial) {
		System.out.println("Not implemented");
		return false;
	}
	
	
	/*  ---------------   DELETE METHODS JPA   ------------------*/
	
	public boolean Delete_stored_client(Client client) {
		try {
			em.getTransaction().begin();
			em.remove(client);
			em.getTransaction().commit();
			
			em.close();
			return true;
			
		} catch (EntityNotFoundException delete_client_error) {
			delete_client_error.printStackTrace();
			return false;
		}
	}
	
	public boolean Delete_stored_category(Category category) {
		try {
			em.getTransaction().begin();
			em.remove(category);
			em.getTransaction().commit();
			
			em.close();
			return true;
			
		} catch (EntityNotFoundException delete_category_error) {
			delete_category_error.printStackTrace();
			return false;
		}
	}
	
	public boolean Delete_stored_user(Integer user_id) {
		System.out.println("Not implemented");
		return false;
	}
	
	public boolean Delete_transaction_from_client(Transaction transaction) {
		System.out.println("Not implemented");
		return false;
	}
	
	public boolean Delete_biomaterial_by_id(Integer biomat_id) {
		System.out.println("Not implemented");
		return false;
	}
	/*  ---------------   READ METHODS JPA   ------------------*/
	
	public Client Search_stored_client(User user) {
		try {
			Query q_client = em.createNativeQuery("SELECT * FROM client WHERE user_id LIKE ?", Client.class);
			q_client.setParameter(1, user.getUserId());
			
			Client client = (Client) q_client.getSingleResult();
			
			em.close();
			
			return client;
			
		} catch (EntityNotFoundException search_client_error) {
			search_client_error.printStackTrace();
			return null;
		}
	}
	
	public Category Search_category_info(Category category) {
		try {
			Query q_category = em.createNativeQuery("SELECT * FROM category WHERE category_id LIKE ?" , Category.class);
			q_category.setParameter(1, category.getCategory_id());
			
			Category cat = (Category) q_category.getSingleResult();
			
			em.close();
			
			return cat;
			
		} catch (EntityNotFoundException search_category_error) {
			search_category_error.printStackTrace();
			return null;
		}
	
	}
	
	public Client Search_client_by_id (Integer client_id) {
		try {
			Query q_client = em.createNativeQuery("SELECT * FROM client WHERE client_id LIKE ?", Client.class);
			q_client.setParameter(1, client_id);
			
			Client client = (Client) q_client.getSingleResult();
			
			em.close();
			
			return client;
			
		} catch (EntityNotFoundException search_client_error) {
			search_client_error.printStackTrace();
			return null;
		}
	}
	
	public Utility Search_utility_by_id (Integer utility_id) {
		System.out.println("Not implemented");
		return null;
	}
	public User Search_stored_user(String name, String password) {
		System.out.println("Not implemented");
		return null;
	}
	public Director Search_stored_director(User user) {
		System.out.println("Not implemented");
		return null;
	}
	public Worker Search_stored_worker(User user) {
		System.out.println("Not implemented");
		return null;
	}
	public User Search_user_by_id(Integer user_id) {
		System.out.println("Not implemented");
		return null;
	}
	public Director Search_director_by_id(Integer director_id) {
		System.out.println("Not implemented");
		return null;
	}
	
	public Worker Search_worker_by_id (Integer worker_id) {
		System.out.println("Not implemented");
		return null;
	}
	public List<Transaction> Search_stored_transactions(Client client) {
		System.out.println("Not implemented");
		return null;
	}
	public Transaction Search_transaction_by_id(Integer transaction_id) {
		System.out.println("Not implemented");
		return null;
	}
	public Biomaterial Search_biomaterial_by_id (Integer biomaterial_id) {
		System.out.println("Not implemented");
		return null;
	}
	public Maintenance Search_maintenance_by_id (Integer maintenance_id) {
		System.out.println("Not implemented");
		return null;
	}
	
	
	
	
	public boolean Stablish_connection() {
		try {
		em = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER).createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean Close_connection() {
		try {
			em.close();
			return true;
		} catch (Exception close_error){
			close_error.printStackTrace();
			return false;
		}
		
	}
	
	/* Propias de SQL */
	
	public boolean Create_tables() {
		System.out.println("Not implemented");
		return false;
	}
	public boolean Check_if_tables_exist() {
		System.out.println("Not implemented");
		return false;
	}
	public void Change_password(String password, Integer user_id) {
		System.out.println("Not implemented");
	}
	
	
	
}

