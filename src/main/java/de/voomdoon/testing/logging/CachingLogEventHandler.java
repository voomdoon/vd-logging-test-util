package de.voomdoon.testing.logging;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import de.voomdoon.logging.LogEvent;
import de.voomdoon.logging.LogEventHandler;
import de.voomdoon.logging.LogLevel;

//TODO rename to CachingTestLogEventHandler

/**
 * DOCME add JavaDoc for
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
public class CachingLogEventHandler implements LogEventHandler {

	/**
	 * @since 0.1.0
	 */
	private List<LogEvent> events;

	/**
	 * DOCME add JavaDoc for constructor CachingLogEventHandler
	 * 
	 * @since 0.1.0
	 */
	public CachingLogEventHandler() {
		events = new ArrayList<>();
	}

	/**
	 * DOCME add JavaDoc for method clear
	 * 
	 * @since 0.1.0
	 */
	public void clear() {
		events.clear();
	}

	/**
	 * DOCME add JavaDoc for method getLogEvents
	 * 
	 * @param logLevels
	 * @return {@link List} of {@link LogEvent}
	 * @since 0.1.0
	 */
	public List<LogEvent> getLogEvents(LogLevel... logLevels) {
		List<LogLevel> levels = logLevels == null ? Collections.emptyList() : Arrays.asList(logLevels);

		return events.stream().filter(event -> levels.isEmpty() || levels.contains(event.getLevel())).toList();
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public void handleLogEvent(LogEvent logEvent) {
		// TODO implement handleLogEvent
		events.add(logEvent);
	}

	/**
	 * DOCME add JavaDoc for method removeEvents
	 * 
	 * @param level
	 * @param messagePattern
	 * @since 0.2.0
	 */
	public void removeEvents(LogLevel level, Pattern messagePattern) {
		Iterator<LogEvent> iterator = events.iterator();

		while (iterator.hasNext()) {
			LogEvent event = iterator.next();

			if (event.getLevel() == level && messagePattern.matcher(event.getMessage().toString()).matches()) {
				iterator.remove();
			}
		}
	}
}
