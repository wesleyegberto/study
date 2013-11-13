package br.com.bsetechnology.primefaces.bean;

import java.util.Date;

public class Tarefa {
	private String descricao;
	private boolean finalizado;
	private Date dataFinalizacao;

	public Tarefa() {
	}

	public Tarefa(String descricao, boolean finalizado, Date dataFinalizacao) {
		setDescricao(descricao);
		setFinalizado(finalizado);
		setDataFinalizacao(dataFinalizacao);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public Date getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(Date dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}

}
