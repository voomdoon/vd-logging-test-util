package de.voomdoon.testing.logging.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.voomdoon.logging.LogEvent;
import de.voomdoon.logging.LogLevel;
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

	// /**
	// * @author André Schulz
	// *
	// * @since 0.1.0
	// */
	// public static class ErrorLoggingCheckingTestBase extends LoggingCheckingTestBaseImplementation {
	//
	// /**
	// * @since 0.1.0
	// */
	// public ErrorLoggingCheckingTestBase() {
	// super(LogLevel.ERROR);
	// }
	// }
	//
	// /**
	// * @author André Schulz
	// *
	// * @since 0.1.0
	// */
	// public static class FatalLoggingCheckingTestBase extends LoggingCheckingTestBaseImplementation {
	//
	// /**
	// * @since 0.1.0
	// */
	// public FatalLoggingCheckingTestBase() {
	// super(LogLevel.FATAL);
	// }
	// }
	//
	// /**
	// * @author André Schulz
	// *
	// * @since 0.1.0
	// */
	// public static class InfoLoggingCheckingTestBase extends LoggingCheckingTestBaseImplementation {
	//
	// /**
	// * @since 0.1.0
	// */
	// public InfoLoggingCheckingTestBase() {
	// super(LogLevel.INFO);
	// }
	// }

	// /**
	// * @author André Schulz
	// *
	// * @since 0.1.0
	// */
	// public static class LogTestStartTest extends LoggingCheckingTestBaseImplementation {
	//
	// /**
	// * @param logLevel
	// * @since 0.1.0
	// */
	// public LogTestStartTest() {
	// super(null);
	// }
	//
	// /**
	// * @since 0.1.0
	// */
	// @Override
	// public void test() {
	// logTestStart();
	//
	// assertThat(getLogCache().getLogEvents(null)).isEmpty();
	// }
	// }

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
	static class LogTestStart extends TestBase {

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
		 * DOCME add JavaDoc for method test_notCached
		 * 
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

		// TODO handle out commented code
		// /**
		// * // * @since 0.1.0 //
		// */
		// @Test
		// void test() {
		// logTestStart();
		//
		// if (!skipLogging) {
		// logger.log(logLevel, "test");
		// }
		// }
	}

	// TODO handle out commented code
	// /**
	// * @since 0.1.0
	// */
	// @Test
	// public void testTest_ERROR_AssertionError() {
	// logTestStart();
	//
	// skipLogging = false;
	// Result result = JUnitCore.runClasses(ErrorLoggingCheckingTestBase.class);
	// skipLogging = true;
	// assertEquals(1, result.getFailureCount());
	// }
	//
	// /**
	// * @since 0.1.0
	// */
	// @Test
	// public void testTest_FATAL_AssertionError() {
	// logTestStart();
	//
	// skipLogging = false;
	// Result result = JUnitCore.runClasses(FatalLoggingCheckingTestBase.class);
	// skipLogging = true;
	// assertEquals(1, result.getFailureCount());
	// }
	//
	// /**
	// * @since 0.1.0
	// */
	// @Test
	// public void testTest_INFO_OK() {
	// logTestStart();
	//
	// Result result = JUnitCore.runClasses(InfoLoggingCheckingTestBase.class);
	// assertEquals(result.getFailures().toString(), 0, result.getFailureCount());
	// }
	//
	// /**
	// * @since 0.1.0
	// */
	// @Test
	// public void testTest_WARN_AssertionError() {
	// logTestStart();
	//
	// skipLogging = false;
	// Result result = JUnitCore.runClasses(WarnLoggingCheckingTestBase.class);
	// skipLogging = true;
	// assertEquals(1, result.getFailureCount());
	// }

	/**
	 * @since 0.1.0
	 */
	private static boolean skipLogging = true;

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
