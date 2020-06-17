package com.shenhufei.exception;

import lombok.Data;

/**
 *   
 * 
 * @Description: 做sql操作的时候，如果异常的就抛出此异常
 * @author shenhufei  
 * @date 2019年5月1日 下午21:34:07 
 * @version V1.0   
 */
@Data
public class DBException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8298073101628167055L;
	private String message;


	public DBException(String message) {
		super();
		this.message = message;
	}

	public DBException() {
		super();
	}

	public DBException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DBException(String message, Throwable cause) {
		super(message, cause);
	}

	public DBException(Throwable cause) {
		super(cause);
	}

	

}
