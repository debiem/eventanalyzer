package com.cs.build.server.db.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdbcUtils {

	private static String jdbcURL = "jdbc:hsqldb:hsql://localhost/";
	private static String jdbcUsername = "SA";
	private static String jdbcPassword = "";

	private static final Logger logger = LoggerFactory.getLogger(JdbcUtils.class);
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException sqle) {
			if(logger.isErrorEnabled()) {
				logger.error("An error occured while connecting to HSQL DB", sqle);
			}
		}
		return connection;
	}

	public static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				if(logger.isErrorEnabled()) {
					logger.error("SQLState: " + ((SQLException) e).getSQLState());
					logger.error("Error Code: " + ((SQLException) e).getErrorCode());
					logger.error("Message: " + e.getMessage());
				}
			}
		}
	}
}
