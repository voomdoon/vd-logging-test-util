package de.voomdoon.testing.logging.tests;

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
	 * @since 0.1.0
	 */
	@Override
	protected void logTestStart() {
		super.logTestStart();

		getLogCache().clear();
	}
}
