/**
 * Copyright (c) 2010-2012, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito e William Carvalho 18/06/2012
 * 
 * @version 1.0
 * 
 *          Classe principal, responsável por iniciar o sistema
 */
import br.com.bse.business.AvisoEmail;
import br.com.bse.business.AvisoPc;
import br.com.bse.business.AvisoSms;
import br.com.bse.business.Compromisso;
import br.com.bse.business.Musica;
import br.com.bse.db.dao.CompromissoDAO;
import br.com.bse.pattern.Util;
import br.com.bse.system.BaseManager;
import br.com.bse.system.CompromissoSortedSet;
import br.com.bse.system.GeralProperty;
import br.com.bse.system.SearchCompromissoManager;
import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Main {
	public Main() {
		super();
	}

	public void inserir() {
		Compromisso c = new Compromisso();
		CompromissoDAO cDao = new CompromissoDAO();

		c.setId(BaseManager.getNextIdCompromisso());
		c.setTipo((byte) 1);
		c.setTitulo("Inicio Facul");
		c.setDescricao("Primeiro dia de aula");
		c.setDataCriacao(new Date());
		c.addHorario(Util.getNewTimeFrom("12:00"));
		c.addHorario(Util.getNewTimeFrom("18:40"));
		c.addData(Util.getNewDate(2012, 8, 7));
		c.addData(Util.getNewDate(2012, 8, 9));
		c.addData(Util.getNewDate(2012, 8, 4));

		AvisoPc avisoPc = new AvisoPc();
		Musica m = new Musica();
		m.setCaminho(new File("C:\\"));
		m.setVolume((byte) 3);
		m.setInicio("00:10");
		m.setFim("1:50");
		avisoPc.setMusica(m);

		AvisoSms avisoSms = new AvisoSms();
		avisoSms.addContato("1169874512");
		avisoSms.addContato("1161232589");
		avisoSms.addContato(2, 4);
		avisoSms.addContato(1, 1);
		avisoSms.addGrupo(1);
		avisoSms.addGrupo(3);

		AvisoEmail avisoEmail = new AvisoEmail();
		avisoEmail.addContato("wesleyegberto@yahoo.com.br");
		avisoEmail.addContato("raul.douglas@hotmail.com");
		avisoEmail.addContato(2, 3);
		avisoEmail.addContato(5, 1);
		avisoEmail.addGrupo(2);
		avisoEmail.addGrupo(1);

		c.setAvisoPc(avisoPc);
		c.setAvisoSms(avisoSms);
		c.setAvisoEmail(avisoEmail);

		System.out.println("ID: " + c.getId());
		System.out.println("Inseriu: " + cDao.insert(c));

		BaseManager.saveConfig();
	}

	public void get() {
		Compromisso c = new Compromisso();
		CompromissoDAO cDao = new CompromissoDAO();

		long inicio = 0, fim = 0;

		inicio = System.nanoTime();

		c = cDao.get(GeralProperty.ROOT + "\\compromisso\\2012\\8\\5\\29.cmp");

		fim = System.nanoTime();

		if(c == null)
			System.out.println("\nIs null");
		else
			System.out.printf("\nID: " + c.getId() + "\tData: " + c.getDatas());

		System.out.println("\n\nTempo: " + (fim - inicio) / 10E9 + "\n\n");

		/*
		 * System.out.println(cDao.delete(c));
		 * 
		 * List<Compromisso> list = cDao.getByData(Util.getNewDate(2012, 5, 1));
		 * 
		 * if(list == null) System.out.println("Is null"); else {
		 * Iterator<Compromisso> iter = list.iterator(); while(iter.hasNext()) {
		 * Compromisso comp = iter.next(); System.out.printf("\nID: " +
		 * comp.getId() + "\tData: " + comp.getDatas()); } }
		 */
	}

	public void delete() {
		Compromisso c = new Compromisso();
		CompromissoDAO cDao = new CompromissoDAO();

		long inicio, fim;

		inicio = System.nanoTime();

		c = SearchCompromissoManager.searchById(3);

		System.out.println("Deletado: " + cDao.delete(c));

		fim = System.nanoTime();

		System.out.println("Tempo decorrido: " + (fim - inicio) / 10E9);
		// System.out.println(list.getTitulo());

		Date d = Util.getNewTimeFrom("22:10:82");

		if(d == null)
			System.out.println("Inválida.");
		else
			System.out.println("Time: " + d);

	}

	public void listaDisparo() {
		CompromissoSortedSet list = new CompromissoSortedSet();
		long inicio, fim;

		inicio = System.nanoTime();

		Compromisso c = new Compromisso();
		c.setId(1);
		c.addHorario(Util.getNewTimeFrom("10:00"));
		c.addHorario(Util.getNewTimeFrom("11:00"));
		list.add(c);

		c = new Compromisso();
		c.setId(2);
		c.addHorario(Util.getNewTimeFrom("11:00"));
		list.add(c);

		c = new Compromisso();
		c.setId(3);
		c.addHorario(Util.getNewTimeFrom("12:30"));
		c.addHorario(Util.getNewTimeFrom("09:30"));
		list.add(c);

		c.addHorario(Util.getNewTimeFrom("11:00"));
		list.add(c);

		fim = System.nanoTime();
		list.print();
		System.out.println("\n\nTempo decorrido: " + (fim - inicio) / 10E9);
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.get();
		// System.out.println(new String("10:00").compareTo("09:00"));
	}
}
