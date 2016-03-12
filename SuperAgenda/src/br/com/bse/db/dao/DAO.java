/**
 * Copyright (c) 2010-2012, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 26/05/2012
 * 
 * @version 1.0
 * 
 *          Interface DAO genérica
 */
package br.com.bse.db.dao;

import java.util.List;

/**
 * @param <E>
 *            tipo da classe que o DAO fornecerá serviços
 */
public interface DAO<E> {
	/**
	 * Persiste o objeto E.
	 * 
	 * @param e
	 *            objeto do tipo E a ser persistido
	 * @return true se foi inserido com sucesso
	 */
	public boolean insert(E e);

	/**
	 * Atualiza os dados persistido do objeto E.
	 * 
	 * @param e
	 *            objeto do tipo E a ser atualizado
	 * @return true se foi atualizado com sucesso, deve-se utilizar um campo
	 *         como chave de pesquisa
	 */
	public boolean update(E e);

	/**
	 * Delete os dados persistido do objeto E.
	 * 
	 * @param e
	 *            objeto do tipo E a ser deletado, deve-se utilizar um campo
	 *            como chave de pesquisa
	 * @return true se foi deletado com sucesso
	 */
	public boolean delete(E e);

	/**
	 * Busca os dados do objeto E no diretório recebido.
	 * 
	 * @param pathFile
	 *            objeto do tipo E a ser carregado com os dados do registro,
	 *            deve-se utilizar um campo como chave de pesquisa
	 * @return E contendo o objeto encontrado
	 */
	public E get(String pathFile);

	/**
	 * Busca todos os dados dos objetos do tipo E persistido.
	 * 
	 * @return uma List de objetos do tipo E com todos os registros encontrados
	 */
	public List<E> getAll();

	/**
	 * Retorna os objetos E a partir da pesquisa.
	 * 
	 * @param fieldName
	 *            nome do campo que será usado como filtro
	 * @param value
	 *            valor para o campo
	 * 
	 * @return List com os objetos E encontrado
	 */
	public List<E> getByField(String fieldName, String value);

}
