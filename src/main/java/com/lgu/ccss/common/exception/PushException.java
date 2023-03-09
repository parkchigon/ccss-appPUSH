package com.lgu.ccss.common.exception;

public class PushException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4671643494945399127L;

	public PushException() {
		super("80000");
	}
	
	public PushException(String message) {
		super(message);
	}
}
