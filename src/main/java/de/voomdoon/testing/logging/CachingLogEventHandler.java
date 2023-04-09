package de.voomdoon.testing.logging;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.voomdoon.logging.LogEvent;
import de.voomdoon.logging.LogEventHandler;
import de.voomdoon.logging.LogLevel;

/**
 * DOCME add JavaDoc for
 *
 * @author André Schulz
 *
 * @since DOCME add inception version number
 */
public class CachingLogEventHandler implements LogEventHandler {

	/**
	 * @since DOCME add inception version number
	 */
	private List<LogEvent> events;

	/**
	 * DOCME add JavaDoc for constructor CachingLogEventHandler
	 * 
	 * @since DOCME add inception version number
	 */
	public CachingLogEventHandler() {
		events = new ArrayList<>();
	}

	/**
	 * DOCME add JavaDoc for method getLogEvents
	 * 
	 * @param logLevels
	 * @return {@link List} of {@link LogEvent}
	 * @since DOCME add inception version number
	 */
	public List<LogEvent> getLogEvents(LogLevel... logLevels) {
		List<LogLevel> levels = logLevels == null ? Collections.emptyList() : Arrays.asList(logLevels);

		return events.stream().filter(event -> levels.isEmpty() || levels.contains(event.getLevel())).toList();
	}

	/**
	 * @since DOCME add inception version number
	 */
	@Override
	public void handleLogEvent(LogEvent logEvent) {
		// TODO implement handleLogEvent
		events.add(logEvent);
	}
}
