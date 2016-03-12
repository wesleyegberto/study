/**
 * Copyright (c) 2010-2012, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito e William Carvalho Noronha 08/07/2012
 * 
 * @version 1.0
 * 
 *          Classe responsável por conter as configurações básicas do sistema
 */
package br.com.bse.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Properties;

public final class BaseManager implements Serializable {
	private static final long serialVersionUID = 3463187276793581499L;

	private Properties property = new Properties();

	private static BaseManager base;

	// Ao carregar a Classe, carrega o objeto de configurações serializado, se
	// não existir é lançado uma exception
	static {
		try {
			File file = new File(GeralProperty.ROOT + "bse.sys");
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			base = (BaseManager) ois.readObject();
		} catch(Exception e) {
			e.printStackTrace();
			javax.swing.JOptionPane.showMessageDialog(null,
					"Erro ao carregar arquivo de configuração. Por favor, repare o sistema.");
			// System.exit(1);

			/*
			 * base = new BaseManager(); base.finalize();
			 */
		}

	}

	public synchronized void finalize() {
		try {
			File file = new File(GeralProperty.ROOT + "bse.sys");
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retorna o próximo ID de cadastro do compromisso.
	 * 
	 * @return número do próximo ID a ser utilizado
	 */
	public static synchronized int getNextIdCompromisso() {
		String value = (String) base.property.getProperty("nextIdCompromisso");
		int id;
		try {
			id = Integer.parseInt(value);
			id++;
		} catch(NumberFormatException e) {
			id = 1;
		}
		base.property.setProperty("nextIdCompromisso", String.valueOf(id));
		return id;
	}

	/**
	 * Retorna o próximo ID de cadastro do contato.
	 * 
	 * @return número do próximo ID a ser utilizado
	 */
	public static synchronized int getNextIdContato() {
		String value = base.property.getProperty("nextIdContato");
		int id;
		try {
			id = Integer.parseInt(value);
			id++;
		} catch(NumberFormatException e) {
			id = 1;
		}
		base.property.setProperty("nextIdContato", String.valueOf(id));
		return id;
	}

	/**
	 * 
	 * Retorna o próximo ID de cadastro do despertador.
	 * 
	 * @return número do próximo ID a ser utilizado
	 */
	public static synchronized int getNextIdDespertador() {
		String value = base.property.getProperty("nextIdDespertador");
		int id;
		try {
			id = Integer.parseInt(value);
			id++;
		} catch(NumberFormatException e) {
			id = 1;
		}
		base.property.put("nextIdDespertador", id);
		return id;
	}

	/**
	 * Retorna o próximo ID de cadastro do grupo.
	 * 
	 * @return número do próximo ID a ser utilizado
	 */
	public static synchronized int getNextIdGrupo() {
		String value = base.property.getProperty("nextIdGrupo");
		int id;
		try {
			id = Integer.parseInt(value);
			id++;
		} catch(NumberFormatException e) {
			id = 1;
		}
		base.property.setProperty("nextIdGrupo", String.valueOf(id));
		return id;
	}

	/**
	 * Salva a configuração atual.
	 * 
	 * @return true se foi salvo com sucesso
	 */
	public static synchronized boolean saveConfig() {
		try {
			File file = new File(GeralProperty.ROOT + "bse.sys");
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(base);
			return true;
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
