/**
 * Copyright (c) 2010-2012, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 26/05/2012
 * 
 * @version 1.0
 * 
 *          Classe de negócio Grupo
 */
package br.com.bse.business;

import java.util.ArrayList;
import java.util.List;

public class Grupo {
	private int id;
	private String descricao;
	private List<Integer> contatos;

	public Grupo() {
		contatos = new ArrayList<Integer>();
	}

	// Getters and Setters
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setContatos(List<Integer> contatos) {
		this.contatos = contatos;
	}

	public List<Integer> getContatos() {
		return contatos;
	}

	/**
	 * @param id
	 *            ID do contato
	 */
	public void addContato(int id) {
		this.contatos.add(id);
	}

	/**
	 * @param id
	 *            ID do contato
	 */
	public void remContato(int id) {
		this.contatos.remove(id);
	}

}
