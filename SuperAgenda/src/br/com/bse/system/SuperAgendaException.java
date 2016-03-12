/**
 * Copyright (c) 2010-2012, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito e William Carvalho Noronha 08/07/2012
 * 
 * @version 1.0
 * 
 *          Classe Exception que pode ser lançada pelo sistema
 */
package br.com.bse.system;

public class SuperAgendaException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6184751746282952460L;

	public SuperAgendaException(String message) {
		super(message);
	}

	public SuperAgendaException(String message, Throwable cause) {
		super(message, cause);
	}

	public SuperAgendaException(Throwable cause) {
		super(cause);
	}
}
