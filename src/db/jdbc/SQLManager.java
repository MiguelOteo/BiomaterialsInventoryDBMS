package db.jdbc;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import db.model.Interface;
import db.pojos.Benefits;
import db.pojos.Biomaterial;
import db.pojos.Category;
import db.pojos.Client;
import db.pojos.Director;
import db.pojos.Maintenance;
import db.pojos.Transaction;
import db.pojos.User;
import db.pojos.Utility;
import db.pojos.Worker;

public class SQLManager implements Interface{
	
	private Connection sqlite_connection;

	public SQLManager() {
		/*
		 * Empty constructor that will allow to create a Manager object in order to use
		 * the methods
		 */
	}

	// Connection route: "jdbc:sqlite:./db/biomat.db"
	public boolean Stablish_connection() {
		try {
			Class.forName("org.sqlite.JDBC");
			this.sqlite_connection = DriverManager.getConnection("jdbc:sqlite:./db/biomat.db");
			this.sqlite_connection.createStatement().execute("PRAGMA foreign_keys=ON");
			return true;
		} catch (ClassNotFoundException | SQLException connection_error) {
			connection_error.printStackTrace();
			return false;
		}
	}

	// Creates the tables in biomat.db file
	public boolean Create_tables() {
		try {
			
			Statement statement_0 = this.sqlite_connection.createStatement();
			String table_0 = " CREATE TABLE user " + "(user_id INTEGER PRIMARY KEY AUTOINCREMENT, "
			        + " user_name TEXT NOT NULL UNIQUE, " + " password TEXT NOT NULL)";
			statement_0.execute(table_0);
			statement_0.close();
			
			Statement statement_1 = this.sqlite_connection.createStatement();
			String table_1 = "CREATE TABLE director " + "(director_id INTEGER PRIMARY KEY AUTOINCREMENT, " + " name TEXT, " 
			        + "telephone INTEGER default 0, " + "email TEXT, " + " user_id FOREING KEY REFERENCES user(user_id) ON DELETE CASCADE)";
			statement_1.execute(table_1);
		    statement_1.close();
			
			Statement statement_2 = this.sqlite_connection.createStatement();
			String table_2 = "CREATE TABLE client " + "(client_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ " responsible TEXT, " + " name TEXT," + " bank_account TEXT UNIQUE, "
					+ " telephone INTEGER default 0, " + " points INTEGER NOT NULL default 0, " 
					+ " user_id FOREING KEY REFERENCES user(user_id) ON DELETE CASCADE)";
			statement_2.execute(table_2);
			statement_2.close();
			
		    Statement statement_3 = this.sqlite_connection.createStatement();
		    String table_3 = "CREATE TABLE worker " + "(worker_id INTEGER PRIMARY KEY AUTOINCREMENT, " + " name TEXT, " 
		            + " telephone INTEGER default 0, " + "email TEXT, "
		            + " user_id FOREING KEY REFERENCES user(user_id) ON DELETE CASCADE)";
		    statement_3.execute(table_3);
		    statement_3.close();
		    
			Statement statement_4 = this.sqlite_connection.createStatement();
			String table_4 = " CREATE TABLE benefits " + "(benefits_id INTEGER PRIMARY KEY, "
					+ " percentage REAL NOT NULL default 0, " /*+ " min_amount INTEGER NOT NULL default 0,"*/
					+ " extra_units INTEGER NOT NULL default 0)";
			statement_4.execute(table_4);
			statement_4.close();

			Statement statement_5 = this.sqlite_connection.createStatement();
			String table_5 = "CREATE TABLE category " + "(category_id INTEGER REFERENCES benefits(benefits_id), "
					+ " category_name TEXT NOT NULL, " + " penalization INTEGER default NULL, "
					// Money interval//
					+ " max INTEGER NOT NULL, " + " min INTEGER NOT NULL)";
			statement_5.execute(table_5);
			statement_5.close();

			Statement statement_6 = this.sqlite_connection.createStatement();
			String table_6 = "CREATE TABLE utility " + "(utility_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ " heat_cold INTEGER default NULL, " + " flexibility TEXT default 'no', "
					+ " resistance TEXT default 'yes', " + " pressure REAL default 1, "
					+ " strength REAL)";
			statement_6.execute(table_6);
			statement_6.close();

			Statement statement_7 = this.sqlite_connection.createStatement();
			String table_7 = "CREATE TABLE maintenance " + "(maintenance_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ " pressure REAL default 1, " + " humidity INT default 50, "
					+ " O2_supply TEXT default 'no', " + " light TEXT default 'no', "
					+ " temperature REAL default 20, " + " compatibility TEXT, "
					+ " others TEXT default NULL)";
			statement_7.execute(table_7);
			statement_7.close();

			Statement statement_8 = this.sqlite_connection.createStatement();
			String table_8 = "CREATE TABLE biomaterial " + "(biomaterial_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ " utility_id INTEGER REFERENCES utility (utility_id), "
					+ " name_product TEXT NOT NULL, "
					+ " price_unit INTEGER NULL default 1, " + " available_units INTEGER NOT NULL, "
					+ " expiration_date DATETIME, "
					+ " maintenance_id INTEGER REFERENCES maintenance(maintenance_id) ON UPDATE RESTRICT ON DELETE CASCADE)";
			statement_8.execute(table_8);
			statement_8.close();

			Statement statement_9 = this.sqlite_connection.createStatement();
			String table_9 = "CREATE TABLE bank_transaction " + "(transaction_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ " client_id INTEGER NOT NULL, " + " gain REAL NOT NULL, " + " units INTEGER NOT NULL default 1, "
					+ " transaction_date DATETIME NOT NULL, " + " biomaterial_id INTEGER NOT NULL REFERENCES biomaterial(biomaterial_id), "
					+ " FOREIGN KEY (client_id) REFERENCES client (client_id) ON UPDATE RESTRICT ON DELETE CASCADE)";
			statement_9.execute(table_9);
			statement_9.close();
			
			return true;
		} catch (SQLException tables_error) {
			tables_error.printStackTrace();
			return false;
		}
	}
	
	// -----> CHECK ALREADY EXISTING TABLES METHOD <-----

	// Checks if all the tables are already created in biomat.db in order to avoid executing the method Create_tables 
	public boolean Check_if_tables_exist() {
			
		String[] tables_array = new String[]{"user", "director", "client", "worker", "bank_transaction", "utility",
				"biomaterial", "maintenance", "category", "benefits"};
			
		for(int table = 0; table < tables_array.length; table++) {
			try {
				DatabaseMetaData meta_data = this.sqlite_connection.getMetaData();
				ResultSet tables = meta_data.getTables(null, null, tables_array[table], null);
				if (tables.next() == false) {
					return false;
				}
			} catch (SQLException check_tables_error) {
				check_tables_error.printStackTrace();
				return false;
			}
		}
		return true;
	}

	// -----> INSERT METHODS <-----
	
	// New_User(user_name, password)
	public User Insert_new_user(String user_name, String password) {
		try {
			String table = "INSERT INTO user (user_name, password) " + " VALUES(?,?);";
			PreparedStatement template = this.sqlite_connection.prepareStatement(table);
			template.setString(1, user_name);
			template.setString(2, password);
			template.executeUpdate();
			
			String SQL_code = "SELECT * FROM user WHERE user_name = ?";
			template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setString(1, user_name);
			ResultSet result_set = template.executeQuery();
			User user = new User();
		    user.setUserName(result_set.getString("user_name"));
		    user.setPassword(result_set.getString("password"));
		    user.setUserId(result_set.getInt("user_id"));
		    return user;
		} catch (SQLException insert_user_error) {
			return null;
		}
	}
	
	// New_Client(name, password)
    public Client Insert_new_client(User user) {
		try {
			String table = "INSERT INTO client (user_id, name) " + "VALUES (?,?);";
			PreparedStatement template = this.sqlite_connection.prepareStatement(table);
			template.setInt(1, user.getUserId());
			template.setString(2, user.getUserName());
			template.executeUpdate();
			
			String SQL_code = "SELECT * FROM client WHERE user_id = ?";
			template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setInt(1, user.getUserId());
			ResultSet result_set = template.executeQuery();
			Client client = new Client();
			client.setClient_id(result_set.getInt("client_id"));
			client.setName(result_set.getString("name"));
			client.setUser(user);
			client.setTelephone(0);
			client.setPoints(0);
			return client;
		} catch (SQLException new_client_account_error) {
			return null;
		}
    }
	
	// New_Director(name, password) 
	public Director Insert_new_director(User user) {
		try {
			String table = "INSERT INTO director (user_id, name) " + "VALUES (?,?)";
			PreparedStatement template = this.sqlite_connection.prepareStatement(table);
			template.setInt(1, user.getUserId());
			template.setString(2, user.getUserName());
			template.executeUpdate();
			
			String SQL_code = "SELECT * FROM director WHERE user_id = ?";
			template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setInt(1, user.getUserId());
			ResultSet result_set = template.executeQuery();
			Director director = new Director();
			director.setDirector_id(result_set.getInt("director_id"));
			director.setDirector_name(result_set.getString("name"));
			director.setUser(user);
			director.setTelephone(0);
			return director;
		} catch(SQLException new_director_error) {
			return null;
		}
	}
	
	
	public Worker Insert_new_worker(User user) {
		try {
			String table = "INSERT INTO worker (user_id, name) " + "VALUES (?,?)";
			PreparedStatement template = this.sqlite_connection.prepareStatement(table);
			template.setInt(1, user.getUserId());
			template.setString(2, user.getUserName());
			template.executeUpdate();
			template.close();
			
			String SQL_code = "SELECT * FROM worker WHERE user_id = ?";
			template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setInt(1, user.getUserId());
			ResultSet result_set = template.executeQuery();
			Worker worker = new Worker();
			worker.setWorker_name(result_set.getString("name"));
			worker.setWorker_id(result_set.getInt("worker_id"));
			worker.setUser(user);
			template.close();
			return worker;
		} catch(SQLException new_worker_error) {
			new_worker_error.printStackTrace();
			return null;
		}
	}
	
	// Benefits(others, percentage, min_amount, extra_units)
	public Integer Insert_new_benefits(Benefits benefits) {
		try {
			String table = "INSERT INTO benefits(percentage, extra_units) " + "VALUES (?,?);";
			PreparedStatement template = this.sqlite_connection.prepareStatement(table);
			template.setFloat(1, benefits.getPercentage());
			//template.setInt(3, benefits.getMin_amount());
			template.setInt(2, benefits.getExtra_units());
			template.executeUpdate();
			template.close();
			
			String SQL_code = "SELECT last_insert_rowid() AS benefits_id";
			template = this.sqlite_connection.prepareStatement(SQL_code);
			ResultSet result_set = template.executeQuery();
			Integer benefits_id = result_set.getInt("benefits_id");
			template.close();	
			return benefits_id;
		} catch (SQLException new_benefits_error) {
			new_benefits_error.printStackTrace();
			return null;
		}
	}

	// Category(category_name, penalization, maximum, minimum)
	public Integer Insert_new_category(Category category) {
		try {
			String table = "INSERT INTO category(category_name, penalization, max, min) " + "VALUES (?,?,?,?);";
			PreparedStatement template = this.sqlite_connection.prepareStatement(table);
			template.setString(1, category.getCategory_name());
			template.setFloat(2, category.getMinimum()/4);
			template.setInt(3, category.getMaximum());
			template.setInt(4, category.getMinimum());
			template.executeUpdate();
			template.close();
			
			String SQL_code = "SELECT last_insert_rowid() AS category_id";
			template = this.sqlite_connection.prepareStatement(SQL_code);
			ResultSet result_set = template.executeQuery();
			Integer category_id = result_set.getInt("category_id");
			template.close();
			return category_id;
		} catch (SQLException new_category_error) {
			new_category_error.printStackTrace();
			return null;
		}
	}

	// Utility(heat_cold, flexibility, resistance, pressure, strength)
	public Integer Insert_new_utility(Utility utility) {
		try {
			String table = "INSERT INTO utility(heat_cold, flexibility, resistance, pressure, strength) "
					+ "VALUES (?,?,?,?,?);";
			PreparedStatement template = this.sqlite_connection.prepareStatement(table);
			template.setString(1, utility.getHeat_cold());
			template.setString(2, utility.getFlexibility());
			template.setString(3, utility.getResistance());
			template.setFloat(4, utility.getPressure());
			template.setFloat(5, utility.getStrength());
			template.executeUpdate();
			template.close();
			
			String SQL_code = "SELECT last_insert_rowid() AS utility_id";
			template = this.sqlite_connection.prepareStatement(SQL_code);
			ResultSet result_set = template.executeQuery();
			Integer utility_id = result_set.getInt("utility_id");
			template.close();
			return utility_id;
		} catch (SQLException new_utility_error) {
			new_utility_error.printStackTrace();
			return null;
		}
	}

	// Maintenance(pressure, humidity, O2_supply, light, temperature, compatibility, others)
	public Integer Insert_new_maintenance(Maintenance maintenance) {
		try {
			String table = "INSERT INTO maintenance(pressure, humidity, O2_supply, light, temperature, compatibility, others) "
					+ "VALUES (?,?,?,?,?,?,?);";
			PreparedStatement template = this.sqlite_connection.prepareStatement(table);
			template.setFloat(1, maintenance.getPressure());
			template.setInt(2, maintenance.getHumidity());
			template.setString(3, maintenance.getO2_supply());
			template.setString(4, maintenance.getLight());
			template.setFloat(5, maintenance.getTemperature());
			template.setString(6, maintenance.getCompatibility());
			template.setString(7, maintenance.getOthers());
			template.executeUpdate();
			template.close();
				
			String SQL_code = "SELECT last_insert_rowid() AS maintenance_id";
			template = this.sqlite_connection.prepareStatement(SQL_code);
			ResultSet result_set = template.executeQuery();
			Integer maintenance_id = result_set.getInt("maintenance_id");
			template.close();
			return maintenance_id;
		} catch (SQLException new_maintenance_error) {
			new_maintenance_error.printStackTrace();
			return null;
		}
	}

	// Bank_transaction(client_id, gain, units, transaction_date, product_id)
	public Integer Insert_new_transaction(Transaction transaction) {
		try {
			String table = "INSERT INTO bank_transaction(client_id, gain, units, transaction_date, biomaterial_id) "
					+ "VALUES (?,?,?,?,?);";
			PreparedStatement template = this.sqlite_connection.prepareStatement(table);
			template.setInt(1, transaction.getClient().getClient_id());
			template.setFloat(2, transaction.getGain());
			template.setInt(3, transaction.getUnits());
			template.setDate(4, Date.valueOf(LocalDate.now()));
			template.setInt(5, transaction.getBiomaterial().getBiomaterial_id());
			template.executeUpdate();
			template.close();
		
			String SQL_code = "SELECT last_insert_rowid() AS transaction_id";
			template = this.sqlite_connection.prepareStatement(SQL_code);
			ResultSet result_set = template.executeQuery();
			Integer transaction_id = result_set.getInt("transaction_id");
			template.close();
			return transaction_id;
		} catch (SQLException new_transaction_error) {
			new_transaction_error.printStackTrace();
			return null;
		}
	}

	// Biomaterial(utility_id, maintenance_id, name_product, price_unit, available_units, expiration_date)
	public Integer Insert_new_biomaterial(Biomaterial biomaterial) {
		try {
			String table = "INSERT INTO biomaterial(name_product, price_unit, available_units, expiration_date) "
					+ "VALUES (?,?,?,?);";
			PreparedStatement template = this.sqlite_connection.prepareStatement(table);
			/*template.setInt(1, biomaterial.getUtility().getUtility_id());
			template.setInt(2, biomaterial.getMaintenance().getManteinance_id());
			*/template.setString(1, biomaterial.getName_product());
			template.setFloat(2, biomaterial.getPrice_unit());
			template.setInt(3, biomaterial.getAvailable_units());
			template.setDate(4, biomaterial.getExpiration_date());
			template.executeUpdate();
			template.close();
			
			String SQL_code = "SELECT last_insert_rowid() AS biomaterial_id";
			template = this.sqlite_connection.prepareStatement(SQL_code);
			ResultSet result_set = template.executeQuery();
			Integer biomaterial_id = result_set.getInt("biomaterial_id");
			template.close();
			return biomaterial_id;
		} catch (SQLException new_biomaterial_error) {
			new_biomaterial_error.printStackTrace();
			return null;
		}
	}
	
	// -----> UPDATE METHODS <-----
	
	public void Change_password(String password, Integer user_id) {
		try {
			String SQL_code = "UPDATE user SET password = ? WHERE user_id = ?";
			PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setString(1, password);
			template.setInt(2, user_id);
			template.executeUpdate();
			template.close();
		} catch (SQLException update_password_error) {
			update_password_error.printStackTrace();
		}
	}
	
	// Updates the information of a Client(responsible, name, bank_account, telephone)
	public boolean Update_client_info(Client client) {
		try {
			String SQL_code = "UPDATE client SET responsible = ?, name = ?, bank_account = ?, telephone = ? WHERE client_id = ?";
			PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setString(1, client.getResponsible());
			template.setString(2, client.getName());
			template.setString(3, client.getName());
			template.setInt(4, client.getTelephone());
			template.setInt(5, client.getClient_id());
			template.executeUpdate();
			template.close();
			return true;
		} catch (SQLException update_client_error) {
			update_client_error.printStackTrace();
			return false;
		}
	}
	
	public boolean Update_director_info(Director director) {
		try {
			String SQL_code = "UPDATE director SET name = ?, telephone = ?, email = ? WHERE director_id = ?";
			PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setString(1, director.getDirector_name());
			template.setInt(2, director.getTelephone());
			template.setString(3, director.getEmail());
			template.setInt(4, director.getDirector_id());
			template.executeUpdate();
			template.close();
			return true;				
		} catch (SQLException update_director_error) {
			update_director_error.printStackTrace();
			return false;
		}
	}

	public boolean Update_category_info(Category category) {
		try {
			String SQL_code = "UPDATE category SET name = ?, maximum = ?, minimum = ? WHERE category_id = ?";
			PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setString(1, category.getCategory_name());
			template.setInt(2, category.getMaximum());
			template.setInt(3, category.getMinimum());
			template.setInt(4, category.getCategory_id());
			template.close();	
			return true;
		} catch (SQLException update_category_error) {
			update_category_error.printStackTrace();
			return false;
		}
	}
	
	public boolean Update_biomaterial_features(Biomaterial biomaterial) {
		try {
			String SQL_code = "UPDATE biomaterial SET utility_id = ?, maintenance_id = ? WHERE biomaterial_id = ?";
			PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setInt(1, biomaterial.getUtility().getUtility_id());
			template.setInt(2, biomaterial.getMaintenance().getManteinance_id());
			template.setInt(3, biomaterial.getBiomaterial_id());
			template.close();	
			return true;
		} catch (SQLException update_category_error) {
			update_category_error.printStackTrace();
			return false;
		}
	}
	
	
	// -----> SEARCH USER DIRECTOR WORKER CLIENT METHODS <-----

	// Selects all users objects with the same user_name from the data base and returns them
	public User Search_stored_user(String name, String password) {
		try {
			String SQL_code = "SELECT * FROM user WHERE user_name LIKE ? AND password LIKE ?";
			PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setString(1, name);
			template.setString(2, password);
			ResultSet result_set = template.executeQuery();
	        User user = new User();
	        user.setUserId(result_set.getInt("user_id"));
	        user.setUserName(result_set.getString("user_name"));
	        user.setPassword(result_set.getString("password"));
	        template.close();
	        return user;
		} catch (SQLException search_user_error) {
			return null;
		}
	}
	
	// Selects the client object with the same user_id from the data base and returns them
	public Client Search_stored_client(User user) {
		try {
			String SQL_code = "SELECT * FROM client WHERE user_id LIKE ?";
			PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setInt(1, user.getUserId());
			ResultSet result_set = template.executeQuery();
			Client client = new Client();
			client.setClient_id(result_set.getInt("client_id"));
			client.setName(result_set.getString("name"));
			client.setResponsible(result_set.getString("responsible"));
			client.setBank_account(result_set.getString("bank_account"));
			client.setTelephone(result_set.getInt("telephone"));
			client.setUser(user);
			template.close();
			return client;
		} catch (SQLException search_client_error) {
			return null;
		}
	}
	
	// Selects the director object with the same user_id from the data base and returns them
	public Director Search_stored_director(User user) {
		try {
			String SQL_code = "SELECT * FROM director WHERE user_id LIKE ?";
			PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setInt(1, user.getUserId());
			ResultSet result_set = template.executeQuery();
			Director director = new Director();
			director.setDirector_id(result_set.getInt("director_id"));
			director.setDirector_name(result_set.getString("name"));
			director.setEmail(result_set.getString("email"));
			director.setTelephone(result_set.getInt("telephone"));
			director.setUser(user);
			template.close();
			return director;
		} catch (SQLException search_director_error) {
			return null;
		}
	}
	
	// Selects the director object with the same user_id from the data base and returns them
	public Worker Search_stored_worker(User user) {
		try {
			String SQL_code = "SELECT * FROM worker WHERE user_id LIKE ?";
			PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setInt(1, user.getUserId());
			ResultSet result_set = template.executeQuery();
			Worker worker = new Worker();
			worker.setWorker_id(result_set.getInt("worker_id"));
			worker.setWorker_name(result_set.getString("name"));
			worker.setEmail(result_set.getString("email"));
			worker.setTelephone(result_set.getInt("telephone"));
			worker.setUser(user);
			template.close();
			return worker;
		} catch (SQLException search_worker_error) {
			return null;
		}
	}
	
	public Biomaterial Search_stored_biomaterial(Biomaterial biomaterial) {
		try {
			String SQL_code = "SELECT * FROM boimaterial WHERE biomaterial_id LIKE ?";
			PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setInt(1, biomaterial.getBiomaterial_id());
			ResultSet result_set = template.executeQuery();
			Biomaterial worker = new Biomaterial();
			biomaterial.setAvailable_units(result_set.getInt("available_units"));
			biomaterial.setBiomaterial_id(result_set.getInt("biomaterial_id"));
			biomaterial.setExpiration_date(result_set.getDate("expiration_date"));
			biomaterial.setName_product(result_set.getString("name_product"));
			biomaterial.setPrice_unit(result_set.getInt("price_unit"));
			biomaterial.setUtility(Search_utility_by_id(result_set.getInt("utility_id")));
			biomaterial.setMaintenance(Search_maintenance_by_id(result_set.getInt("maintenance_id")));
			
			template.close();
			return worker;
		} catch (SQLException search_worker_error) {
			return null;
		}
	}
	
	// -----> SEARCH BY ID METHODS <-----
	
	// Selects the user object with the same user_id from the data base and returns it
	public User Search_user_by_id(Integer user_id) {
		try {
			String SQL_code = "SELECT * FROM user WHERE user_id LIKE ?";
			PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setInt(1, user_id);
			ResultSet result_set = template.executeQuery();
			User user = new User();
			user.setUserId(user_id);
			user.setUserName(result_set.getString("user_name"));
			user.setPassword(result_set.getString("password"));
			template.close();
			return user;
		} catch (SQLException search_user_error) {
			search_user_error.printStackTrace();
			return null;
		}
	}
    
	// Selects the director object with the same director_id from the data base and returns it
	public Director Search_director_by_id (Integer director_id) {
		try {
			String SQL_code = "SELECT * FROM director WHERE director_id LIKE ?";
			PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setInt(1, director_id);
			Director director = new Director();
			ResultSet result_set = template.executeQuery();
			director.setDirector_id(director_id);
			director.setDirector_name(result_set.getString("name"));
			director.setEmail(result_set.getString("email"));
			director.setTelephone(result_set.getInt("telephone"));
			User user = Search_user_by_id(result_set.getInt("user_id"));
			director.setUser(user);
			template.close();
			return director;
		} catch (SQLException search_director_error) {
			search_director_error.printStackTrace();
			return null;
		}
	}
	 
	public Worker Search_worker_by_id (Integer worker_id) {
		try {
			String SQL_code = "SELECT * FROM worker WHERE worker_id LIKE ?";
			PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setInt(1, worker_id);
			Worker worker = new Worker();
			ResultSet result_set = template.executeQuery();
			worker.setWorker_id(worker_id);
			worker.setWorker_name(result_set.getString("name"));
			worker.setEmail(result_set.getString("email"));
			worker.setTelephone(result_set.getInt("telephone"));
			User user = Search_user_by_id(result_set.getInt("user_id"));
			worker.setUser(user);
			template.close();
			return worker;
		} catch (SQLException search_director_error) {
			search_director_error.printStackTrace();
			return null;
		}
	}
	
	// Selects the client object with the same client_id from the data base and returns it
	public Client Search_client_by_id (Integer client_id) {
			try {
				String SQL_code = "SELECT * FROM client WHERE client_id LIKE ?";
				PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
				template.setInt(1, client_id);
				Client client = new Client();
				ResultSet result_set = template.executeQuery();
                client.setBank_account(result_set.getString("bank_account"));
                client.setClient_id(client_id);
                client.setName(result_set.getString("name"));
                client.setPoints(result_set.getInt("points"));
                client.setResponsible(result_set.getString("responsible"));
                client.setTelephone(result_set.getInt("telephone"));
                List<Transaction> transaction_list = Search_stored_transactions(client);
				User user = Search_user_by_id(result_set.getInt("user_id"));
				client.setUser(user);
				template.close(); 
				return client;
			} catch (SQLException search_client_error) {
				search_client_error.printStackTrace();
				return null;
		}
    }
		
	// Selects the utility object with the same utility_id from the data base and returns it
	public Utility Search_utility_by_id (Integer utility_id) {
			try {
				String SQL_code = "SELECT * FROM utility WHERE utility_id LIKE ?";
				PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
				template.setInt(1, utility_id);
				Utility utility = new Utility();
				ResultSet result_set = template.executeQuery();
                utility.setFlexibility(result_set.getString("flexibility"));
                utility.setHeat_cold(result_set.getString("heat_cold"));
                utility.setPressure(result_set.getFloat("pressure"));
                utility.setResistance(result_set.getString("resistance"));
                utility.setStrength(result_set.getFloat("strength"));
                utility.setUtility_id(utility_id);
				template.close();
				return utility;
			} catch (SQLException search_utility_error) {
				search_utility_error.printStackTrace();
				return null;
		}
    }
	
	// Selects the maintenance object with the same maintenance_id from the data base and returns it
	public Maintenance Search_maintenance_by_id (Integer maintenance_id) {
			try {
				String SQL_code = "SELECT * FROM maintenance WHERE maintenance_id LIKE ?";
				PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
				template.setInt(1, maintenance_id);
				Maintenance maintenance = new Maintenance();
				ResultSet result_set = template.executeQuery();
                maintenance.setCompatibility(result_set.getString("compatibility"));
                maintenance.setHumidity(result_set.getInt("humidity"));
                maintenance.setLight(result_set.getString("light"));
                maintenance.setO2_supply(result_set.getString("o2_supply"));
                maintenance.setOthers(result_set.getString("others"));
                maintenance.setPressure(result_set.getFloat("pressure"));
                maintenance.setTemperature(result_set.getFloat("temperature"));
                maintenance.setManteinance_id(maintenance_id);
				template.close();
				return maintenance;
			} catch (SQLException search_maintenance_error) {
				search_maintenance_error.printStackTrace();
				return null;
		}
    }
	
	// Selects the utility object with the same utility_id from the data base and returns it
	public Biomaterial Search_biomaterial_by_id (Integer biomaterial_id) {
			try {
				String SQL_code = "SELECT * FROM biomaterial WHERE biomaterial_id LIKE ?";
				PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
				template.setInt(1, biomaterial_id);
				Biomaterial biomaterial = new Biomaterial();
				ResultSet result_set = template.executeQuery();
                biomaterial.setAvailable_units(result_set.getInt("available_units"));
                biomaterial.setExpiration_date(result_set.getDate("expiration_date"));
                biomaterial.setName_product(result_set.getString("name_product"));
                biomaterial.setPrice_unit(result_set.getInt("price_unit"));
                biomaterial.setBiomaterial_id(biomaterial_id);
                /*Utility utility = Search_utility_by_id(result_set.getInt("utility_id"));
                biomaterial.setUtility(utility);
                Maintenance maintenance = Search_maintenance_by_id(result_set.getInt("maintenance_id"));
                biomaterial.setMaintenance(maintenance);*/
                template.close();
				return biomaterial;
			} catch (SQLException search_biomaterial_error) {
				search_biomaterial_error.printStackTrace();
				return null;
		}
    }
	
	public Transaction Search_transaction_by_id(Integer transaction_id) {
		try {
			String SQL_code = "SELECT * FROM bank_transaction WHERE transaction_id LIKE ?";
			PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setInt(1, transaction_id);
			Transaction transaction = new Transaction();
			ResultSet result_set = template.executeQuery();
			transaction.setTransaction_id(result_set.getInt("transaction_id"));
			Biomaterial biomaterial = Search_biomaterial_by_id(result_set.getInt("biomaterial_id"));
			transaction.setBiomaterial(biomaterial);
			transaction.setTransaction_date(result_set.getDate("transaction_date"));
			transaction.setGain(result_set.getFloat("gain"));
			transaction.setUnits(result_set.getInt("units"));
			Client client = Search_client_by_id(result_set.getInt("client_id"));
			transaction.setClient(client);
            template.close();
			return transaction;
		} catch (SQLException search_transaction_error) {
			search_transaction_error.printStackTrace();
			return null;
	}
	}
	
	// -----> LIST METHODS <-----
	
	// Selects all clients objects with the same client_id from the data base and returns them
	public List<Transaction> Search_stored_transactions(Client client) {
		try {
			String SQL_code = "SELECT * FROM bank_transaction WHERE client_id LIKE ?";
			PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setInt(1, client.getClient_id());
			List<Transaction> transactions_list = new LinkedList<Transaction>();
			ResultSet result_set = template.executeQuery();
			while (result_set.next()) {
				Transaction transaction = new Transaction();
				transaction.setClient(client);
				transaction.setGain(result_set.getFloat("gain"));
				Biomaterial biomaterial = Search_biomaterial_by_id(result_set.getInt("biomaterial_id"));
				transaction.setBiomaterial(biomaterial);
				transaction.setTransaction_date(result_set.getDate("transaction_date"));
				transaction.setTransaction_id(result_set.getInt("transaction_id"));
				transaction.setUnits(result_set.getInt("units"));
				transactions_list.add(transaction);
			}
			template.close();
			return transactions_list;
		} catch (SQLException search_transaction_error) {
			search_transaction_error.printStackTrace();
			return null;
		}
	}

	// List all users returning a linkedList with all of them
	public List<User> List_all_users() {
		try {
			Statement statement = this.sqlite_connection.createStatement();
			String SQL_code = "SELECT * FROM user";
			List<User> users_list = new LinkedList<User>();
			ResultSet result_set = statement.executeQuery(SQL_code);
			while (result_set.next()) {
				User user = new User();
				user.setUserId(result_set.getInt("user_id"));
				user.setPassword(result_set.getString("password"));
				user.setUserName(result_set.getString("user_name"));
				users_list.add(user);
			}
			statement.close();
			return users_list;
		} catch (SQLException list_users_error) {
			list_users_error.printStackTrace();
			return null;
		}
	}
	
	// List all clients returning a linkedList with all of them
	public List<Client> List_all_clients() {
		try {
			Statement statement = this.sqlite_connection.createStatement();
			String SQL_code = "SELECT * FROM client";
			List<Client> clients_list = new LinkedList<Client>();
			ResultSet result_set = statement.executeQuery(SQL_code);
			while (result_set.next()) {
				Client client = new Client();
				client.setClient_id(result_set.getInt("client_id"));
				client.setName(result_set.getString("name"));
				client.setResponsible(result_set.getString("responsible"));
				client.setBank_account(result_set.getString("bank_account"));
				client.setTelephone(result_set.getInt("telephone"));
				User user = Search_user_by_id(result_set.getInt("user_id"));
				client.setUser(user);
				clients_list.add(client);
			}
			statement.close();
			return clients_list;
		} catch (SQLException list_clients_error) {
			list_clients_error.printStackTrace();
			return null;
		}
	}

	// List all clients returning a linkedList with all of them
	public List<Transaction> List_all_transactions() {
		try {
			Statement statement = this.sqlite_connection.createStatement();
			String SQL_code = "SELECT * FROM bank_transaction";
			List<Transaction> transactions_list = new LinkedList<Transaction>();
			ResultSet result_set = statement.executeQuery(SQL_code);
			while (result_set.next()) {
				Transaction transaction = new Transaction();
				Client client = Search_client_by_id(result_set.getInt("client_id"));
				transaction.setClient(client);
				transaction.setGain(result_set.getFloat("gain"));
				Biomaterial biomaterial = Search_biomaterial_by_id(result_set.getInt("biomaterial_id"));
				transaction.setBiomaterial(biomaterial);
				transaction.setTransaction_date(result_set.getDate("transaction_date"));
				transaction.setTransaction_id(result_set.getInt("transaction_id"));
				transaction.setUnits(result_set.getInt("units"));
				transactions_list.add(transaction);
			}
			statement.close();
			return transactions_list;
		} catch (SQLException list_transactions_error) {
			list_transactions_error.printStackTrace();
			return null;
		}
	}
	
	// List all categories returning a linkedList with all of them
	public List<Category> List_all_categories() {
		try {
			Statement statement = this.sqlite_connection.createStatement();
			String SQL_code = "SELECT * FROM category";
			List<Category> categories_list = new LinkedList<Category>();
			ResultSet result_set = statement.executeQuery(SQL_code);
			while (result_set.next()) {
                Category category = new Category();
                category.setCategory_name(result_set.getString("category_name"));
                category.setMaximum(result_set.getInt("max"));
                category.setMinimum(result_set.getInt("min"));
                category.setPenalization(result_set.getInt("penalization"));
                category.setCategory_id(result_set.getInt("category_id"));
				categories_list.add(category);
			}
			statement.close();
			return categories_list;
		} catch (SQLException list_categories_error) {
			list_categories_error.printStackTrace();
			return null;
		}
	}
	
	// List all biomaterials returning a linkedList with all of them
	public List<Biomaterial> List_all_biomaterials() {
		try {
			Statement statement = this.sqlite_connection.createStatement();
			String SQL_code = "SELECT * FROM biomaterial";
			List<Biomaterial> biomaterials_list = new LinkedList<Biomaterial>();
			ResultSet result_set = statement.executeQuery(SQL_code);
			while (result_set.next()) {
				Biomaterial biomaterial = new Biomaterial();
				biomaterial.setName_product(result_set.getString("name_product"));
				biomaterial.setAvailable_units(result_set.getInt("available_units"));
				biomaterial.setBiomaterial_id(result_set.getInt("biomaterial_id"));
				biomaterial.setExpiration_date(result_set.getDate("expiration_date"));
				/*Maintenance maintenance = Search_maintenance_by_id(result_set.getInt("maintenance_id"));
				biomaterial.setMaintenance(maintenance);
				Utility utility = Search_utility_by_id(result_set.getInt("utility_id"));
				biomaterial.setUtility(utility);*/
				biomaterial.setPrice_unit(result_set.getInt("price_unit"));
				
				biomaterials_list.add(biomaterial);
			}
			statement.close();
			return biomaterials_list;
		} catch (SQLException list_biomeatrials_error) {
			list_biomeatrials_error.printStackTrace();
			return null;
		}
	}
	
	// List all utilities returning a linkedList with all of them
		public List<Utility> List_all_utilities() {
			try {
				Statement statement = this.sqlite_connection.createStatement();
				String SQL_code = "SELECT * FROM utility";
				List<Utility> utilities_list = new LinkedList<Utility>();
				ResultSet result_set = statement.executeQuery(SQL_code);
				while (result_set.next()) {
					Utility utility = new Utility();
					utility.setFlexibility(result_set.getString("flexibility"));
	                utility.setHeat_cold(result_set.getString("heat_cold"));
	                utility.setPressure(result_set.getFloat("pressure"));
	                utility.setResistance(result_set.getString("resistance"));
	                utility.setStrength(result_set.getFloat("strength"));
	                utility.setUtility_id(result_set.getInt("utility_id"));
					utilities_list.add(utility);
				}
				statement.close();
				return utilities_list;
			} catch (SQLException list_utilities_error) {
				list_utilities_error.printStackTrace();
				return null;
			}
		}
		
		// List all maintenances returning a linkedList with all of them
				public List<Maintenance> List_all_maintenances() {
					try {
						Statement statement = this.sqlite_connection.createStatement();
						String SQL_code = "SELECT * FROM maintenance";
						List<Maintenance> maintenance_list = new LinkedList<Maintenance>();
						ResultSet result_set = statement.executeQuery(SQL_code);
						while (result_set.next()) {
							Maintenance maintenance = new Maintenance();
							maintenance.setCompatibility(result_set.getString("compatibility"));
			                maintenance.setHumidity(result_set.getInt("humidity"));
			                maintenance.setLight(result_set.getString("light"));
			                maintenance.setO2_supply(result_set.getString("o2_supply"));
			                maintenance.setOthers(result_set.getString("others"));
			                maintenance.setPressure(result_set.getFloat("pressure"));
			                maintenance.setTemperature(result_set.getFloat("temperature"));
			                maintenance.setManteinance_id(result_set.getInt("maintenance_id"));
			                maintenance_list.add(maintenance);
						}
						statement.close();
						return maintenance_list;
					} catch (SQLException list_maintenances_error) {
						list_maintenances_error.printStackTrace();
						return null;
					}
				}
	
	// List all directors returning a linkedList with all of them
	public List<Director> List_all_directors() {
		try {
			Statement statement = this.sqlite_connection.createStatement();
			String SQL_code = "SELECT * FROM director";
			List<Director> directors_list = new LinkedList<Director>();
			ResultSet result_set = statement.executeQuery(SQL_code);
			while (result_set.next()) {
				Director director = new Director();
				director.setDirector_id(result_set.getInt("director_id"));
				director.setDirector_name(result_set.getString("name"));
				director.setEmail(result_set.getString("email"));
				director.setTelephone(result_set.getInt("telephone"));
				User user = Search_user_by_id(result_set.getInt("user_id"));
				director.setUser(user);
			    directors_list.add(director);
			}
			statement.close();
			return directors_list;
		} catch (SQLException list_directors_error) {
			list_directors_error.printStackTrace();
			return null;
		}
	}
	
	// List all workers returning a linkedList with all of them
	public List<Worker> List_all_workers() {
		try {
			Statement statement = this.sqlite_connection.createStatement();
			String SQL_code = "SELECT * FROM worker";
			List<Worker> workers_list = new LinkedList<Worker>();
			ResultSet result_set = statement.executeQuery(SQL_code);
			while (result_set.next()) {
				Worker worker = new Worker();
				worker.setWorker_id(result_set.getInt("worker_id"));
				worker.setWorker_name(result_set.getString("name"));
				worker.setTelephone(result_set.getInt("telephone"));
				worker.setEmail(result_set.getString("email"));
				User user = Search_user_by_id(result_set.getInt("user_id"));
				worker.setUser(user);
			    workers_list.add(worker);
			}
			statement.close();
			return workers_list;
		} catch (SQLException list_workers_error) {
			list_workers_error.printStackTrace();
			return null;
		}
	}

	// -----> DELETE METHODS <-----

	// Delete a user from user table with the given user_id
	public boolean Delete_stored_user(Integer user_id) {
		try {
			String SQL_code = "DELETE FROM user WHERE user_id = ?;";
			PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setInt(1, user_id);
			template.executeUpdate();
			template.close();
			return true;
		} catch (SQLException delete_user_error) {
			delete_user_error.printStackTrace();
			return false;
		}
	}
	
	public boolean Delete_stored_category(Category category) {
		try {
			String SQL_code = "DELETE FROM category WHERE category_id = ?;";
			PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setInt(1, category.getCategory_id());
			template.executeUpdate();
			template.close();
			return true;
		} catch (SQLException delete_client_error) {
			delete_client_error.printStackTrace();
			return false;
		}
	}
	
	
	public boolean Delete_transaction_from_client(Transaction transaction) {
		try {
			String SQL_code = "DELETE FROM bank_transaction WHERE transaction_id = ?;";
			PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setInt(1, transaction.getTransaction_id());
			template.executeUpdate();
			template.close();
			return true;
			
		} catch (SQLException delete_transaction_error) {
			delete_transaction_error.printStackTrace();
			return false;
		}
	
	}
	
	public boolean Delete_biomaterial_by_id(Integer biomat_id) {
		try {
			String SQL_code = "DELETE FROM biomaterial WHERE biomaterial_id = ?;";
			PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setInt(1, biomat_id);
			template.executeUpdate();
			template.close();
			return true;
			
		} catch (SQLException delete_biomaterial_error) {
			delete_biomaterial_error.printStackTrace();
			return false;
		}
	}
	
	// -----> CLOSE CONNECTION METHOD <-----

	// Close connection with the data base method
	public boolean Close_connection() {
		try {
			this.sqlite_connection.close();
			return true;
		} catch (SQLException close_connection_error) {
			close_connection_error.printStackTrace();
			return false;
		}
	}



}
