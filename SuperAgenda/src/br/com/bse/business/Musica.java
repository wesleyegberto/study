/**
 * Copyright (c) 2010-2012, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 26/05/2012
 * 
 * @version 1.0
 * 
 *          Classe de negócio Musica
 * 
 */
package br.com.bse.business;

import java.io.File;

public class Musica {
	private File arquivo;
	private byte volume;
	private String inicio;
	private String fim;

	public Musica() {
	}

	public Musica(String caminho) {
		this.arquivo = new File(caminho);
	}

	public Musica(String caminho, byte volume) {
		this(caminho);
		this.setVolume(volume);
	}

	public Musica(String caminho, byte volume, String inicio, String fim) {
		this(caminho, volume);
		this.setInicio(inicio);
		this.setFim(fim);
	}

	public void setCaminho(File arquivo) {
		this.arquivo = arquivo;
	}

	public File getCaminho() {
		return arquivo;
	}

	public void setVolume(byte volume) {
		if(volume < 0)
			this.volume = 0;
		else
			this.volume = volume;
	}

	public byte getVolume() {
		return volume;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getInicio() {
		return inicio;
	}

	public void setFim(String fim) {
		this.fim = fim;
	}

	public String getFim() {
		return fim;
	}

}
