package de.voomdoon.testing.logging.tests;

import de.voomdoon.logging.LogManager;
import de.voomdoon.testing.logging.CachingLogEventHandler;
import de.voomdoon.testing.tests.TestBase;

/**
 * DOCME add JavaDoc for
 *
 * @author André Schulz
 *
 * @since DOCME add inception version number
 */
public abstract class LoggingCheckingTestBase extends TestBase {

	/**
	 * @since 0.1.0
	 */
	private CachingLogEventHandler logCache;

	/**
	 * DOCME add JavaDoc for constructor LoggingCheckingTestBase
	 * 
	 * @since DOCME add inception version number
	 */
	protected LoggingCheckingTestBase() {
		logCache = new CachingLogEventHandler();

		LogManager.addLogEventHandler(logCache);
	}

	/**
	 * DOCME add JavaDoc for method getLogCache
	 * 
	 * @return
	 * @since DOCME add inception version number
	 */
	public CachingLogEventHandler getLogCache() {
		return logCache;
	}
}
