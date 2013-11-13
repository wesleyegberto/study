package br.com.caelum.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {
	private static final String HOST = "localhost";
	private static final String PORT = "3306";
	private static final String USER = "root";
	private static final String PASSWORD = "1234abc@";
	private static final String DATABASE = "Estudo";
	private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;

	private ConnectionFactory() {
	}

	public static Connection getConnection() {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			return conn;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return conn;
	}

	public static void close(Statement stmt) {
		try {
			stmt.close();
		} catch(SQLException e) {
		}
	}

	public static void close(Connection conn) {
		try {
			conn.close();
		} catch(SQLException e) {
		}
	}
}
