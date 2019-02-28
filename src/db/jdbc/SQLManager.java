package db.jdbc;

import java.sql.*;

import db.pojos.*;

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

	public boolean Create_tables() {
		boolean connection_status = Stablish_connection();
		if (connection_status == true) {
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
				String table_3 = "CREATE TABLE client " + "(client_id INTEGER PRIMARY KEY AUTOINCREMENT, " + " responsible TEXT, "
						+ " name TEXT NOT NULL, " + " bank_account TEXT NOT NULL UNIQUE, "
						+ " telephone INTEGER NOT NULL UNIQUE)";
				statement_3.execute(table_3);
				statement_3.close();

				Statement statement_4 = this.sqlite_connection.createStatement();
				String table_4 = "CREATE TABLE bank_transaction " + "(transaction_id INTEGER NOT NULL, "
						+ " client_id INTEGER NOT NULL, " + " gain REAL NOT NULL, "
						+ " units INTEGER NOT NULL default 1, " + " transaction_date DATETIME NOT NULL, "
						+ " product_name TEXT NOT NULL, " + " PRIMARY KEY (transaction_id, product_name), "
						+ " FOREIGN KEY (client_id) REFERENCES client (client_id) ON UPDATE RESTRICT ON DELETE CASCADE)";
				statement_4.execute(table_4);
				statement_4.close();

				Statement statement_5 = this.sqlite_connection.createStatement();
				String table_5 = "CREATE TABLE utility " + "(utility_id INTEGER PRIMARY KEY AUTOINCREMENT, "
						+ " heat_cold TEXT default NULL, " + " flexibility TEXT default 'no', "
						+ " resistance TEXT default 'yes', " + " pressure REAL NOT NULL default 1, "
						+ " strength REAL NOT NULL)";
				statement_5.execute(table_5);
				statement_5.close();

				Statement statement_6 = this.sqlite_connection.createStatement();
				String table_6 = "CREATE TABLE maintenance " + "(maintenance_id INTEGER PRIMARY KEY AUTOINCREMENT, "
						+ " pressure REAL NOT NULL default 1, " + " humidity INT NOT NULL default 50, "
						+ " O2_supply TEXT default 'no', " + " light TEXT default 'no', "
						+ " temperature REAL NOT NULL default 20, " + " compatibility TEXT, "
						+ " others TEXT default NULL)";
				statement_6.execute(table_6);
				statement_6.close();
				
				Statement statement_7 = this.sqlite_connection.createStatement();
				String table_7 = "CREATE TABLE biomaterial " + "(biomaterial_id INTEGER PRIMARY KEY AUTOINCREMENT, "
						+ " utility_id INTEGER REFERENCES utility (utility_id), "
						+ " name_product TEXT NOT NULL REFERENCES bank_transaction(product_name), "
						+ " price_unit INTEGER NULL default 1, " + " available_units INTEGER NOT NULL, "
						+ " expiration_date DATETIME, "
						+ " maintenance_id INTEGER REFERENCES maintenance(maintenance_id) ON UPDATE RESTRICT ON DELETE CASCADE)";
				statement_7.execute(table_7);
				statement_7.close();
				return true;
			} catch (SQLException tables_error) {
				tables_error.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	// Benefits(others, percentage, min_amount, extra_units)
	public boolean Inset_new_benefits(Benefits benefits) {
		try {
			Statement statement = this.sqlite_connection.createStatement();
			String table = "INSERT INTO benefits(others, percentage, min_amount, extra_units) "
					+ "VALUES (?,?,?,?);";
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
			String table = "INSERT INTO category(category_name, penalization, max, min) "
					+ "VALUES (?,?,?,?);";
			PreparedStatement template = this.sqlite_connection.prepareStatement(table);
			template.setString(1, category.getCategory_name());
			template.setFloat(2, category.getPenalization());
			template.setInt(3, category.getMaximum());
			template.setInt(4, category.getMinimum());
			template.executeUpdate();
			statement.close();
			return true;
		}catch (SQLException new_category_error) {
			new_category_error.printStackTrace();
			return false;
		}
	}

	// Client(responsible, name, bank_account, telephone) 
	public boolean Inset_new_client(Client client) {
		try {
			Statement statement = this.sqlite_connection.createStatement();
			String table = "INSERT INTO client (responsible, name, bank_account, telephone) "
					+ "VALUES (?,?,?,?);";
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
		} catch(SQLException new_utility_error) {
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
		}catch(SQLException new_maintenance_error) {
			new_maintenance_error.printStackTrace();
			return false;
		}
	}
	
	// Bank_transaction(client_id, gain, units, transaction_date, product_name)
	public boolean Insert_new_transaction(Transaction transaction) {
	    try {
	    	Statement statement = this.sqlite_connection.createStatement();
	    	String table = "INSERT INTO bank_transaction(client_id, gain, units, transaction_date, product_name) "
	    			+ "VALUES (?,?,?,?,?);";
	    	PreparedStatement template = this.sqlite_connection.prepareStatement(table);
	    	template.setInt(1, transaction.getClient_id());
	    	template.setFloat(2, transaction.getGain());
	    	template.setInt(3, transaction.getUnits());
	    	template.setDate(4, transaction.getTransaction_date());
	    	template.setString(5, transaction.getProduct_name());
	    	template.executeUpdate();
	    	statement.close();
	    	return true;
	    } catch(SQLException new_transaction_error) {
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
	
	// Selects a client object based on their client_name from data base and returns it
	public Client Search_stored_client(String name) {
		try {
			Statement statement = this.sqlite_connection.createStatement();
			String SQL_code = "SELECT * FROM client WHERE name LIKE ?";
			PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setString(1, name);
			Client client = new Client();
			ResultSet result_set = template.executeQuery();
			while(result_set.next()) {
			   client.setId(result_set.getInt("client_id"));
			   client.setName(result_set.getString("name"));
			   client.setResponsible(result_set.getString("responsible"));
			   client.setBank_account(result_set.getString("bank_account"));
			   client.setTelephone(result_set.getInt("telephone"));
			}
			statement.close();
			return client;
		} catch (SQLException search_client_error) {
			search_client_error.printStackTrace();
			return null;
		}
	}
	
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
