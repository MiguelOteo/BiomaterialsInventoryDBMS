package db.jpa;

import java.util.List;

import db.model.Interface;
import db.pojos.*;

import javax.persistence.*;

public class JPAManager implements Interface {

	private static final String PERSISTENCE_PROVIDER = "project-provider";
	private static EntityManager entity_manager;
	
	public JPAManager() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// -----> CREATE CONNECTION METHOD <-----
	
	public boolean Stablish_connection() {
		try {
		entity_manager = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER).createEntityManager();
		entity_manager.getTransaction().begin();
		entity_manager.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		entity_manager.getTransaction().commit();
		return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// -----> INSERT METHODS JPA <-----
	
	public Integer Insert_new_category(Category category) {
		try{
			System.out.println("New category: " + entity_manager.getTransaction().isActive());
			Category catgory = new Category();
			category.setCategory_name(category.getCategory_name());
			category.setMaximum(category.getMaximum());
			category.setMinimum(category.getMinimum());
			category.setPenalization(category.getMinimum()/4);
			category.setBenefits(category.getBenefits());
			entity_manager.getTransaction().begin();
			entity_manager.persist(category);
			entity_manager.getTransaction().commit();	
			return category.getCategory_id();
		} catch(EntityNotFoundException new_category_error) {
			new_category_error.printStackTrace();
			return null;
		}
	}
	
	// Insert client into the DB
	public Client Insert_new_client(User user, Category category) {
		try {
			System.out.println("New client: " + entity_manager.getTransaction().isActive());
			Client client = new Client();
			client.setUser(user);
			client.setName(user.getUserName());
			entity_manager.getTransaction().begin();
			entity_manager.persist(client);
			entity_manager.getTransaction().commit();
			return client;
		} catch (EntityNotFoundException new_client_account_error) {
			new_client_account_error.printStackTrace();
			return null;
		}
	}
	
	public Integer Insert_new_benefit(Benefits benefits) {
		try{
			System.out.println("New benefit: " + entity_manager.getTransaction().isActive());
			Benefits benefit = new Benefits();
			benefit.setExtra_units(benefits.getExtra_units());
			benefit.setPercentage(benefits.getPercentage());
			entity_manager.getTransaction().begin();
			entity_manager.persist(benefits);
			entity_manager.getTransaction().commit();
			return benefit.getBenefits_id();
		} catch(EntityNotFoundException new_benefits_error) {
			new_benefits_error.printStackTrace();
			return null;
		}
	}
	
	// -----> LIST METHODS JPA <-----
	
	// List all clients
	public List<Client> List_all_clients() {
		try {
			Query query = entity_manager.createNativeQuery("SELECT * FROM client", Client.class);
			@SuppressWarnings("unchecked")
			List<Client> clients = query.getResultList();	
			return clients;
		} catch (EntityNotFoundException List_all_clients_error) {
			List_all_clients_error.printStackTrace();
			return null;
		}
	}
	
	// List all categories
	public List<Category> List_all_categories() {
		try {
			Query query = entity_manager.createNativeQuery("SELECT * FROM category", Category.class);
			@SuppressWarnings("unchecked")
			List<Category> categories = query.getResultList();
			return categories;
		} catch (EntityNotFoundException List_all_categories_error) {
			List_all_categories_error.printStackTrace();
			return null;
		}
	}
	
	// List all benefits
	public List<Benefits> List_all_benefits() {
		try {
			Query query = entity_manager.createNativeQuery("SELECT * FROM benefits", Benefits.class);
			@SuppressWarnings("unchecked")
			List<Benefits> benefits = query.getResultList();
			return benefits;
		} catch (EntityNotFoundException List_all_benefits_error) {
			List_all_benefits_error.printStackTrace();
			return null;
		}
	}
	
	// -----> UPDATE METHODS JPA <-----
	
	public boolean Update_client_info(Client client) {
		try {
			Query q = entity_manager.createNativeQuery("SELECT * FROM client WHERE client_id = ?", Client.class);
			q.setParameter(1, client.getClient_id());
			Client c = (Client) q.getSingleResult();
			entity_manager.getTransaction().begin();
				c.setName(client.getName());
				c.setResponsible(client.getResponsible());
				c.setTelephone(client.getTelephone());
				c.setBank_account(client.getBank_account());
			entity_manager.getTransaction().commit();
			return true;
		} catch (EntityNotFoundException update_client_error) {
			update_client_error.printStackTrace();
			return false;
		}
	}
	
	public boolean Update_category_info(Category category) {
		try {
			Query q = entity_manager.createNativeQuery("SELECT * FROM category WHERE category_id = ?", Category.class);
			q.setParameter(1, category.getCategory_id());
			Category c = (Category) q.getSingleResult();
			entity_manager.getTransaction().begin();
			c.setCategory_name(category.getCategory_name());
			c.setMaximum(category.getMaximum());
			c.setMinimum(category.getMinimum());
			c.setPenalization(category.getMinimum()/4);
			entity_manager.getTransaction().commit();
			return true;
		} catch (EntityNotFoundException update_category_error) {
			update_category_error.printStackTrace();
			return false;
		}
	}
		
	public boolean Update_benefits_info(Benefits benefits) {
		try {
			Query q = entity_manager.createNativeQuery("SELECT * FROM benefits WHERE benefits_id = ?", Benefits.class);
			q.setParameter(1, benefits.getBenefits_id());
			Benefits c = (Benefits) q.getSingleResult();
			entity_manager.getTransaction().begin();
			c.setExtra_units(benefits.getBenefits_id());
			c.setPercentage(benefits.getPercentage());
			entity_manager.getTransaction().commit();
			return true;
		} catch (EntityNotFoundException update_benefits_error) {
			update_benefits_error.printStackTrace();
			return false;
		}
	}
		
	// -----> DELETE METHODS JPA <-----
	
	public boolean Delete_stored_client(Client client) {
		try {
			entity_manager.getTransaction().begin();
			entity_manager.remove(client);
			entity_manager.getTransaction().commit();
			return true;
		} catch (EntityNotFoundException delete_client_error) {
			delete_client_error.printStackTrace();
			return false;
		}
	}
	
	public boolean Delete_stored_category(Category category) {
		try {
			entity_manager.getTransaction().begin();
			entity_manager.remove(category);
			entity_manager.getTransaction().commit();
			return true;
		} catch (EntityNotFoundException delete_category_error) {
			delete_category_error.printStackTrace();
			return false;
		}
	}
	
	public boolean Delete_stored_benefits(Benefits benefits) {
		try {
			entity_manager.getTransaction().begin();
			entity_manager.remove(benefits);
			entity_manager.getTransaction().commit();
			return true;
		} catch(EntityNotFoundException delete_benefits_error) {
			delete_benefits_error.printStackTrace();
			return false;
		}
	}
	
	// -----> SEARCH METHODS JPA <-----
	
	public Client Search_stored_client(User user) {
		try {
			Query query_client = entity_manager.createNativeQuery("SELECT * FROM client WHERE user_id LIKE ?", Client.class);
			query_client.setParameter(1, user.getUserId());
			Client client = (Client) query_client.getSingleResult();
			return client;
		} catch (EntityNotFoundException search_client_error) {
			search_client_error.printStackTrace();
			return null;
		}
	}
	
	public Category Search_category_by_id (Integer category_id) {
		try {
			Query query_category = entity_manager.createNativeQuery("SELECT * FROM category WHERE category_id LIKE ?" , Category.class);
			query_category.setParameter(1, category_id);
			Category category = (Category) query_category.getSingleResult();
			return category;
		} catch (EntityNotFoundException search_category_error) {
			search_category_error.printStackTrace();
			return null;
		}
	
	}
	
	public Client Search_client_by_id (Integer client_id) {
		try {
			Query query_client = entity_manager.createNativeQuery("SELECT * FROM client WHERE client_id LIKE ?", Client.class);
			query_client.setParameter(1, client_id);
			Client client = (Client) query_client.getSingleResult();
			return client;
		} catch (EntityNotFoundException search_client_error) {
			search_client_error.printStackTrace();
			return null;
		}
	}
	
	public Benefits Search_benefits_by_id(Integer benefits_id) {
		try {
			Query query_client = entity_manager.createNativeQuery("SELECT * FROM benefits WHERE benefits_id LIKE ?", Benefits.class);
			query_client.setParameter(1, benefits_id);
			Benefits benefits = (Benefits) query_client.getSingleResult();
			return benefits;
		} catch (EntityNotFoundException search_client_error) {
			search_client_error.printStackTrace();
			return null;
		}
	}
	
	public Category Search_none_category() {
		try {
			Query query_category = entity_manager.createNativeQuery("SELECT * FROM category WHERE category_name = 'None';", Category.class);
			Category category = (Category) query_category.getSingleResult();
			return category;
		} catch (EntityNotFoundException search_client_error) {
			search_client_error.printStackTrace();
			return null;
		}
	}
	
	// -----> CLOSE CONNECTION METHOD JPA <-----
	
	public boolean Close_connection() {
		try {
			entity_manager.close();
			return true;
		} catch (Exception close_error){
			close_error.printStackTrace();
			return false;
		}	
	}
	
	// -----> UNUSED INSERT METHODS <-----
	
	public User Insert_new_user(String user_name, String password) {return null;}
	public Director Insert_new_director(User user){return null;}
	public Worker Insert_new_worker(User user) {return null;}
	public Integer Insert_new_utility(Utility utility) {return null;}
	public Integer Insert_new_maintenance(Maintenance maintenance) {return null;}
	public Integer Insert_new_transaction(Transaction transaction) {return null;}
	public Integer Insert_new_biomaterial(Biomaterial biomaterial) {return null;}
	
	// -----> UNUSED LIST METHODS <-----
	
	public List<User> List_all_users(){return null;}
	public List<Transaction> List_all_transactions(){return null;}
	public List<Biomaterial> List_all_biomaterials(){return null;}
	public List<Director> List_all_directors(){return null;}
	public List<Worker> List_all_workers(){return null;}
	public List<Utility> List_all_utilities(){return null;}
	public List<Maintenance> List_all_maintenances() {return null;}
	
	// -----> UNUSED UPDATE METHODS <-----
	
	public boolean Update_director_info(Director director) {return false;}
	public boolean Update_worker_info(Worker worker) {return false;}
	public void Update_biomaterial_features(Biomaterial biomaterial) {}
	public boolean Update_biomaterial_units(Biomaterial biomaterial) {return false;}
	
	// -----> UNUSED DELETE METHODS <-----
	
	public boolean Delete_stored_user(Integer user_id) {return false;}
	public boolean Delete_transaction_from_client(Transaction transaction) {return false;}
	public boolean Delete_biomaterial_by_id(Integer biomat_id) {return false;}
	public boolean Delete_biomaterial_list(BiomaterialList list) {return false;}
	
	// -----> UNUSED SEARCH METHODS <-----
	
	public Utility Search_utility_by_id (Integer utility_id) {return null;}
	public User Search_stored_user(String name, String password) {return null;}
	public Director Search_stored_director(User user) {return null;}
	public Worker Search_stored_worker(User user) {return null;}
	public User Search_user_by_id(Integer user_id) {return null;}
	public Director Search_director_by_id(Integer director_id) {return null;}
	public Worker Search_worker_by_id (Integer worker_id) {return null;}
	
	public List<Transaction> Search_stored_transactions(Client client) {return null;}
	public Transaction Search_transaction_by_id(Integer transaction_id) {return null;}
	public Biomaterial Search_biomaterial_by_id (Integer biomaterial_id) {return null;}
	public Maintenance Search_maintenance_by_id (Integer maintenance_id) {return null;}
	
	// -----> SQL METHODS <-----
	
	public boolean Create_tables() {return false;}
	public boolean Check_if_tables_exist() {return false;}
	public void Change_password(String password, Integer user_id) {}

	
}

