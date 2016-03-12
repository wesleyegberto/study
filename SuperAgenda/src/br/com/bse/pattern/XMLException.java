/**
 * Copyright (c) 2010-2012, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 02/06/2012
 * 
 * @version 1.0
 * 
 *          Classe de XMLException que podem ser lançadas pela XMLCreator e
 *          XMLReader
 */
package br.com.bse.pattern;

public class XMLException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1342343286984991220L;

	public XMLException(String message) {
		super(message);
	}

	public XMLException(String message, Throwable cause) {
		super(message, cause);
	}

	public XMLException(Throwable cause) {
		super(cause);
	}
}
