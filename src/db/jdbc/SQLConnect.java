package db.jdbc;
import java.sql.*;

public class SQLConnect {

	public static void main(String args[]) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection sqlite_connection = DriverManager.getConnection("jdbc:sqlite:./db/biomat.db"); 
			sqlite_connection.createStatement().execute("PRAGMA foreign_keys=ON");
			sqlite_connection.close();
		} catch(Exception connection_error) {
			
		}
	}
}
