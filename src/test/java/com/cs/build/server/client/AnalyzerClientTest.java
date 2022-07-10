package com.cs.build.server.client;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.NoSuchFileException;

import org.junit.jupiter.api.Test;

public class AnalyzerClientTest {
	
	@Test
	public void testFileNotFound() throws Exception {
		String[] args = null;
		InputStream sysInBackup = System.in; // backup System.in to restore it later
		ByteArrayInputStream in = new ByteArrayInputStream("My string".getBytes());
		System.setIn(in);
		assertThrows(NoSuchFileException.class, () -> AnalyzerClient.main(args));
		System.setIn(sysInBackup);
	}

}
