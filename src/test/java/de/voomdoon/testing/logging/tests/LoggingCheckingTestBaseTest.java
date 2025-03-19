package de.voomdoon.testing.logging.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import de.voomdoon.logging.LogEvent;
import de.voomdoon.logging.LogLevel;
import de.voomdoon.logging.LogManager;
import de.voomdoon.testing.logging.CachingLogEventHandler;
import de.voomdoon.testing.tests.TestBase;

//TODO test logTestEnd

/**
 * DOCME add JavaDoc for
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
class LoggingCheckingTestBaseTest extends TestBase {

	/**
	 * @author André Schulz
	 *
	 * @since 0.1.0
	 */
	public static class InfoLoggingCheckingTestBase extends LoggingCheckingTestBaseImplementation {

		/**
		 * @since 0.1.0
		 */
		public InfoLoggingCheckingTestBase() {
			super(LogLevel.INFO);
		}
	}

	/**
	 * @author André Schulz
	 *
	 * @since 0.1.0
	 */
	public static class WarnLoggingCheckingTestBase extends LoggingCheckingTestBaseImplementation {

		/**
		 * @since 0.1.0
		 */
		public WarnLoggingCheckingTestBase() {
			super(LogLevel.WARN);
		}
	}

	/**
	 * DOCME add JavaDoc for LoggingCheckingTestBaseTest
	 *
	 * @author André Schulz
	 *
	 * @since 0.1.0
	 */
	@Nested
	class LogTestStartTest extends TestBase {

		/**
		 * DOCME add JavaDoc for LoggingCheckingTestBaseTest.LogTestStart
		 *
		 * @author André Schulz
		 *
		 * @since 0.1.0
		 */
		private static class LoggingCheckingTestBaseWithPublicLogTestStart extends LoggingCheckingTestBase {

			/**
			 * @since 0.1.0
			 */
			@Override
			public void logTestStart() {
				super.logTestStart();
			}
		}

		/**
		 * @since 0.1.0
		 */
		@Test
		void test_callsSuper() throws Exception {
			logTestStart();

			CachingLogEventHandler logCache = new CachingLogEventHandler();
			LogManager.addLogEventHandler(logCache);

			LoggingCheckingTestBaseImplementation implementation = new LoggingCheckingTestBaseImplementation(
					LogLevel.DEBUG);
			implementation.logTestStart();

			assertThat(logCache.getLogEvents(LogLevel.INFO).get(0).getMessage().toString()).contains("running test");
		}

		/**
		 * @since 0.1.0
		 */
		@Test
		void test_notCached() throws Exception {
			logTestStart();

			LoggingCheckingTestBaseWithPublicLogTestStart test = new LoggingCheckingTestBaseWithPublicLogTestStart();

			test.logTestStart();

			assertThat(test.getLogCache().getLogEvents(LogLevel.DEBUG)).isEmpty();
		}

	}

	/**
	 * @author André Schulz
	 *
	 * @since 0.1.0
	 */
	private static class LoggingCheckingTestBaseImplementation extends LoggingCheckingTestBase {

		/**
		 * @since 0.1.0
		 */
		private LogLevel logLevel;

		/**
		 * @param logLevel
		 * @since 0.1.0
		 */
		protected LoggingCheckingTestBaseImplementation(LogLevel logLevel) {
			this.logLevel = logLevel;
		}

		/**
		 * DOCME add JavaDoc for method log
		 * 
		 * @since 0.1.0
		 */
		void log() {
			logger.log(logLevel, logLevel);
		}
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void test_INFO() {
		logTestStart();

		InfoLoggingCheckingTestBase test = new InfoLoggingCheckingTestBase();
		test.log();

		List<LogEvent> actuals = test.getLogCache().getLogEvents(LogLevel.INFO);
		assertThat(actuals).hasSize(1);
		assertThat(actuals.get(0).getMessage()).isEqualTo(LogLevel.INFO);
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testGetLogCache() {
		logTestStart();

		assertThat(new LoggingCheckingTestBaseImplementation(LogLevel.DEBUG).getLogCache()).isNotNull();
	}
}
