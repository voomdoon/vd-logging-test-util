package de.voomdoon.testing.logging.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;

import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import de.voomdoon.logging.LogEvent;
import de.voomdoon.logging.LogLevel;
import de.voomdoon.logging.LogManager;
import de.voomdoon.testing.logging.CachingLogEventHandler;
import de.voomdoon.testing.tests.TestBase;

//TODO test logTestEnd

/**
 * Test class for {@link LoggingCheckingTestBase}.
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
	 * Test class for {@link LoggingCheckingTestBase#afterEach_verifyNoSevereLogs()}.
	 *
	 * @author André Schulz
	 *
	 * @since 0.1.0
	 */
	@Nested
	class AfterEach_verifyNoSevereLogs_Test extends TestBase {

		/**
		 * @since 0.1.0
		 */
		@ParameterizedTest
		@EnumSource(value = LogLevel.class, names = { "WARN", "ERROR", "FATAL" })
		void test_AssertionError(LogLevel logLevel) throws Exception {
			logTestStart();

			LoggingCheckingTestBaseImplementation test = new LoggingCheckingTestBaseImplementation(logLevel);

			test.log();

			Throwable actual = assertThatThrownBy(() -> test.afterEach_verifyNoSevereLogs())
					.isInstanceOf(AssertionError.class).hasMessageContaining(logLevel.toString()).actual();
			logger.debug("actual: " + actual.getMessage(), actual);
		}

		/**
		 * @since 0.1.0
		 */
		@Test
		void test_AssertionError_messageContainsHIghestLevel() throws Exception {
			logTestStart();

			LoggingCheckingTestBaseImplementation test = new LoggingCheckingTestBaseImplementation(LogLevel.DEBUG) {

				@Override
				void log() {
					logger.error("test-error");
					logger.warn("test-warn");
				}
			};

			test.log();

			AbstractThrowableAssert<?, ?> assert1 = assertThatThrownBy(() -> test.afterEach_verifyNoSevereLogs())
					.isInstanceOf(AssertionError.class).hasMessageContaining("ERROR");
			assert1.message().doesNotContain("WARN");

			Throwable actual = assert1.actual();
			logger.debug("actual: " + actual.getMessage(), actual);
		}

		/**
		 * @since 0.1.0
		 */
		@Test
		void test_passes_INFO() throws Exception {
			logTestStart();

			LoggingCheckingTestBaseImplementation test = new LoggingCheckingTestBaseImplementation(LogLevel.INFO);

			test.log();

			assertDoesNotThrow(() -> test.afterEach_verifyNoSevereLogs());
		}
	}

	/**
	 * Test class for {@link LoggingCheckingTestBase#logTestStart()}.
	 *
	 * @author André Schulz
	 *
	 * @since 0.1.0
	 */
	@Nested
	class LogTestStartTest extends TestBase {

		/**
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
