package db.jpa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import db.pojos.Category;
import db.pojos.Client;

import javax.persistence.*;

public class JPAManager {

	private static final String PERSISTENCE_PROVIDER = "project-provider";
	private static EntityManagerFactory factory;
	
	/*  ---------------   CREATE METHODS JPA   ------------------*/
	
	
	public boolean Create_new_Category(Category category) {
		try{
			
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
			EntityManager em = factory.createEntityManager();
			em = Persistence.createEntityManagerFactory("project-provider").createEntityManager();
			
		System.out.println("Please, input the new category info:");
						
			category.setCategory_name("PlatinumMAX");
			category.setPenalization(1600/4);
			category.setMaximum(1800);
			category.setMinimum(1600);
			em.persist(category);
			
			em.close();
			factory.close();
						
			return true;
		} catch(EntityNotFoundException new_category_error) {
			new_category_error.printStackTrace();
			return false;
		}
	}
	
	
	
	public boolean Create_new_ClientAccount(Client client) {
		try {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
			EntityManager em = factory.createEntityManager();
			em = Persistence.createEntityManagerFactory("project-provider").createEntityManager();
			
			em.getTransaction().begin();
			
			System.out.println("Please, input the client info:");
			
				client.setName("Siemens");
				client.setResponsible("Carlos");
				client.setBank_account("1234");
				client.setTelephone(1234);
			// Insert client into the DB
			em.persist(client);
			em.getTransaction().commit();

			
			em.close();
			factory.close();
			return true;
			
		} catch (EntityNotFoundException new_client_account_error) {
			new_client_account_error.printStackTrace();
			return false;
		}
	}
	
	public boolean Create_new_Client(Client client) {
		try {
			
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
			EntityManager em = factory.createEntityManager();
			em = Persistence.createEntityManagerFactory("project-provider").createEntityManager();
			
			em.getTransaction().begin();
			
			System.out.println("Please, input the client info:");
			
				client.setName("Siemens");

				
			// Insert client into the DB
			em.persist(client);
			em.getTransaction().commit();

			
			em.close();
			factory.close();
			return true;
			
		} catch (EntityNotFoundException new_client_error) {
			new_client_error.printStackTrace();
			return false;
		}
	}
	
	
	
	/*  ---------------   UPDATE METHODS JPA   ------------------*/
	
	private static void printAllClients() {
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
	}
	
	private static void printAllCategories() {
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
	}
	
	
	public boolean Update_client_info(Client client) throws IOException{
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
			EntityManager em = factory.createEntityManager();
			em = Persistence.createEntityManagerFactory("project-provider").createEntityManager();
			
			em.getTransaction().begin();
			em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
			em.getTransaction().commit();
			
			Query q = em.createNativeQuery("SELECT * FROM Client WHERE name LIKE ?", Client.class);

			System.out.println("List of all clients: ");
			printAllClients();
			System.out.println("Please, select the client to update. Type it's ID: ");
			

			int client_id = Integer.parseInt(reader.readLine());
			Query q2 = em.createNativeQuery("SELECT * FROM Client WHERE client_id = ?", Client.class);
			q2.setParameter(1, client_id);
			Client c = (Client) q2.getSingleResult();
			
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
		}
	}
	
	public boolean Update_category_info(Category category) throws IOException{
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
			EntityManager em = factory.createEntityManager();
			em = Persistence.createEntityManagerFactory("project-provider").createEntityManager();
			
			em.getTransaction().begin();
			em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
			em.getTransaction().commit();
			
			Query q = em.createNativeQuery("SELECT * FROM Category WHERE name LIKE ?", Category.class);

			System.out.println("List of all categories: ");
			printAllCategories();
			System.out.println("Please, select the category to update. Type it's ID: ");
			

			int cat_id = Integer.parseInt(reader.readLine());
			Query q2 = em.createNativeQuery("SELECT * FROM Category WHERE category_id = ?", Category.class);
			q2.setParameter(1, cat_id);
			Client c = (Client) q2.getSingleResult();
			
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
		}
	}
	
	
	/*  ---------------   DELETE METHODS JPA   ------------------*/
	
	public boolean Delete_client(Client client) {
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
	
	public boolean Delete_category(Category category) {
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
	
	/*  ---------------   READ METHODS JPA   ------------------*/
	
	public boolean Read_client_info(Client client) {
		try {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
			EntityManager em = factory.createEntityManager();
			em = Persistence.createEntityManagerFactory("project-provider").createEntityManager();
			
			em.getTransaction().begin();
			em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
			em.getTransaction().commit();
			
			Query q_client = em.createNativeQuery("SELECT FROM Client", Client.class);
			
						/*For reading more than one result we use List
						List<Client> list1 = q_client.getResultList();
						List<Category> list2 = q_category.getResultList();
						*/
			
			/*For reading ONE single result --- In this case we are reading a single result*/
			Client c = (Client) q_client.getSingleResult();
			
			
			em.getTransaction().commit();
			em.close();
			factory.close();
			
			return true;
			
		} catch (EntityNotFoundException update_client_error) {
			update_client_error.printStackTrace();
			return false;
		}
	}
	
	public boolean Read_category_info(Category category) {
		try {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
			EntityManager em = factory.createEntityManager();
			em = Persistence.createEntityManagerFactory("project-provider").createEntityManager();
			
			em.getTransaction().begin();
			em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
			em.getTransaction().commit();
			
			Query q_category = em.createNativeQuery("SELECT category_name FROM Category" , Category.class);
			
			Category o = (Category) q_category.getSingleResult();
			
			em.getTransaction().commit();
			em.close();
			factory.close();
			
			return true;
			
		} catch (EntityNotFoundException update_client_error) {
			update_client_error.printStackTrace();
			return false;
		}
	}
	
	
}
