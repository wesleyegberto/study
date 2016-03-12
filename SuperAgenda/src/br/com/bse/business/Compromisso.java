/**
 * Copyright (c) 2010-2012, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 26/05/2012
 * 
 * @version 1.0
 * 
 *          Classe de negócio Compromisso
 */
package br.com.bse.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Compromisso {
	private int id;
	private String titulo;
	private String descricao;
	private byte tipo;
	private Date dataCriacao;
	private List<Date> horarios;
	private List<Date> datas;
	private AvisoPc avisoPc;
	private AvisoEmail avisoEmail;
	private AvisoSms avisoSms;

	public Compromisso() {
		horarios = new ArrayList<Date>();
		datas = new ArrayList<Date>();
	}

	// Getters e Setters
	public void setId(int id) {
		if(id >= 0)
			this.id = id;
		else
			this.id = 0;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setTipo(byte tipo) {
		if(tipo >= 0)
			this.tipo = tipo;
		else
			this.tipo = 0;
	}

	public byte getTipo() {
		return tipo;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setHorarios(List<Date> horarios) {
		this.horarios = horarios;
	}

	public List<Date> getHorarios() {
		return horarios;
	}

	public void setDatas(List<Date> datas) {
		this.datas = datas;
	}

	public List<Date> getDatas() {
		return datas;
	}

	public AvisoPc getAvisoPc() {
		return avisoPc;
	}

	public void setAvisoPc(AvisoPc avisoPc) {
		this.avisoPc = avisoPc;
	}

	public AvisoEmail getAvisoEmail() {
		return avisoEmail;
	}

	public void setAvisoEmail(AvisoEmail avisoEmail) {
		this.avisoEmail = avisoEmail;
	}

	public AvisoSms getAvisoSms() {
		return avisoSms;
	}

	public void setAvisoSms(AvisoSms avisoSms) {
		this.avisoSms = avisoSms;
	}

	public void addHorario(Date hora) {
		if(!this.horarios.contains(hora))
			this.horarios.add(hora);
	}

	public void removeHorario(String hora) {
		this.horarios.remove(hora);
	}

	public void addData(Date data) {
		if(!this.datas.contains(data))
			this.datas.add(data);
	}

	public void removeData(Date data) {
		this.horarios.remove(data);
	}
}
