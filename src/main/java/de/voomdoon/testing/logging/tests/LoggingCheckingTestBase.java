package de.voomdoon.testing.logging.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;

import de.voomdoon.logging.LogEvent;
import de.voomdoon.logging.LogLevel;
import de.voomdoon.logging.LogManager;
import de.voomdoon.testing.logging.CachingLogEventHandler;
import de.voomdoon.testing.tests.TestBase;

/**
 * DOCME add JavaDoc for
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
public abstract class LoggingCheckingTestBase extends TestBase {

	/**
	 * @since 0.1.0
	 */
	private CachingLogEventHandler logCache;

	/**
	 * DOCME add JavaDoc for constructor LoggingCheckingTestBase
	 * 
	 * @since 0.1.0
	 */
	protected LoggingCheckingTestBase() {
		logCache = new CachingLogEventHandler();

		LogManager.addLogEventHandler(logCache);
	}

	/**
	 * DOCME add JavaDoc for method getLogCache
	 * 
	 * @return
	 * @since 0.1.0
	 */
	public CachingLogEventHandler getLogCache() {
		return logCache;
	}

	/**
	 * Asserts whether there was no {@link LogLevel#WARN} or worse.
	 * 
	 * @since 0.1.0
	 */
	@AfterEach
	protected void afterEach_verifyNoSevereLogs() {
		for (LogLevel logLevel : new LogLevel[] { LogLevel.FATAL, LogLevel.ERROR, LogLevel.WARN }) {
			List<LogEvent> events = getLogCache().getLogEvents(logLevel);
			assertThat(events)
					.withFailMessage(() -> "Expecting no logging of level " + logLevel + ", but found " + events.size()
							+ "!\n"
							+ events.stream().map(event -> "• " + event.getMessage()).collect(Collectors.joining("\n")))
					.isEmpty();
		}
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	protected void logTestStart() {
		super.logTestStart();

		getLogCache().clear();
	}
}
