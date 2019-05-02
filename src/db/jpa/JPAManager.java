package db.jpa;

import java.io.BufferedReader;


import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import db.model.Interface;
import db.pojos.*;

import javax.persistence.*;

public class JPAManager implements Interface {

	private static final String PERSISTENCE_PROVIDER = "project-provider";
	private static EntityManagerFactory factory;
	
	/*  ---------------   CREATE METHODS JPA   ------------------*/
	
	
	public Integer Insert_new_category(Category category) {
		try{
			
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
			EntityManager em = factory.createEntityManager();
			em = Persistence.createEntityManagerFactory("project-provider").createEntityManager();
			
		System.out.println("Please, input the new category info:");
						
			category.setCategory_name(category.getCategory_name());
			category.setPenalization(category.getMinimum()/4);
			category.setMaximum(category.getMaximum());
			category.setMinimum(category.getMinimum());
			em.persist(category);
			
			em.close();
			factory.close();
						
			return null;
		} catch(EntityNotFoundException new_category_error) {
			new_category_error.printStackTrace();
			return null;
		}
	}
	
	
	
	public Client Insert_new_client(User user) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
			EntityManager em = factory.createEntityManager();
			em = Persistence.createEntityManagerFactory("project-provider").createEntityManager();
			
			em.getTransaction().begin();
			
			Client client = new Client();
			System.out.println("Please, input the client info:");
				System.out.println("Input User ID: ");
				int id = Integer.parseInt(br.readLine());
				client.setClient_id(id);
				client.setName("Siemens");
				client.setResponsible("Carlos");
				client.setBank_account("1234");
				client.setTelephone(1234);
			// Insert client into the DB
			em.persist(client);
			em.getTransaction().commit();

			
			em.close();
			factory.close();
			return client;
			
		} catch (EntityNotFoundException new_client_account_error) {
			new_client_account_error.printStackTrace();
			return null;
		} catch (IOException read_error) {
			read_error.printStackTrace();
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
	public Integer Insert_new_benefits(Benefits benefits) {
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
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
			EntityManager em = factory.createEntityManager();
			em = Persistence.createEntityManagerFactory("project-provider").createEntityManager();
			Query q1 = em.createNativeQuery("SELECT * FROM Client", Client.class);
			@SuppressWarnings("unchecked")
			List<Client> clients = (List<Client>) q1.getResultList();
			// Print the clients
				for (Client client : clients) {
					System.out.println(client);
				}
			
		return clients;
		
		} catch (EntityNotFoundException List_all_clients_error) {
			List_all_clients_error.printStackTrace();
			return null;
		}
	}
	
	public List<Category> List_all_categories() {
		
		try {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
			EntityManager em = factory.createEntityManager();
			em = Persistence.createEntityManagerFactory("project-provider").createEntityManager();
			Query q1 = em.createNativeQuery("SELECT * FROM Client", Client.class);
			@SuppressWarnings("unchecked")
			List<Category> categories = (List<Category>) q1.getResultList();
			// Print the clients
				for (Category category : categories) {
					System.out.println(category);
				}
		return categories;
		
		} catch (EntityNotFoundException List_all_clients_error) {
			List_all_clients_error.printStackTrace();
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
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
			EntityManager em = factory.createEntityManager();
			em = Persistence.createEntityManagerFactory("project-provider").createEntityManager();
			
			em.getTransaction().begin();
			em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
			em.getTransaction().commit();
			
			//Query q = em.createNativeQuery("SELECT * FROM Client WHERE name LIKE ?", Client.class);

			System.out.println("List of all clients: ");
			List_all_clients();
			System.out.println("Please, select the client to update. Type it's ID: ");
			

			int client_id = Integer.parseInt(reader.readLine());
			Query q = em.createNativeQuery("SELECT * FROM Client WHERE client_id = ?", Client.class);
			q.setParameter(1, client_id);
			Client c = (Client) q.getSingleResult();
			
			em.getTransaction().begin();
			System.out.println("Please, set the new info of client: ");
				System.out.println("Name: ");
				String name = reader.readLine();
				client.setName(name);
				System.out.println("Responsible: ");
				String responsible = reader.readLine();
				client.setResponsible(responsible);
				System.out.println("Telephone: ");
				int tel = Integer.parseInt(reader.readLine());
				client.setTelephone(tel);
				System.out.println("Bank account: ");
				String bank = reader.readLine();
				client.setBank_account(bank);
			
			em.getTransaction().commit();
			em.close();
			factory.close();
			return true;
			
		} catch (EntityNotFoundException update_client_error) {
			update_client_error.printStackTrace();
			return false;
		} catch (IOException read_error) {
			read_error.printStackTrace();
			return false;
		}
	}
	
	public boolean Update_category_info(Category category) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
			EntityManager em = factory.createEntityManager();
			em = Persistence.createEntityManagerFactory("project-provider").createEntityManager();
			
			em.getTransaction().begin();
			em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
			em.getTransaction().commit();
			
			/* NOT NECESSARY
			Query q = em.createNativeQuery("SELECT * FROM Category WHERE name LIKE ?", Category.class);
			q.setParameter(1, reader.readLine());
			*/
			System.out.println("List of all categories: ");
			List_all_categories();
			System.out.println("Please, select the category to update. Type it's ID: ");
			

			int cat_id = Integer.parseInt(reader.readLine());
			Query q = em.createNativeQuery("SELECT * FROM Category WHERE category_id = ?", Category.class);
			q.setParameter(1, cat_id);
			Client c = (Client) q.getSingleResult();
			
			em.getTransaction().begin();
			System.out.println("Please, set the new info of the category: ");
				System.out.println("Name: ");
				String name = reader.readLine();
				category.setCategory_name(name);
				System.out.println("Maximum: ");
				int maximum = Integer.parseInt(reader.readLine());
				category.setMaximum(maximum);
				System.out.println("Minimum: ");
				int minimum = Integer.parseInt(reader.readLine());
				category.setMinimum(minimum);
				category.setPenalization(minimum/4);
			
			
			em.getTransaction().commit();
			em.close();
			factory.close();
			return true;
			
		} catch (EntityNotFoundException update_client_error) {
			update_client_error.printStackTrace();
			return false;
		} catch (IOException read_error) {
			read_error.printStackTrace();
			return false;
		}
	}
	
	public boolean Update_director_info(Director director) {
		System.out.println("Not implemented");
		return false;
	}
	
	public boolean Update_biomaterial_features(Biomaterial biomaterial) {
		System.out.println("Not implemented");
		return false;
	}
	
	
	/*  ---------------   DELETE METHODS JPA   ------------------*/
	
	public boolean Delete_stored_client(Client client) {
		try {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
			EntityManager em = factory.createEntityManager();
			em = Persistence.createEntityManagerFactory("project-provider").createEntityManager();
			
			em.getTransaction().begin();
			em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
			em.getTransaction().commit();
			
			
				em.remove(client);
			
			
			em.getTransaction().commit();
			em.close();
			factory.close();
			return true;
			
		} catch (EntityNotFoundException update_client_error) {
			update_client_error.printStackTrace();
			return false;
		}
	}
	
	public boolean Delete_stored_category(Category category) {
		try {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
			EntityManager em = factory.createEntityManager();
			em = Persistence.createEntityManagerFactory("project-provider").createEntityManager();
			
			em.getTransaction().begin();
			em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
			em.getTransaction().commit();
			
			
				em.remove(category);
			
			em.getTransaction().commit();
			em.close();
			factory.close();
			return true;
			
		} catch (EntityNotFoundException update_client_error) {
			update_client_error.printStackTrace();
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
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
			EntityManager em = factory.createEntityManager();
			em = Persistence.createEntityManagerFactory("project-provider").createEntityManager();
			
			em.getTransaction().begin();
			em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
			em.getTransaction().commit();
			
			Query q_client = em.createNativeQuery("SELECT * FROM client c WHERE c.user_id LIKE ?", Client.class);
			q_client.setParameter(1, user.getUserId());
			
						/*
						For reading more than one result we use List
						List<Client> list1 = q_client.getResultList();
						List<Category> list2 = q_category.getResultList();
						*/
			
			/*For reading ONE single result --- In this case we are reading a single result*/
			Client client = (Client) q_client.getSingleResult();
			
			
			em.getTransaction().commit();
			em.close();
			factory.close();
			
			return client;
			
		} catch (EntityNotFoundException update_client_error) {
			update_client_error.printStackTrace();
			return null;
		}
	}
	
	public Category Search_category_info(Category category) {
		try {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
			EntityManager em = factory.createEntityManager();
			em = Persistence.createEntityManagerFactory("project-provider").createEntityManager();
			
			em.getTransaction().begin();
			em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
			em.getTransaction().commit();
			
			Query q_category = em.createNativeQuery("SELECT * FROM Category c WHERE c.category_id LIKE ?" , Category.class);
			q_category.setParameter(1, category.getCategory_id());
			
			Category cat = (Category) q_category.getSingleResult();
			
			em.getTransaction().commit();
			em.close();
			factory.close();
			
			return cat;
			
		} catch (EntityNotFoundException update_client_error) {
			update_client_error.printStackTrace();
			return null;
		}
	
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
	public Biomaterial Search_stored_biomaterial(Biomaterial biomaterial) {
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
	public Client Search_client_by_id (Integer client_id) {
		System.out.println("Not implemented");
		return null;
	}
	public Worker Search_worker_by_id (Integer worker_id) {
		System.out.println("Not implemented");
		return null;
	}
	public List<Transaction> Search_stored_transaction(Client client){
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
	public Utility Search_utility_by_id (Integer utility_id) {
		System.out.println("Not implemented");
		return null;
	}
	
	/* Propias de SQL */
	
	public boolean Stablish_connection() {
		System.out.println("Not implemented");
		return false;
	}
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
	public boolean Close_connection() {
		System.out.println("Not implemented");
		return false;
	}
	@Override
	public List<Transaction> Search_stored_transactions(Client client) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
