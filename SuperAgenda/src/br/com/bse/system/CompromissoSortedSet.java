/**
 * Copyright (c) 2010-2012, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 02/08/2012
 * 
 * @version 1.0
 * 
 *          Classe responsável pela lista de Compromissos do dia.
 */
package br.com.bse.system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import br.com.bse.business.Compromisso;
import br.com.bse.pattern.Util;

public class CompromissoSortedSet {
	private NodeCompromisso firstNode;
	private NodeCompromisso lastNode;

	/**
	 * Controe um novo CompromissoSortedSet com o primeiro elemento vazio.
	 */
	public CompromissoSortedSet() {
		this.firstNode = this.lastNode = new NodeCompromisso();
	}

	/**
	 * Retorna a lista de compromissos que serão disparados no horário recebido.
	 * 
	 * @param time
	 *            Date contendo o horário
	 * @return List<Compromisso> contendo os compromissos do horário recebido
	 */
	public List<Compromisso> getCompromissoFrom(Date time) {
		return null;
	}

	/**
	 * Adiciona um compromisso na lista do dia.
	 * 
	 * @param c
	 *            Compromisso a ser adicionado
	 * @return true se foi adicionado com sucesso, senão false caso já exista
	 */
	public boolean add(Compromisso c) {
		if(c == null || c.getId() <= 0 || c.getHorarios() == null || c.getHorarios().size() == 0)
			return false;

		Collections.sort(c.getHorarios());
		Iterator<Date> iterator = c.getHorarios().iterator();
		String hora = null;
		while(iterator.hasNext()) {
			hora = Util.formataHora(iterator.next());
			this.firstNode.add(hora, c.getId());
		}

		return true;
	}

	/**
	 * Remove um compromisso da lista do dia.
	 * 
	 * @param c
	 *            Compromisso a ser removido
	 * @return true se foi removido com sucesso, senão false caso não exista
	 */
	public boolean remove(Compromisso c) {
		return false;
	}

	public List<Compromisso> getFirstList() {
		if(firstNode == null)
			return null;
		else {
			Compromisso c;
			List<Compromisso> list = new ArrayList<Compromisso>();

			Iterator<Integer> iterator = firstNode.idCompromissos.iterator();

			while(iterator.hasNext()) {
				c = SearchCompromissoManager.searchById(iterator.next());

				if(c != null)
					list.add(c);
			}

			if(list.size() > 0)
				return list;
			else
				return null;
		}
	}

	public Date getFirstTime() {
		if(firstNode == null)
			return null;
		else
			return Util.getNewTimeFrom(firstNode.time);
	}

	public void removeFirst() {
		if(firstNode != null) {
			firstNode = firstNode.next;
		}
	}

	public void print() {
		if(firstNode != null)
			firstNode.print();
	}

	private class NodeCompromisso {
		private NodeCompromisso back;
		private NodeCompromisso next;

		private String time;
		private Set<Integer> idCompromissos;

		/**
		 * Constroe um novo NodeCompromisso com um lista de IDs vazia e com o
		 * time null.
		 */
		public NodeCompromisso() {
			this.time = null;
			this.idCompromissos = new HashSet<Integer>();
		}

		/**
		 * Constroe um novo NodeCompromisso com uma lista de IDs vazia e com o
		 * time recebido.
		 * 
		 * @param time
		 *            String contendo a hora a ser disparado.
		 */
		public NodeCompromisso(String time) {
			this();
			this.time = time;
		}

		/**
		 * Constroe um novo NodeCompromisso com a lista de IDs e o time
		 * recebido.
		 * 
		 * @param time
		 *            String contendo a hora a ser disparado
		 * @param idCompromissos
		 *            lista de compromissos a serem disparado nesse horário
		 */
		public NodeCompromisso(String time, Set<Integer> idCompromissos) {
			this(time);
			if(idCompromissos != null)
				this.idCompromissos = idCompromissos;
		}

		/**
		 * Adiciona o ID do compromisso da lista de compromissos de determinado
		 * horário.
		 * 
		 * @param time
		 *            String contendo o horário do compromisso
		 * @param idCompromisso
		 *            ID do compromisso
		 * @return true se foi removido com sucesso, senão false caso já exista
		 */
		public boolean add(String time, int idCompromisso) {
			if(this.time == null) {
				this.time = time;
				return this.idCompromissos.add(idCompromisso);
			} else if(this.time.equals(time)) {
				return this.idCompromissos.add(idCompromisso);
			} else {
				if(this.time.compareTo(time) < 0) {
					if(this.next != null) {
						return this.next.add(time, idCompromisso);
					} else {
						NodeCompromisso newNode = new NodeCompromisso(time);
						newNode.back = this;

						this.next = newNode;
						if(this == lastNode)
							lastNode = newNode;

						return next.add(time, idCompromisso);
					}
				} else {
					NodeCompromisso newNode = new NodeCompromisso(time);

					if(back != null)
						this.back.next = newNode;

					newNode.next = this;
					this.back = newNode;

					if(this == firstNode)
						firstNode = newNode;

					return newNode.add(time, idCompromisso);
				}
			}
		}

		/**
		 * Remove o ID do compromisso da lista de compromissos de determinado
		 * horário.
		 * 
		 * @param time
		 *            String contendo o horário do compromisso
		 * @param idCompromisso
		 *            ID do compromisso
		 * @return true se foi removido com sucesso, senão false caso não exista
		 */
		public boolean remove(Date time, int idCompromisso) {
			if(this.time != null && this.time.equals(time))
				return this.idCompromissos.remove(idCompromissos);
			else if(this.next != null)
				return this.next.remove(time, idCompromisso);
			else
				return false;
		}

		public void print() {
			System.out.print(time + ": ");
			System.out.println(this.idCompromissos.toString());

			if(next != null)
				next.print();
		}
	}
}
