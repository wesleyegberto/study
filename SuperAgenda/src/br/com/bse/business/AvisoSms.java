/**
 * Copyright (c) 2010-2012, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 26/05/2012
 * 
 * @version 1.0
 * 
 *          Classe de negócio AvisoSms
 */
package br.com.bse.business;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AvisoSms extends Aviso {
	private Set<String> numeros;
	private Map<Integer, Integer> celularContatos;
	private Set<Integer> grupos;

	public AvisoSms() {
		numeros = new HashSet<String>();
		celularContatos = new HashMap<Integer, Integer>();
		grupos = new HashSet<Integer>();
	}

	// Getters e Setters
	public void setNumeros(Set<String> numeros) {
		this.numeros = numeros;
	}

	public Set<String> getNumeros() {
		return numeros;
	}

	public void setCelularContatos(Map<Integer, Integer> celularContatos) {
		this.celularContatos = celularContatos;
	}

	public Map<Integer, Integer> getCelularContatos() {
		return celularContatos;
	}

	/**
	 * @param grupos
	 *            the grupos to set
	 */
	public void setGrupos(Set<Integer> grupos) {
		this.grupos = grupos;
	}

	/**
	 * @return the grupos
	 */
	public Set<Integer> getGrupos() {
		return grupos;
	}

	/**
	 * @see br.com.bse.business.Aviso#addContato(java.lang.String)
	 */
	@Override
	public void addContato(String valor) {
		if(!this.numeros.contains(valor))
			this.numeros.add(valor);
	}

	/**
	 * @see br.com.bse.business.Aviso#addContato(int, int)
	 */
	@Override
	public void addContato(int idContato, int idComun) {
		this.celularContatos.put(idContato, idComun);
	}

	/**
	 * @see br.com.bse.business.Aviso#addGrupo(int)
	 */
	@Override
	public void addGrupo(int idGrupo) {
		if(!this.grupos.contains(idGrupo))
			this.grupos.add(idGrupo);
	}

	/**
	 * @see br.com.bse.business.Aviso#removeContato(java.lang.String)
	 */
	@Override
	public void removeContato(String valor) {
		this.numeros.remove(valor);
	}

	/**
	 * @see br.com.bse.business.Aviso#removeContato(int, int)
	 */
	@Override
	public void removeContato(int idContato, int idComun) {

	}

	/**
	 * @see br.com.bse.business.Aviso#removeGrupo(int)
	 */
	@Override
	public void removeGrupo(int idGrupo) {
		this.grupos.remove(idGrupo);
	}

}
