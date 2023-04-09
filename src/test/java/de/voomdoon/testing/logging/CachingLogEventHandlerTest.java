package de.voomdoon.testing.logging;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import de.voomdoon.logging.LogLevel;
import de.voomdoon.logging.test.TestLogEvent;
import de.voomdoon.testing.tests.TestBase;

/**
 * DOCME add JavaDoc for
 *
 * @author André Schulz
 *
 * @since DOCME add inception version number
 */
abstract class CachingLogEventHandlerTest {

	/**
	 * DOCME add JavaDoc for CachingLogEventHandlerTest
	 *
	 * @author André Schulz
	 *
	 * @since DOCME add inception version number
	 */
	static class GetLogEventsTest extends TestBase {

		/**
		 * @since DOCME add inception version number
		 */
		private CachingLogEventHandler handler = new CachingLogEventHandler();

		/**
		 * DOCME add JavaDoc for method test
		 * 
		 * @throws Exception
		 * @since DOCME add inception version number
		 */
		@Test
		void test_all() throws Exception {
			logTestStart();

			handler.handleLogEvent(new TestLogEvent().setLevel(LogLevel.INFO));

			assertThat(handler.getLogEvents()).hasSize(1);
		}

		/**
		 * DOCME add JavaDoc for method test_initial
		 * 
		 * @throws Exception
		 * @since DOCME add inception version number
		 */
		@Test
		void test_initial() throws Exception {
			logTestStart();

			assertThat(handler.getLogEvents()).isEmpty();
		}

		/**
		 * DOCME add JavaDoc for method test_level
		 * 
		 * @throws Exception
		 * @since DOCME add inception version number
		 */
		@Test
		void test_level() throws Exception {
			logTestStart();

			handler.handleLogEvent(new TestLogEvent().setLevel(LogLevel.INFO));

			assertThat(handler.getLogEvents(LogLevel.INFO)).hasSize(1);
		}

		/**
		 * DOCME add JavaDoc for method test_level_empty
		 * 
		 * @throws Exception
		 * @since DOCME add inception version number
		 */
		@Test
		void test_level_empty() throws Exception {
			logTestStart();

			handler.handleLogEvent(new TestLogEvent().setLevel(LogLevel.INFO));

			assertThat(handler.getLogEvents(LogLevel.WARN)).isEmpty();
		}
	}
}
