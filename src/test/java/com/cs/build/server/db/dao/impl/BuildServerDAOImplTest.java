package com.cs.build.server.db.dao.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cs.build.server.db.dao.BuildServerDAO;
import com.cs.build.server.model.AnalyzedEvents;

public class BuildServerDAOImplTest {

	BuildServerDAO dao = new BuildServerDAOImpl();
	
	@BeforeEach
	public void setUp() throws SQLException {
		dao.dropTable();
	}
	

	@Test
	public void testUnableToInsertWithoutID() throws SQLException {
		dao.createTable();
		AnalyzedEvents ae = new AnalyzedEvents(null, 4L, "test", "test", false);
		assertThrows(SQLIntegrityConstraintViolationException.class, () -> dao.insertRecord(ae));
		dao.truncateTable();
	}

	@Test
	public void testNoTwoRecordsCanHaveSameId() throws SQLException {
		dao.createTable();
		AnalyzedEvents ae = new AnalyzedEvents("test1", 4L, "test", "test", false);
		AnalyzedEvents ae2 = new AnalyzedEvents("test1", 4L, "test2", "test2", false);
		dao.insertRecord(ae);
		assertThrows(SQLIntegrityConstraintViolationException.class, () -> dao.insertRecord(ae2));
		dao.truncateTable();
	}

}
