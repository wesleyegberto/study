/**
 * Copyright (c) 2010-2012, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 01/06/2012
 * 
 * @version 1.0
 * 
 *          Classe DAO responsável pela persistência dos tipos Contato
 */
package br.com.bse.db.dao;

import java.io.File;
import java.util.List;
import br.com.bse.business.Contato;
import br.com.bse.pattern.XMLCreator;
import br.com.bse.system.GeralProperty;

public class ContatoDAO implements DAO<Contato> {
	private String pathRelative = GeralProperty.ROOT + "contatos" + File.separator;
	private XMLCreator xmlCreator;

	public ContatoDAO() {
		xmlCreator = new XMLCreator("CONTATO");
	}

	/**
	 * @param c
	 *            objeto Contato que será persistido
	 * @return true se foi persistido com sucesso
	 * 
	 * @see br.com.bse.db.dao.DAO#insert(java.lang.Object)
	 */
	public boolean insert(Contato c) {
		return false;
	}

	/**
	 * @param c
	 *            objeto Contato que será persistido
	 * @return true se foi atualizado com sucesso
	 * 
	 * @see br.com.bse.db.dao.DAO#update(java.lang.Object)
	 */
	public boolean update(Contato c) {
		return false;
	}

	/**
	 * @param c
	 *            objeto Contato que será deletado
	 * @return true se foi deletado com sucesso
	 * 
	 * @see br.com.bse.db.dao.DAO#delete(java.lang.Object)
	 */
	public boolean delete(Contato c) {
		return false;
	}

	/**
	 * @param pathFile
	 * @return
	 * 
	 * @see br.com.bse.db.dao.DAO#get(java.lang.String)
	 */
	public Contato get(String pathFile) {
		return null;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Contato get(int id) {
		String path = this.pathRelative + File.separator + id + ".cnt";
		return get(path);
	}

	/**
	 * @return uma List de Contato com todos os registros encontrados
	 * @see br.com.bse.db.dao.DAO#getAll(java.lang.Object)
	 */
	public List<Contato> getAll() {
		return null;
	}

	/**
	 * @param fieldName
	 *            nome do campo existente na fonte de dados Contato
	 * @param value
	 *            valor para o campo
	 * @return List com os objetos Contato encontrado
	 * @see br.com.bse.db.dao.DAO#getByField(java.lang.String, java.lang.String)
	 */
	public List<Contato> getByField(String fieldName, String value) {
		return null;
	}

}
