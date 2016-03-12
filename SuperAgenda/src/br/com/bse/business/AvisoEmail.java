/**
 * Copyright (c) 2010-2012, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 26/05/2012
 * 
 * @version 1.0
 * 
 *          Classe de negócio AvisoEmail
 */
package br.com.bse.business;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AvisoEmail extends Aviso {
	private Set<String> destinatarios;
	private Set<Integer> grupos;
	private Map<Integer, Integer> emailContatos;

	public AvisoEmail() {
		destinatarios = new HashSet<String>();
		grupos = new HashSet<Integer>();
		emailContatos = new HashMap<Integer, Integer>();
	}

	// Getters e Setters
	public void setDestinatarios(Set<String> destinatarios) {
		this.destinatarios = destinatarios;
	}

	public Set<String> getDestinatarios() {
		return destinatarios;
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

	public void setEmailContatos(Map<Integer, Integer> emailContatos) {
		this.emailContatos = emailContatos;
	}

	public Map<Integer, Integer> getEmailContatos() {
		return emailContatos;
	}

	/**
	 * @see br.com.bse.business.Aviso#addContato(java.lang.String)
	 */
	@Override
	public void addContato(String valor) {
		if(!this.destinatarios.contains(valor))
			this.destinatarios.add(valor);
	}

	/**
	 * @see br.com.bse.business.Aviso#addContato(int, int)
	 */
	@Override
	public void addContato(int idContato, int idEmail) {
		this.emailContatos.put(idContato, idEmail);
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
		this.grupos.remove(valor);
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
