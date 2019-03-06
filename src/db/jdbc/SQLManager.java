package db.jdbc;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import db.pojos.Benefits;
import db.pojos.Biomaterial;
import db.pojos.Category;
import db.pojos.Client;
import db.pojos.Maintenance;
import db.pojos.Transaction;
import db.pojos.Utility;

public class SQLManager {

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
			Statement statement_1 = this.sqlite_connection.createStatement();
			String table_1 = " CREATE TABLE benefits " + "(benefits_id INTEGER PRIMARY KEY, "
					+ " others TEXT default NULL, " + " percentage REAL NOT NULL default 0, "
					+ " min_amount INTEGER NOT NULL default 0," + " extra_units INTEGER NOT NULL default 0)";
			statement_1.execute(table_1);
			statement_1.close();

			Statement statement_2 = this.sqlite_connection.createStatement();
			String table_2 = "CREATE TABLE category " + "(category_id INTEGER REFERENCES benefits(benefits_id), "
					+ " category_name TEXT NOT NULL, " + " penalization INTEGER default NULL, "
					// Money interval//
					+ " max INTEGER NOT NULL, " + " min INTEGER NOT NULL)";
			statement_2.execute(table_2);
			statement_2.close();

			Statement statement_3 = this.sqlite_connection.createStatement();
			String table_3 = "CREATE TABLE client " + "(client_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ " responsible TEXT, " + " name TEXT NOT NULL, " + " bank_account TEXT NOT NULL UNIQUE, "
					+ " telephone INTEGER NOT NULL UNIQUE)";
			statement_3.execute(table_3);
			statement_3.close();

			Statement statement_4 = this.sqlite_connection.createStatement();
			String table_4 = "CREATE TABLE utility " + "(utility_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ " heat_cold TEXT default NULL, " + " flexibility TEXT default 'no', "
					+ " resistance TEXT default 'yes', " + " pressure REAL NOT NULL default 1, "
					+ " strength REAL NOT NULL)";
			statement_4.execute(table_4);
			statement_4.close();

			Statement statement_5 = this.sqlite_connection.createStatement();
			String table_5 = "CREATE TABLE maintenance " + "(maintenance_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ " pressure REAL NOT NULL default 1, " + " humidity INT NOT NULL default 50, "
					+ " O2_supply TEXT default 'no', " + " light TEXT default 'no', "
					+ " temperature REAL NOT NULL default 20, " + " compatibility TEXT, "
					+ " others TEXT default NULL)";
			statement_5.execute(table_5);
			statement_5.close();

			Statement statement_6 = this.sqlite_connection.createStatement();
			String table_6 = "CREATE TABLE biomaterial " + "(biomaterial_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ " utility_id INTEGER REFERENCES utility (utility_id), "
					+ " name_product TEXT NOT NULL, "
					+ " price_unit INTEGER NULL default 1, " + " available_units INTEGER NOT NULL, "
					+ " expiration_date DATETIME, "
					+ " maintenance_id INTEGER REFERENCES maintenance(maintenance_id) ON UPDATE RESTRICT ON DELETE CASCADE)";
			statement_6.execute(table_6);
			statement_6.close();

			Statement statement_7 = this.sqlite_connection.createStatement();
			String table_7 = "CREATE TABLE bank_transaction " + "(transaction_id INTEGER NOT NULL, "
					+ " client_id INTEGER NOT NULL, " + " gain REAL NOT NULL, " + " units INTEGER NOT NULL default 1, "
					+ " transaction_date DATETIME NOT NULL, " + " product_id INTEGER NOT NULL REFERENCES biomaterial(biomaterial_id), "
					+ " PRIMARY KEY (transaction_id, product_id), "
					+ " FOREIGN KEY (client_id) REFERENCES client (client_id) ON UPDATE RESTRICT ON DELETE CASCADE)";
			statement_7.execute(table_7);
			statement_7.close();
			
			return true;
		} catch (SQLException tables_error) {
			tables_error.printStackTrace();
			return false;
		}
	}
	
	// -----> CHECK ALREADY EXISTING TABLES METHOD <-----

	// Checks if all the tables are already created in biomat.db in order to avoid executing the method Create_tables 
	public boolean Check_if_tables_exist() {
		
		String[] tables_array = new String[]{"client", "bank_transaction", "utility",
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

	// Benefits(others, percentage, min_amount, extra_units)
	public boolean Inset_new_benefits(Benefits benefits) {
		try {
			Statement statement = this.sqlite_connection.createStatement();
			String table = "INSERT INTO benefits(others, percentage, min_amount, extra_units) " + "VALUES (?,?,?,?);";
			PreparedStatement template = this.sqlite_connection.prepareStatement(table);
			template.setString(1, benefits.getOthers());
			template.setFloat(2, benefits.getPercentage());
			template.setInt(3, benefits.getMin_amount());
			template.setInt(4, benefits.getExtra_units());
			template.executeUpdate();
			statement.close();
			return true;
		} catch (SQLException new_benefits_error) {
			new_benefits_error.printStackTrace();
			return false;
		}
	}

	// Category(category_name, penalization, maximum, minimum)
	public boolean Insert_new_category(Category category) {
		try {
			Statement statement = this.sqlite_connection.createStatement();
			String table = "INSERT INTO category(category_name, penalization, max, min) " + "VALUES (?,?,?,?);";
			PreparedStatement template = this.sqlite_connection.prepareStatement(table);
			template.setString(1, category.getCategory_name());
			template.setFloat(2, category.getPenalization());
			template.setInt(3, category.getMaximum());
			template.setInt(4, category.getMinimum());
			template.executeUpdate();
			statement.close();
			return true;
		} catch (SQLException new_category_error) {
			new_category_error.printStackTrace();
			return false;
		}
	}

	// Client(responsible, name, bank_account, telephone)
	public boolean Inset_new_client(Client client) {
		try {
			Statement statement = this.sqlite_connection.createStatement();
			String table = "INSERT INTO client (responsible, name, bank_account, telephone) " + "VALUES (?,?,?,?);";
			PreparedStatement template = this.sqlite_connection.prepareStatement(table);
			template.setString(1, client.getResponsible());
			template.setString(2, client.getName());
			template.setString(3, client.getBank_account());
			template.setInt(4, client.getTelephone());
			template.executeUpdate();
			statement.close();
			return true;
		} catch (SQLException new_client_error) {
			new_client_error.printStackTrace();
			return false;
		}
	}

	// Utility(heat_cold, flexibility, resistance, pressure, strength)
	public boolean Isert_new_utility(Utility utility) {
		try {
			Statement statement = this.sqlite_connection.createStatement();
			String table = "INSERT INTO utility(heat_cold, flexibility, resistance, pressure, strength) "
					+ "VALUES (?,?,?,?,?);";
			PreparedStatement template = this.sqlite_connection.prepareStatement(table);
			template.setString(1, utility.getHeat_cold());
			template.setString(2, utility.getFlexibility());
			template.setString(3, utility.getResistance());
			template.setFloat(4, utility.getPressure());
			template.setFloat(5, utility.getStrength());
			template.executeUpdate();
			statement.close();
			return true;
		} catch (SQLException new_utility_error) {
			new_utility_error.printStackTrace();
			return false;
		}
	}

	// Maintenance(pressure, humidity, O2_supply, light, temperature, compatibility, others)
	public boolean Insert_new_maintenance(Maintenance maintenance) {
		try {
			Statement statement = this.sqlite_connection.createStatement();
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
			statement.close();
			return true;
		} catch (SQLException new_maintenance_error) {
			new_maintenance_error.printStackTrace();
			return false;
		}
	}

	// Bank_transaction(client_id, gain, units, transaction_date, product_id)
	public boolean Insert_new_transaction(Transaction transaction) {
		try {
			Statement statement = this.sqlite_connection.createStatement();
			String table = "INSERT INTO bank_transaction(transaction_id, client_id, gain, units, transaction_date, product_id) "
					+ "VALUES (?,?,?,?,?,?);";
			PreparedStatement template = this.sqlite_connection.prepareStatement(table);
			template.setInt(1, transaction.getTransaction_id());
			template.setInt(2, transaction.getClient_id());
			template.setFloat(3, transaction.getGain());
			template.setInt(4, transaction.getUnits());
			template.setDate(5, transaction.getTransaction_date());
			template.setInt(6, transaction.getProduct_id());
			template.executeUpdate();
			statement.close();
			return true;
		} catch (SQLException new_transaction_error) {
			new_transaction_error.printStackTrace();
			return false;
		}
	}

	// Biomaterial(utility_id, maintenance_id, name_product, price_unit, available_units, expiration_date)
	public boolean Insert_new_biomaterial(Biomaterial biomaterial) {
		try {
			Statement statement = this.sqlite_connection.createStatement();
			String table = "INSERT INTO biomaterial(utility_id, maintenance_id, name_product, price_unit, available_units, expiration_date) "
					+ "VALUES (?,?,?,?,?,?);";
			PreparedStatement template = this.sqlite_connection.prepareStatement(table);
			template.setInt(1, biomaterial.getUtility_id());
			template.setInt(2, biomaterial.getMaintenance_id());
			template.setString(3, biomaterial.getName_product());
			template.setFloat(4, biomaterial.getPrice_unit());
			template.setInt(5, biomaterial.getAvailable_units());
			template.setDate(6, biomaterial.getExpiration_date());
			template.executeUpdate();
			statement.close();
			return true;
		} catch (SQLException new_biomaterial_error) {
			new_biomaterial_error.printStackTrace();
			return false;
		}
	}
	
	// -----> UPDATE METHODS <-----
	
	// Updates the information of a Client(responsible, name, bank_account, telephone)
	public boolean Update_client_info(Client client) {
		try {
			Statement statement = this.sqlite_connection.createStatement();
			String SQL_code = "UPDATE client SET responsible = ?, name = ?, bank_account = ?, telephone = ? WHERE client_id = ?";
			PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setString(1, client.getResponsible());
			template.setString(2, client.getName());
			template.setString(3, client.getName());
			template.setInt(4, client.getTelephone());
			template.setInt(5, client.getClient_id());
			template.executeUpdate();
			statement.close();
			return true;
		} catch (SQLException update_client_error) {
			update_client_error.printStackTrace();
			return false;
		}
	}

	// -----> SEARCH METHODS <-----

	// Selects all clients objects with the same client_name from the data base and returns them
	public List<Client> Search_stored_clients(String name) {
		try {
			Statement statement = this.sqlite_connection.createStatement();
			String SQL_code = "SELECT * FROM client WHERE name LIKE ?";
			PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setString(1, name);
			List<Client> clients_list = new LinkedList<Client>();
			ResultSet result_set = template.executeQuery();
			while (result_set.next()) {
				Client client = new Client();
				client.setClient_id(result_set.getInt("client_id"));
				client.setName(result_set.getString("name"));
				client.setResponsible(result_set.getString("responsible"));
				client.setBank_account(result_set.getString("bank_account"));
				client.setTelephone(result_set.getInt("telephone"));
				clients_list.add(client);
			}
			statement.close();
			return clients_list;
		} catch (SQLException search_client_error) {
			search_client_error.printStackTrace();
			return null;
		}
	}

	// Selects all clients objects with the same client_id from the data base and returns them
	public List<Transaction> Search_stored_transactions(Client client) {
		try {
			Statement statement = this.sqlite_connection.createStatement();
			String SQL_code = "SELECT * FROM bank_transaction WHERE client_id LIKE ?";
			PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setInt(1, client.getClient_id());
			List<Transaction> transactions_list = new LinkedList<Transaction>();
			ResultSet result_set = template.executeQuery();
			while (result_set.next()) {
				Transaction transaction = new Transaction();
				transaction.setClient_id(result_set.getInt("client_id"));
				transaction.setGain(result_set.getFloat("gain"));
				transaction.setProduct_id(result_set.getInt("product_id"));
				transaction.setTransaction_date(result_set.getDate("transaction_date"));
				transaction.setTransaction_id(result_set.getInt("transaction_id"));
				transaction.setUnits(result_set.getInt("units"));
				transactions_list.add(transaction);
			}
			statement.close();
			return transactions_list;
		} catch (SQLException search_transaction_error) {
			search_transaction_error.printStackTrace();
			return null;
		}
	}

	// -----> LIST METHODS <-----

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
				transaction.setClient_id(result_set.getInt("client_id"));
				transaction.setGain(result_set.getFloat("gain"));
				transaction.setProduct_id(result_set.getInt("product_id"));
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

	// -----> DELETE METHODS <-----

	// Deletes a client from client table with the given client_id
	public boolean Delete_stored_client(Integer client_id) {
		try {
			Statement statement = this.sqlite_connection.createStatement();
			String SQL_code = "DELETE FROM client WHERE client_id = ?;";
			PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setInt(1, client_id);
			template.executeUpdate();
			statement.close();
			return true;
		} catch (SQLException delete_client_error) {
			delete_client_error.printStackTrace();
			return false;
		}
	}
	
	// Deletes a transaction from transaction table with the given transaction_id
	public boolean Delete_stored_transaction(Integer transaction_id) {
		try {
			Statement statement = this.sqlite_connection.createStatement();
			String SQL_code = "DELETE FROM bank_transaction WHERE transaction_id = ?;";
			PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setInt(1, transaction_id);
			template.executeUpdate();
			statement.close();
			return true;
		} catch (SQLException delete_transaction_error) {
			delete_transaction_error.printStackTrace();
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
