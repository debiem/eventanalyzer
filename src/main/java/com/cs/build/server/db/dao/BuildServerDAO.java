package com.cs.build.server.db.dao;

import java.sql.SQLException;

import com.cs.build.server.model.AnalyzedEvents;

public interface BuildServerDAO {

	public static final String CREATE_TABLE_SQL = "create table EventStatistics (\r\n" + "  id  varchar(15) primary key,\r\n" +
	        "  duration int,\r\n" + "  type varchar(20),\r\n" + "  host varchar(20),\r\n" +
	        "  alert varchar(20)\r\n" + "  );";
	public static final String INSERT_EVENTS_SQL = "INSERT INTO EventStatistics" +
	        "  (id, duration, type, host, alert) VALUES " +
	        " (?, ?, ?, ?, ?);";
	public static final String DROP_TABLE_SQL = "drop table EventStatistics";
	public static final String TRUNCATE_TABLE_SQL = "truncate table EventStatistics";
	public static final String READ_EVENTS_SQL = "SELECT * FROM EventStatistics";
	public void createTable() throws SQLException;
	public void insertRecord(AnalyzedEvents ees) throws SQLException;
	public void readRecords() throws SQLException;
	public void dropTable() throws SQLException;
	public void truncateTable() throws SQLException;
}
