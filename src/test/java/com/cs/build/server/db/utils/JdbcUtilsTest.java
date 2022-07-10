package com.cs.build.server.db.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class JdbcUtilsTest {
	
	@Test
	public void testGetConnectionOnCorrectDetails() {
		assertNotNull(JdbcUtils.getConnection());
	}

}
