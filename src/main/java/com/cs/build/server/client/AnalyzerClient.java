package com.cs.build.server.client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cs.build.server.db.dao.BuildServerDAO;
import com.cs.build.server.db.dao.impl.BuildServerDAOImpl;
import com.cs.build.server.model.AnalyzedEvents;
import com.cs.build.server.model.Event;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AnalyzerClient {
	
	private static final Logger logger = LoggerFactory.getLogger(AnalyzerClient.class); 

	public static void main(String[] args) throws Exception {
		Scanner keyboard = new Scanner(System.in);
		String fileName = keyboard.nextLine();
		if(logger.isDebugEnabled()) {
			logger.debug("Entered Filename is: {}", fileName);
		}
		keyboard.close();
		Path path = Paths.get(fileName);
		ObjectMapper mapper = new ObjectMapper();
		try (Stream<String> stream = Files.lines(path)) {
			Map<String, List<Event>> eventMap = stream.parallel().map(event -> {
				try {
					return mapper.readValue(event, Event.class);
				} catch (JsonMappingException jme) {
					if(logger.isErrorEnabled()) {
						logger.error("An error occured while transforming event JSON to event model", jme);
					}
					return null;
				} catch (JsonProcessingException jpe) {
					if(logger.isErrorEnabled()) {
						logger.error("An error occured while transforming event JSON to event model", jpe);
					}
					return null;
				}
			}).collect(Collectors.groupingBy(Event::getId));
			
			BuildServerDAO dao = new BuildServerDAOImpl();
			dao.createTable();
			
			/*Can not use parallel stream in below pipeline because it is doing a stateful operation, using parallel stream here can give unpredictable results */
			eventMap.keySet().stream().map(eventKey -> {
				List<Event> events = eventMap.get(eventKey);
				long buildTime = Math.abs(events.get(0).getTimeStamp() - events.get(1).getTimeStamp());
				if (buildTime > 4) {
					return new AnalyzedEvents(events.get(0).getId(), buildTime, events.get(0).getType(),
							events.get(0).getHost(), true);
				} else {
					return new AnalyzedEvents(events.get(0).getId(), buildTime, events.get(0).getType(),
							events.get(0).getHost(), false);
				}
			}).forEach(analyzedEvent -> {
				try {
					dao.insertRecord(analyzedEvent);
				} catch (SQLException sqle) {
					if(logger.isErrorEnabled()) {
						logger.error("An error occured while inserting analyzed events to HSQL DB", sqle);
					}
				}
			});
			dao.readRecords();
		} catch (IOException ioe) {
			if(logger.isErrorEnabled()) {
				logger.error("An error occured while reading the input log events file", ioe);
			}
			throw ioe;
		}
	}

}
