/**
 * Copyright (c) 2010-2012, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 26/05/2012
 * 
 * @version 1.0
 * 
 *          Classe de negócio Contato
 */
package br.com.bse.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Contato {
	private int id;
	private String nome;
	private String apelido;
	private char sexo;
	private Date dataNasc;
	private String endereco;
	private String numero;
	private String bairro;
	private String cidade;
	private String uf;
	private char status;
	private List<Character> grupos;
	private HashMap<Integer, HashMap<String, String>> comunicacoes;

	public Contato() {
		grupos = new ArrayList<Character>();
		comunicacoes = new HashMap<Integer, HashMap<String, String>>();
	}

	// Getters e Setters
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getApelido() {
		return apelido;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public char getSexo() {
		return sexo;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNumero() {
		return numero;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCidade() {
		return cidade;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getUf() {
		return uf;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public char getStatus() {
		return status;
	}

	/**
	 * @param grupos
	 *            seta a lista de IDs dos grupos
	 */
	public void setGrupo(List<Character> grupos) {
		this.grupos = grupos;
	}

	/**
	 * @return a lista dos grupos
	 */
	public List<Character> getGrupos() {
		return grupos;
	}

	/**
	 * @param comunicacoes
	 *            seta a lista das comunicações
	 */
	public void setComunicacoes(HashMap<Integer, HashMap<String, String>> comunicacoes) {
		this.comunicacoes = comunicacoes;
	}

	/**
	 * @return a lista de comunicações
	 */
	public HashMap<Integer, HashMap<String, String>> getComunicacoes() {
		return comunicacoes;
	}
}
