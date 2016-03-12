/**
 * Copyright (c) 2010-2012, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 26/05/2012
 * 
 * @version 1.0
 * 
 *          Classe de negócio Aviso
 */
package br.com.bse.business;

public abstract class Aviso {
	/**
	 * Adiciona o valor de comunicação de um contato não cadastrado.
	 * 
	 * @param valor
	 *            valor da comunicação
	 */
	public abstract void addContato(String valor);

	/**
	 * Remove um valor de comunicação de um contato não cadastrado.
	 * 
	 * @param valor
	 *            valor da comunicação
	 */
	public abstract void removeContato(String valor);

	/**
	 * Adiciona a comunicação de um contato cadastrado.
	 * 
	 * @param idContato
	 *            ID do contato
	 * @param idComun
	 *            ID da comunicação
	 * 
	 */
	public abstract void addContato(int idContato, int idComun);

	/**
	 * Remove a comunicação de um contato cadastrado.
	 * 
	 * @param idContato
	 *            ID do contato
	 * @param idComun
	 *            ID da comunicação
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
