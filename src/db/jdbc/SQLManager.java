package db.jdbc;

import java.sql.*;

import db.pojos.Client;
import db.pojos.Transaction;

public class SQLManager {

	private Connection sqlite_connection;

	public SQLManager() {
		/*
		 * Empty constructor that will allow to create a Manager object in order to use
		 * the methods
		 */
	}

	//Connection route: 
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
				Statement statement_1 = sqlite_connection.createStatement();
				String table_1 = " CREATE TABLE benefits " + "(benefits_id INTEGER PRIMARY KEY , "
						+ " type TEXT default 0 )";
				statement_1.execute(table_1);
				statement_1.close();

				Statement statement_2 = sqlite_connection.createStatement();
				String table_2 = "CREATE TABLE category " + "(category_id INTEGER REFERENCES beneficts(beneficts_id), "
						+ " category_name TEXT NOT NULL, " + " penalization INTEGER default NULL, "
						// Money interval//
						+ " max INTEGER NOT NULL, " + " min INTEGER NOT NULL, "
						+ " percentage REAL NOT NULL default 0)";
				statement_2.execute(table_2);
				statement_2.close();

				Statement statement_3 = sqlite_connection.createStatement();
				String table_3 = "CREATE TABLE client " + "(client_id INTEGER PRIMARY KEY AUTOINCREMENT, " + " responsible TEXT, "
						+ " name TEXT NOT NULL, " + " bank_account TEXT NOT NULL UNIQUE, "
						+ " telephone INTEGER NOT NULL UNIQUE)";
				statement_3.execute(table_3);
				statement_3.close();

				Statement statement_4 = sqlite_connection.createStatement();
				String table_4 = "CREATE TABLE bank_transaction " + "(transaction_id INTEGER NOT NULL, "
						+ " client_id INTEGER NOT NULL, " + " gain REAL NOT NULL, "
						+ " units INTEGER NOT NULL default 1, " + " transaction_date DATETIME NOT NULL, "
						+ " product_name TEXT NOT NULL, " + " PRIMARY KEY (transaction_id, product_name), "
						+ " FOREIGN KEY (client_id) REFERENCES client (client_id) ON UPDATE RESTRICT ON DELETE CASCADE)";
				statement_4.execute(table_4);
				statement_4.close();

				Statement statement_5 = sqlite_connection.createStatement();
				String table_5 = "CREATE TABLE utility " + "(utility_id INTEGER PRIMARY KEY AUTOINCREMENT, "
						+ " heat_cold TEXT default NULL, " + " flexibility TEXT default 'no', "
						+ " resistance TEXT default 'yes', " + " pressure REAL NOT NULL default 1, "
						+ " strength REAL NOT NULL)";
				statement_5.execute(table_5);
				statement_5.close();

				Statement statement_6 = sqlite_connection.createStatement();
				String table_6 = "CREATE TABLE manteinance " + "(manteinance_id INTEGER PRIMARY KEY AUTOINCREMENT, "
						+ " pressure REAL NOT NULL default 1, " + " humidity INT NOT NULL default 50, "
						+ " O2_supply TEXT default 'no', " + " light TEXT default 'no', "
						+ " temperature REAL NOT NULL default 20, " + " compatibility TEXT, "
						+ " others TEXT default NULL)";
				statement_6.execute(table_6);
				statement_6.close();

				Statement statement_7 = sqlite_connection.createStatement();
				String table_7 = "CREATE TABLE biomaterial " + "(biomaterial_id INTEGER PRIMARY KEY AUTOINCREMENT, "
						+ " utility_id INTEGER REFERENCES utility (utility_id), "
						+ " name_product TEXT NOT NULL REFERENCES bank_transaction(product_name), "
						+ " price_unit INTEGER NULL default 1, " + " avalible_units INTEGER NOT NULL, "
						+ " expiration_date DATETIME, "
						+ " mantein_id INTEGER REFERENCES manteinance(manteinance_id) ON UPDATE RESTRICT ON DELETE CASCADE)";
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

	// Client(id, name, telephone, bank_account, responsible) 
	public boolean Inset_new_client(Client client) {
		try {
			Statement statement = this.sqlite_connection.createStatement();
			String table = "INSERT INTO client(name, telephone, bank_account, responsible)"
					+ "VALUES ('" + client.getName() + "', '" + client.getTelephone() + "', '" + client.getBank_account() 
					+ "', '" + client.getResponsible() + "');";
			statement.executeUpdate(table);
			statement.close();
			return true;
		} catch (SQLException new_client_error) {
			new_client_error.printStackTrace();
			return false;
		}
	}
	
	//Transaction(transaction_id, gain, client_id, units, product_name, transaction_date)
	public boolean Insert_new_transaction(Transaction transaction) {
	    try {
	    	Statement statement = this.sqlite_connection.createStatement();
	    	String table = "INSERT INTO transaction()";
	    	statement.executeUpdate(table);
	    	statement.close();
	    	return true;
	    } catch(SQLException new_transaction_error) {
	    	new_transaction_error.printStackTrace();
	        return false;
	    }
	}
	
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
