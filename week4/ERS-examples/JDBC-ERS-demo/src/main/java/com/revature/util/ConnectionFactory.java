package com.revature.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionFactory {
	
	private static Logger logger = LogManager.getLogger(ConnectionFactory.class);
	public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Properties properties = new Properties();
		// search for files in the current project
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Connection connection = null;
		try {
			properties.load(loader.getResourceAsStream("connection.properties"));
			String url = properties.getProperty("url");
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");
			try {
				connection = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				logger.warn("Unable to obtain connection to database", e);
			}
		} catch (IOException e1) {
		}
		return connection;
	}
}


//public class ConnectionFactory {
//
////	private static Logger logger = LogManager.getLogger(ConnectionFactory.class);
//	public static Connection getConnection() {
//		try {
//			Class.forName("org.postgresql.Driver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		
//		Properties props = new Properties();
//		// search for files in the current project
//		ClassLoader loader = Thread.currentThread().getContextClassLoader();
//		Connection conn = null;
//		
//		try {
//			props.load(loader.getResourceAsStream("connection.properties"));
//			String url = props.getProperty("url");
//			String username = props.getProperty("username");
//			String password = props.getProperty("password");
//			try {
//				conn = DriverManager.getConnection(url, username, password);
//			} catch (SQLException e) {
////				logger.warn("Unable to obtain connection to database", e);
//			}
//		} catch (IOException e1) {
//		}
//		return conn;
//	}
//}
