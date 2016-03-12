/**
 * Copyright (c) 2010-2012, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 26/05/2012
 * 
 * @version 1.0
 * 
 *          Classe de neg�cio Aviso
 */
package br.com.bse.business;

public abstract class Aviso {
	/**
	 * Adiciona o valor de comunica��o de um contato n�o cadastrado.
	 * 
	 * @param valor
	 *            valor da comunica��o
	 */
	public abstract void addContato(String valor);

	/**
	 * Remove um valor de comunica��o de um contato n�o cadastrado.
	 * 
	 * @param valor
	 *            valor da comunica��o
	 */
	public abstract void removeContato(String valor);

	/**
	 * Adiciona a comunica��o de um contato cadastrado.
	 * 
	 * @param idContato
	 *            ID do contato
	 * @param idComun
	 *            ID da comunica��o
	 * 
	 */
	public abstract void addContato(int idContato, int idComun);

	/**
	 * Remove a comunica��o de um contato cadastrado.
	 * 
	 * @param idContato
	 *            ID do contato
	 * @param idComun
	 *            ID da comunica��o
	 * 
	 */
	public abstract void removeContato(int idContato, int idComun);

	/**
	 * Adiciona um grupo.
	 * 
	 * @param idGrupo
	 *            ID do grupo
	 */
	public abstract void addGrupo(int idGrupo);

	/**
	 * Remove um grupo.
	 * 
	 * @param idGrupo
	 *            ID do grupo
	 */
	public abstract void removeGrupo(int idGrupo);
}
