/**
 * Copyright (c) 2010-2012, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito e William Carvalho 26/05/2012
 * 
 * @version 1.0
 * 
 *          Classe respons�vel pela cria��o de XML's
 */
package br.com.bse.pattern;

import java.util.ArrayList;
import java.util.List;

public class XMLCreator {
	private String root;
	private String xml;

	/**
	 * String -> nome do n� ou elemento Object -> valor para o elemento ou
	 * ref�ncia para o n�
	 */
	private List<Object[]> bodyRoot;

	/**
	 * Controe um novo XMLCreator com a lista de elementos vazia e com o root
	 * null.
	 */
	public XMLCreator() {
		bodyRoot = new ArrayList<Object[]>();
	}

	/**
	 * Controe um novo XMLCreator com a lista de elementos vazia e com o root
	 * recebido.
	 * 
	 * @param root
	 *            elemento raiz do XML
	 */
	public XMLCreator(String root) {
		this();
		setRoot(root);
	}

	/**
	 * Seta o elemento root do XML
	 * 
	 * @param elementName
	 *            nome da elemento root
	 */
	private void setRoot(String elementName) {
		this.root = elementName;
	}

	/**
	 * Seta um novo XML para criar
	 * 
	 * @param xml
	 *            XML que ser� utilizado
	 */
	public void setXml(StringBuffer xml) {

	}

	/**
	 * Retorna o XML comprensado
	 * 
	 * @return String contendo o XML
	 */
	public String getXml() {
		if(this.xml != null)
			return this.xml;
		else
			return this.getNewXml();
	}

	/**
	 * Limita o XML criado
	 */
	public void clearBody() {
		this.xml = null;
		this.bodyRoot = new ArrayList<Object[]>();
	}

	/**
	 * Procura a elemento key na List bodyRoot
	 * 
	 * @param key
	 *            Nome do elemento que ser� pesquisada
	 * @return posi��o do elemento encontrada, caso contrario retornar� -1
	 */
	private int searchOnRoot(String key) {
		return search(bodyRoot, key);
	}

	/**
	 * Procura a elemento key na List list
	 * 
	 * @param list
	 *            List em que ser� pesquisada
	 * @param key
	 *            Nome elemento que ser� pesquisada
	 * @return posi��o da elemento encontrada, caso contrario retornar� -1
	 */
	private int search(List<Object[]> list, String key) {
		if(list == null)
			return -1;

		int len = list.size();

		for(int i = 0; i < len; i++) {
			if(list.get(i) == null)
				continue;

			String s = (String) list.get(i)[0];
			if(s.equalsIgnoreCase(key)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Verifica se determinado n� ou elemento existe.
	 * 
	 * @param fullName
	 *            Caminho at� o n� ou elemento
	 * @return Se o elemento ou n� existe � retornado true, sen�o � retornado
	 *         false
	 */
	@SuppressWarnings("unchecked")
	public boolean exists(String fullName) {
		String[] path = fullName.split("[.]");
		int length = path.length;
		int index = -1;

		if(!this.root.equalsIgnoreCase(path[0]))
			return false;

		List<Object[]> current = bodyRoot;

		for(int i = 1; i < length; i++) {
			index = search(current, path[i]);
			if(index < 0)
				return false;
			else {
				if(current.get(index)[1] instanceof List<?>)
					current = (List<Object[]>) current.get(index)[1];
				else {
					if(i == length - 1)
						return true;
					else
						current = null;
				}
			}
		}
		return true;
	}

	/**
	 * Adiciona um elemento e valor na raiz do XML. Caso o elemento j� exista,
	 * ele � substituido.
	 * 
	 * @param elementName
	 *            nome da elemento
	 * @param value
	 *            valor para elemento
	 * @throws XMLException
	 *             se raiz do XML seja null
	 */
	public void add(String elementName, int value) throws XMLException {
		add(elementName, String.valueOf(value));
	}

	/**
	 * @see br.com.bse.pattern.XMLCreator#add(java.lang.String, int)
	 * 
	 * @param elementName
	 *            nome da elemento
	 * @param value
	 *            valor para elemento
	 * @throws XMLException
	 *             se raiz do XML seja null
	 */
	public void add(String elementName, String value) throws XMLException {
		add(elementName, (Object) value, true);
	}

	/**
	 * Adiciona um elemento e valor no XML.
	 * 
	 * @param elementName
	 *            nome da elemento
	 * @param value
	 *            valor para elemento
	 * @param replace
	 *            true substitui o elemento existente, false duplica o elemento
	 * 
	 * @throws XMLException
	 *             se raiz do XML seja null
	 */
	public void add(String elementName, String value, boolean replace) throws XMLException {
		add(elementName, (Object) value, replace);
	}

	/**
	 * @see br.com.bse.pattern.XMLCreator#add(java.lang.String,
	 *      java.lang.String, boolean)
	 * 
	 * @param elementName
	 *            nome da elemento
	 * @param value
	 *            valor para elemento
	 * @param replace
	 *            true substitui o elemento existente, false duplica o elemento
	 * 
	 * @throws XMLException
	 *             se raiz do XML seja null
	 */
	private void add(String elementName, Object value, boolean replace) throws XMLException {
		Object[] objs = new Object[2];
		objs[0] = elementName;
		objs[1] = value;

		if(!replace)
			bodyRoot.add(objs);
		else {
			int index = searchOnRoot(elementName);

			if(index < 0)
				bodyRoot.add(objs);
			else {
				Object[] objs2 = bodyRoot.get(index);

				objs2[0] = elementName;
				objs2[1] = value;
			}
		}
	}

	/**
	 * Adiciona um novo elemento n� na raiz do XML.
	 * 
	 * @param nodeName
	 *            nome do elemento n� a ser criado
	 * @param replace
	 *            true substitui o elemento existente, false duplica o elemento
	 * @throws XMLException
	 *             se raiz do XML seja null
	 */
	public void addNode(String nodeName, boolean replace) throws XMLException {
		add(nodeName, new ArrayList<Object[]>(), replace);
	}

	/**
	 * Adiciona um elemento n� em um elemento n� pai.
	 * 
	 * @param fullNode
	 *            caminho completo at� o pai elemento n� que ser� adicionado
	 * @param nodeName
	 *            nome do elemento n� � ser adicionado
	 * @param replace
	 *            true substitui o elemento existente, false duplica o elemento
	 * @throws XMLException
	 *             se raiz do XML seja null, ou se o caminho n�o existir
	 */
	@SuppressWarnings("unchecked")
	public void addNodeOn(String fullNode, String nodeName, boolean replace) throws XMLException {
		String[] path = fullNode.split("[.]");
		int length = path.length;
		int index = -1;

		List<Object[]> current = bodyRoot;
		if(!path[0].equalsIgnoreCase(this.root))
			throw new XMLException("O elemento " + path[0] + " n�o existe.");

		for(int i = 1; i < length; i++) {
			index = search(current, path[i]);

			if(i == length - 1) {
				if((index < 0) || replace == false)
					current.add(new Object[] { nodeName, new ArrayList<Object[]>() });
				else {
					if(current.get(index)[1] instanceof String)
						throw new XMLException("O elementmento " + path[i] + " n�o � um n�");

					List<Object[]> list = (List<Object[]>) current.get(index)[1];
					list.add(new Object[] { nodeName, new ArrayList<Object[]>() });
				}
			} else {
				if(index < 0)
					throw new XMLException("N� " + path[i] + " n�o existe.");
				else {
					if(current.get(index)[1] instanceof List<?>)
						current = (List<Object[]>) current.get(index)[1];
					else
						current = null;
				}
			}
		}
	}

	/**
	 * Adiciona um elemento n� em um elemento n� pai.
	 * 
	 * @param fullNode
	 *            caminho completo at� o pai elemento que ser� adicionado
	 * @param elementName
	 *            nome do elemento � ser adicionado
	 * @param value
	 *            valor do elemento n� � ser adicionado
	 * @param replace
	 *            true substitui o elemento existente, false duplica o elemento
	 * @throws XMLException
	 *             se raiz do XML seja null, ou se o caminho n�o existir
	 */
	@SuppressWarnings("unchecked")
	public void addElementOn(String fullNode, String elementName, String value, boolean replace) throws XMLException {
		String[] path = fullNode.split("[.]");
		int length = path.length;
		int index;

		List<Object[]> current = bodyRoot;

		for(int i = 1; i < length; i++) {
			index = search(current, path[i]);

			if(i == length - 1) {
				if((index < 0) || replace == false) {
					if(current.get(index)[1] instanceof List)
						((List<Object[]>) current.get(index)[1]).add(new Object[] { elementName, value });
					else
						current.add(new Object[] { elementName, value });
				} else {
					List<Object[]> list = (List<Object[]>) current.get(index)[1];
					list.add(new Object[] { elementName, value });
				}
			} else {
				if(index < 0)
					throw new XMLException("N� " + path[i] + " n�o existe.");
				else {
					if(current.get(index)[1] instanceof List<?>)
						current = (List<Object[]>) current.get(index)[1];
					else
						current = null;
				}
			}
		}
	}

	/**
	 * Exibe na sa�da padr�o os elementos no List, identando pela String
	 * recebida
	 * 
	 * @param list
	 *            List que cont�m os elementos
	 * @param spc
	 *            String para contendo o formato da identa��o
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private void printBodyXml(List<Object[]> list, String spc) {
		if(list == null)
			return;
		String s;
		Object o;
		int len = list.size();

		for(int i = 0; i < len; i++) {
			s = (String) list.get(i)[0];
			o = list.get(i)[1];

			if(o instanceof List) {
				System.out.println(spc + "+" + s);
				printBodyXml((List<Object[]>) o, spc + "\t");
			} else
				System.out.println(spc + s + " = " + o);

		}
	}

	/**
	 * � criado o XML a partir dos elementos adicionados.
	 * 
	 * @return String contendo o XML
	 */
	public String getNewXml() {
		StringBuffer xml = new StringBuffer("<" + root + ">");

		xml.append(getXml(this.bodyRoot));

		xml.append("</" + root + ">");

		this.setXml(xml);
		return xml.toString();
	}

	/**
	 * Retorna o XML criado.
	 * 
	 * @param list
	 *            List contendo os elementos
	 * @return StringBuffer contendo os elementos
	 */
	@SuppressWarnings("unchecked")
	private StringBuffer getXml(List<Object[]> list) {
		StringBuffer xml = new StringBuffer("");

		if(list == null)
			return xml;

		String s;
		Object o;
		int len = list.size();

		for(int i = 0; i < len; i++) {
			s = (String) list.get(i)[0];
			o = list.get(i)[1];

			if(o instanceof List) {
				xml.append("<" + s + ">");
				xml.append(getXml((List<Object[]>) o));
				xml.append("</" + s + ">");
			} else
				xml.append("<" + s + ">" + o + "</" + s + ">");

		}

		return xml;
	}

}
