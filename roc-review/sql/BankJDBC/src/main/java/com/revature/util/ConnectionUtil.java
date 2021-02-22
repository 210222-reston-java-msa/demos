package com.revature.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	// we import Connection from java.sql
	public static Connection getConnection() {
		
		/*
		 * Thus URL includes the driver we're using which is from Postgres
		 * as well as the computer (localhost) and the PORT (5432) 
		 */
		
		// This is not secure!!! It's better to create a separate file called application.properties with
		// these connection credentials
		
//		String url = "jdbc:postgresql://localhost:5432/postgres";
//		String username = "postgres";
//		String password = "postgres";
		
		Connection conn = null;
		Properties prop = new Properties();
		
		// The properties object allows me to read characters (like my credentials) from another file.
		try {
			prop.load(new FileReader("C:\\Users\\SophieGavrila\\Desktop\\demos\\week2\\BankJDBC\\src\\main\\resources\\application.properties"));
			
			// we use the DriverManager class from JDBC to input our credentials and
			// establish a connection with the db.
			conn = DriverManager.getConnection(
					prop.getProperty("url"), 
					prop.getProperty("username"), 
					prop.getProperty("password"));

		} catch (FileNotFoundException e) {
			// later we will change this to log.warn("something something");
			System.out.println("Cannot locate application.properties file!");
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Incorrect credentials to the DB");
			e.printStackTrace();
		}
		System.out.println("Connection created!");
		return conn;
	}

}
