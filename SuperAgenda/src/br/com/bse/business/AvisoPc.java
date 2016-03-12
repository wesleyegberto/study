/**
 * Copyright (c) 2010-2012, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 26/05/2012
 * 
 * @version 1.0
 * 
 *          Classe de negócio AvisoPc
 */
package br.com.bse.business;

import javax.swing.JOptionPane;

public class AvisoPc extends Aviso {
	private Musica musica;
	private byte posicao;

	// Getters e Setters
	public void setMusica(Musica musica) {
		this.musica = musica;
	}

	public Musica getMusica() {
		return musica;
	}

	/**
	 * @see br.com.bse.business.Aviso#addContato(java.lang.String)
	 */
	@Override
	public void addContato(String valor) {
		
	}

	/**
	 * @see br.com.bse.business.Aviso#addContato(java.lang.String)
	 */
	@Override
	public void addContato(int idContato, int idComun) {

	}

	/**
	 * @see br.com.bse.business.Aviso#addGrupo(int)
	 */
	@Override
	public void addGrupo(int idGrupo) {

	}

	/**
	 * @see br.com.bse.business.Aviso#removeContato(java.lang.String)
	 */
	@Override
	public void removeContato(String valor) {

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

	}

	public void setPosicao(byte posicao) {
		this.posicao = posicao;
	}

	public byte getPosicao() {
		return posicao;
	}

	public static void showAviso() {
		Compromisso objNegocio = new Compromisso();
		objNegocio.setDescricao("Teste");
		objNegocio.setTitulo("Teste AvisoPc");
		// Caminho da música
		objNegocio.getAvisoPc().getMusica().getCaminho();

		JOptionPane.showMessageDialog(null, objNegocio.getDescricao(), objNegocio.getTitulo(), 2);
	}

	public static void main(String[] args) {
		showAviso();
	}

}
