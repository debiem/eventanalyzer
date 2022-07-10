package com.cs.build.server.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cs.build.server.db.dao.BuildServerDAO;
import com.cs.build.server.db.utils.JdbcUtils;
import com.cs.build.server.model.AnalyzedEvents;

public class BuildServerDAOImpl implements BuildServerDAO {

	private static final Logger logger = LoggerFactory.getLogger(BuildServerDAOImpl.class);
	
	@Override
	public void createTable() throws SQLException {
		if(logger.isDebugEnabled()) {
			logger.debug("About to create table for analyzed events on HSQL DB");
		}
		dropTable(); //Drop the table if it already exists
		try (Connection connection = JdbcUtils.getConnection(); Statement statement = connection.createStatement();) {
			statement.execute(BuildServerDAO.CREATE_TABLE_SQL);
		} catch (SQLException e) {
			JdbcUtils.printSQLException(e);
		}
	}
	
	@Override
	public void dropTable() throws SQLException {
		if(logger.isDebugEnabled()) {
			logger.debug("About to create table for analyzed events on HSQL DB");
		}
		try (Connection connection = JdbcUtils.getConnection(); Statement statement = connection.createStatement();) {
			statement.execute(BuildServerDAO.DROP_TABLE_SQL);
		} catch (SQLException e) {
			JdbcUtils.printSQLException(e);
		}
	}
	
	@Override
	public void truncateTable() throws SQLException {
		if(logger.isDebugEnabled()) {
			logger.debug("About to create table for analyzed events on HSQL DB");
		}
		try (Connection connection = JdbcUtils.getConnection(); Statement statement = connection.createStatement();) {
			statement.execute(BuildServerDAO.TRUNCATE_TABLE_SQL);
		} catch (SQLException e) {
			JdbcUtils.printSQLException(e);
		}
	}

	@Override
	public void insertRecord(AnalyzedEvents ees) throws SQLException {
		if(logger.isDebugEnabled()) {
			logger.debug("About to insert event with Event id "+ ees.getId() +" to HSQL DB");
		}
		try (Connection connection = JdbcUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(BuildServerDAO.INSERT_EVENTS_SQL)) {
			preparedStatement.setString(1, ees.getId());
			preparedStatement.setLong(2, ees.getDuration());
			preparedStatement.setString(3, ees.getType()==null?"":ees.getType());
			preparedStatement.setString(4, ees.getHost()==null?"":ees.getHost());
			preparedStatement.setString(5, new Boolean(ees.isAlert()).toString());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			JdbcUtils.printSQLException(e);
			throw e;
		}
	}

	@Override
	public void readRecords() throws SQLException {
		if(logger.isDebugEnabled()) {
			logger.debug("About to read all analyzed events on HSQL DB");
		}
		try (Connection connection = JdbcUtils.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(BuildServerDAO.READ_EVENTS_SQL);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id");
				long duration = rs.getLong("duration");
				String type = rs.getString("type");
				String host = rs.getString("host");
				String alert = rs.getString("alert");
				if(logger.isInfoEnabled()) {
					logger.info("id: " + id + "," + " duration: " + duration + ", type: " + type + ", host: " + host
							+ ", alert: " + alert);
				}
			}
		} catch (SQLException e) {
			JdbcUtils.printSQLException(e);
		}
	}

}
