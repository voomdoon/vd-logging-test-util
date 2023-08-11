package de.voomdoon.testing.logging;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import de.voomdoon.logging.LogEvent;
import de.voomdoon.logging.LogLevel;
import de.voomdoon.logging.test.TestLogEvent;
import de.voomdoon.testing.tests.TestBase;

/**
 * DOCME add JavaDoc for
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
abstract class CachingLogEventHandlerTest {

	/**
	 * DOCME add JavaDoc for CachingLogEventHandlerTest
	 *
	 * @author André Schulz
	 *
	 * @since 0.1.0
	 */
	@Nested
	class ClearTest extends TestBase {

		/**
		 * DOCME add JavaDoc for method test
		 * 
		 * @since 0.1.0
		 */
		@Test
		void test() throws Exception {
			logTestStart();

			CachingLogEventHandler handler = new CachingLogEventHandler();
			handler.handleLogEvent(new LogEvent() {

				@Override
				public Throwable getError() {
					throw new UnsupportedOperationException("'getError' not implemented at 'ClearTest'!");
				}

				@Override
				public LogLevel getLevel() {
					return LogLevel.DEBUG;
				}

				@Override
				public Object getMessage() {
					throw new UnsupportedOperationException("'getMessage' not implemented at 'ClearTest'!");
				}

				@Override
				public Class<?> getSourceClass() {
					throw new UnsupportedOperationException("'getSourceClass' not implemented at 'ClearTest'!");
				}

				@Override
				public long getTimestamp() {
					throw new UnsupportedOperationException("'getTimestamp' not implemented at 'ClearTest'!");
				}
			});

			handler.clear();

			assertThat(handler.getLogEvents(LogLevel.DEBUG)).isEmpty();
		}

		/**
		 * DOCME add JavaDoc for method test_empty
		 * 
		 * @since 0.1.0
		 */
		@Test
		void test_empty_notThrowingAnything() throws Exception {
			logTestStart();

			CachingLogEventHandler handler = new CachingLogEventHandler();

			assertDoesNotThrow(() -> handler.clear());
		}
	}

	/**
	 * DOCME add JavaDoc for CachingLogEventHandlerTest
	 *
	 * @author André Schulz
	 *
	 * @since 0.1.0
	 */
	@Nested
	class GetLogEventsTest extends TestBase {

		/**
		 * @since 0.1.0
		 */
		private CachingLogEventHandler handler = new CachingLogEventHandler();

		/**
		 * DOCME add JavaDoc for method test
		 * 
		 * @throws Exception
		 * @since 0.1.0
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
		 * @since 0.1.0
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
		 * @since 0.1.0
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
		 * @since 0.1.0
		 */
		@Test
		void test_level_empty() throws Exception {
			logTestStart();

			handler.handleLogEvent(new TestLogEvent().setLevel(LogLevel.INFO));

			assertThat(handler.getLogEvents(LogLevel.WARN)).isEmpty();
		}
	}
}
