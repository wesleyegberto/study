/**
 * Copyright (c) 2010-2012, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 27/07/2012
 * 
 * @version 1.0
 * 
 *          Classe responsável pelo gerenciamento dos Compromissos próximos no
 *          sistema.
 */
package br.com.bse.system;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import br.com.bse.business.Compromisso;
import br.com.bse.db.dao.CompromissoDAO;
import br.com.bse.pattern.Util;

public class CompromissoManager implements Runnable {
	CompromissoSortedSet compromissos;
	Date minTime;
	Date lastTime;

	/**
	 * 
	 */
	public CompromissoManager() {
		super();
		compromissos = new CompromissoSortedSet();
	}

	/**
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		while(true) {
			if(minTime == null || !minTime.equals(compromissos.getFirstTime())) {
				try {
					System.out.println("I'm going to sleep.");
					Thread.sleep(1000);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				List<Compromisso> list = compromissos.getFirstList();

				if(list != null) {
					Iterator<Compromisso> iterator = list.iterator();

					while(iterator.hasNext()) {
						iterator.next().getId();
					}
				}
			}
		}
	}

	/**
	 * Seta o tempo mínimo até o próximo compromisso.
	 * 
	 * @param minTime
	 *            Date contendo o horário mínimo
	 */
	private void setMinTime(Date minTime) {
		this.minTime = minTime;
	}

	public void loadCompromissos() {
		if(this.lastTime == null || this.lastTime.compareTo(new Date()) <= 0)
			this.lastTime = new Date();

		loadCompromissos(this.lastTime);
		setMinTime(compromissos.getFirstTime());
	}

	private void loadCompromissos(Date date) {
		CompromissoDAO cDao = new CompromissoDAO();
		List<Compromisso> listComp = cDao.getByData(date);
		Compromisso c = null;

		this.lastTime = new Date();

		if(listComp != null) {
			Iterator<Compromisso> iterator = listComp.iterator();

			while(iterator.hasNext()) {
				c = iterator.next();
				compromissos.add(c);
			}
		}
	}

	public static void main(String... args) {
		CompromissoManager cm = new CompromissoManager();
		cm.loadCompromissos(Util.getNewDate(2012, 8, 5));
		cm.compromissos.print();

		Thread cManThread = new Thread(cm);
		cManThread.start();
	}
}
