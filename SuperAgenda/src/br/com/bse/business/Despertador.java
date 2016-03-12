/**
 * Copyright (c) 2010-2012, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 26/05/2012
 * 
 * @version 1.0
 * 
 *          Classe de negócio Despertador
 */
package br.com.bse.business;

public class Despertador {
	private int id;
	private String titulo;
	private byte numeroToque;
	private byte intervalo;
	private Musica musica;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setNumeroToque(byte numeroToque) {
		if(numeroToque < 1)
			this.numeroToque = 1;
		else
			this.numeroToque = numeroToque;
	}

	public byte getNumeroToque() {
		return numeroToque;
	}

	public void setIntervalo(byte intervalo) {
		if(intervalo < 1)
			this.intervalo = 1;
		else
			this.intervalo = intervalo;
	}

	public byte getIntervalo() {
		return intervalo;
	}

	public void setMusica(Musica musica) {
		this.musica = musica;
	}

	public Musica getMusica() {
		return musica;
	}
}
