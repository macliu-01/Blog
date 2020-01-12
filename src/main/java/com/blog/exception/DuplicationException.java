package com.blog.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DuplicationException extends RuntimeException {

	public static Logger logger = LoggerFactory.getLogger(DuplicationException.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -4673646604743557852L;
	
	public DuplicationException() {
		super();
	}

	public DuplicationException(String message) {
		super(message);
		logger.info("****** Find DuplicationException ******");
	}
}
