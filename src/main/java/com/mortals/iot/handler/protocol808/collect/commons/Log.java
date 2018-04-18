package com.mortals.iot.handler.protocol808.collect.commons;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {

	private final Logger logger;

	private Log(Class<?> clazz) {
		// logger = org.apache.log4j.Logger.getLogger(clazz);
		logger = LoggerFactory.getLogger(clazz);
	}

	private Log() {
		logger = LoggerFactory.getLogger(this.getClass());
	}

	public static Log getLogger(Class<?> clazz) {
		return new Log(clazz);
	}

	public static Log getRootLogger() {
		return new Log();
	}

	public void trace(String message) {
		if (logger.isTraceEnabled()) {
			logger.trace(message);
		}
	}

	public void trace(String message, Throwable t) {
		if (logger.isTraceEnabled()) {
			logger.trace(message, t);
		}
	}

	public void trace(String pattern, Object... arguments) {
		if (logger.isTraceEnabled()) {
			logger.trace(format(pattern, arguments));
		}
	}

	public void trace(String pattern, Throwable t, Object... arguments) {
		if (logger.isTraceEnabled()) {
			logger.trace(format(pattern, arguments), t);
		}
	}

	public void debug(String message) {
		if (logger.isDebugEnabled()) {
			logger.debug(message);
		}
	}

	public void debug(String message, Throwable t) {
		if (logger.isDebugEnabled()) {
			logger.debug(message, t);
		}
	}

	public void debug(String pattern, Object... arguments) {
		if (logger.isDebugEnabled()) {
			logger.debug(format(pattern, arguments));
		}
	}

	public void debug(String pattern, Throwable t, Object... arguments) {
		if (logger.isDebugEnabled()) {
			logger.debug(format(pattern, arguments), t);
		}
	}

	public void info(String message) {
		if (logger.isInfoEnabled()) {
			logger.info(message);
		}
	}

	public void info(String message, Throwable t) {
		if (logger.isInfoEnabled()) {
			logger.info(message, t);
		}
	}

	public void info(String pattern, Object... arguments) {
		if (logger.isInfoEnabled()) {
			logger.info(format(pattern, arguments));
		}
	}

	public void info(String pattern, Throwable t, Object... arguments) {
		if (logger.isInfoEnabled()) {
			logger.info(format(pattern, arguments), t);
		}
	}

	public void warn(String message) {
		if (logger.isWarnEnabled()) {
			logger.warn(message);
		}
	}

	public void warn(String message, Throwable t) {
		if (logger.isWarnEnabled()) {
			logger.warn(message, t);
		}
	}

	public void warn(String pattern, Object... arguments) {
		if (logger.isWarnEnabled()) {
			logger.warn(format(pattern, arguments));
		}
	}

	public void warn(String pattern, Throwable t, Object... arguments) {
		if (logger.isWarnEnabled()) {
			logger.trace(format(pattern, arguments), t);
		}
	}

	public void error(String message) {
		if (logger.isErrorEnabled()) {
			logger.error(message);
		}
	}

	public void error(String message, Throwable t) {
		if (logger.isErrorEnabled()) {
			logger.error(message, t);
		}
	}

	public void error(String pattern, Object... arguments) {
		if (logger.isErrorEnabled()) {
			logger.error(format(pattern, arguments));
		}
	}

	public void error(String pattern, Throwable t, Object... arguments) {
		if (logger.isErrorEnabled()) {
			logger.trace(format(pattern, arguments), t);
		}
	}


	public void assertLog(boolean assertion, String message) {
		if (!assertion) {
			logger.error(message);
		}
	}

	private static String format(String pattern, Object... arguments) {
		return MessageFormat.format(pattern, arguments);
	}

	@SuppressWarnings("unused")
	private static final String FQCN;

	static {
		FQCN = Log.class.getName();
	}

}