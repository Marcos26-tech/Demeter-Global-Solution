package br.com.demeter.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnection() {
		try {
			String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
			String username = "RM88082";
			String password = "080502";

			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection = DriverManager.getConnection(url, username, password);
			return connection;
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return null;

	}
}
