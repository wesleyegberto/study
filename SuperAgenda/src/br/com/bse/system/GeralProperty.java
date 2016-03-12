/**
 * Copyright (c) 2010-2012, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 26/05/2012
 * 
 * @version 1.0
 * 
 *          Classe que conter� todas as informa��es e configura��es sobre o
 *          sistema
 */
package br.com.bse.system;

import java.io.File;

public final class GeralProperty {
	/**
	 * ROOT - cont�m o diret�rio absoluto at� a pasta da SuperAgenda NAME_SO -
	 * cont�m o nome do sistema operacional
	 */
	public static final String ROOT;
	public static final String NAME_SO;

	private GeralProperty() {
	}

	static {
		// Obtem o diret�rio atual do execut�vel
		ROOT = java.lang.System.getProperty("user.dir") + File.separator;
		// Obtem o nome do sistema operacional
		NAME_SO = java.lang.System.getProperty("os.name");
	}
}
