/**
 * Copyright (c) 2010-2012, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 26/05/2012
 * 
 * @version 1.0
 * 
 *          Classe GrupoDAO
 */
package br.com.bse.db.dao;

import java.util.List;
import br.com.bse.business.Grupo;

@SuppressWarnings("unused")
public class GrupoDAO implements DAO<Grupo> {
	private String path;

	/**
	 * @param g
	 *            objeto Grupo que será persistido
	 * @return true se foi persistido com sucesso
	 * 
	 * @see br.com.bse.db.dao.DAO#insert(java.lang.Object)
	 */
	public boolean insert(Grupo g) {
		this.path = "" + g.getId();

		return false;
	}

	/**
	 * @param g
	 * @return
	 * 
	 * @see br.com.bse.db.dao.DAO#update(java.lang.Object)
	 */
	public boolean update(Grupo g) {
		return insert(g);
	}

	/**
	 * @param e
	 * @return
	 * 
	 * @see br.com.bse.db.dao.DAO#delete(java.lang.Object)
	 */
	public boolean delete(Grupo e) {
		return false;
	}

	/**
	 * @param pathFile
	 * @return
	 * 
	 * @see br.com.bse.db.dao.DAO#get(java.lang.String)
	 */
	public Grupo get(String pathFile) {
		return null;
	}

	/**
	 * @return
	 * 
	 * @see br.com.bse.db.dao.DAO#getAll()
	 */
	public List<Grupo> getAll() {
		return null;
	}

	/**
	 * @param fieldName
	 *            nome do campo existente na fonte de dados Grupo
	 * @param value
	 *            valor para o campo
	 * 
	 * @see br.com.bse.db.dao.DAO#getByField(java.lang.String, java.lang.String)
	 */
	public List<Grupo> getByField(String fieldName, String value) {
		return null;
	}

}
