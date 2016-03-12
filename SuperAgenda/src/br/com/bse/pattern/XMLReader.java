/**
 * Copyright (c) 2010-2012, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito e William Carvalho
 * 
 *         26/05/2012
 * 
 * @version 1.0
 * 
 *          Classe responsável pela leitura de XML's
 */
package br.com.bse.pattern;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLReader {
	private List<Object[]> bodyRoot;
	private String root;
	private String xml;

	/**
	 * Controe um novo XMLReader com os elementos obtidos a partir do
	 * pathFileXml.
	 * 
	 * @param pathFileXml
	 *            caminho absoluto do XML à ser carregado
	 * @throws FileNotFoundException
	 *             É lançada quando o arquivo não for encontrado
	 * @throws IOException
	 *             se ocorrer qualquer erro de leitura
	 */
	public XMLReader(String pathFileXml) throws FileNotFoundException, IOException {
		this(new File(pathFileXml));
	}

	/**
	 * Controe um novo XMLReader com os elementos obtidos a partir do fileXml.
	 * 
	 * @param fileXml
	 *            caminho absoluto do XML à ser carregado
	 * @throws FileNotFoundException
	 *             É lançada quando o arquivo não for encontrado
	 * @throws IOException
	 *             se ocorrer qualquer erro de leitura
	 */
	public XMLReader(File fileXml) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileXml));
		String line = null;

		while((line = br.readLine()) != null) {
			this.xml = line.replace("\n", "");
		}
		br.close();
		fileXml = null;
		bodyRoot = construcFromRoot(this.xml);
		// printBodyXml(bodyRoot, "\t");
	}

	// Getters e Setters
	/**
	 * Retorna o XML que foi lido.
	 * 
	 * @return String contendo o XML
	 */
	public String getXml() {
		return this.xml;
	}

	/**
	 * Constroe o XMLReader a partir de um XML com apenas uma elemento root
	 * 
	 * @param xml
	 *            XML a ser lido
	 * @return List contendo a estrutura dos elementos do XML
	 */
	private List<Object[]> construcFromRoot(String xml) {
		List<Object[]> list = new ArrayList<Object[]>();
		String element, xmlFound;
		Matcher mP, m = Pattern.compile("(<.*?>)?").matcher(xml);

		if(m.find()) {
			element = m.group();

			element = element.replace("<", "").replace(">", "");
			mP = Pattern.compile("<" + element + ">.*?</" + element + ">").matcher(xml);

			this.root = element;

			if(!mP.find()) {
				list.add(new Object[] { element, xml });
			} else {
				xmlFound = mP.group();
				xml = xml.replace(xmlFound, "");
				xmlFound = xmlFound.replace("<" + element + ">", "").replace("</" + element + ">", "");
				construct(xmlFound, list);
			}
		}

		return list;
	}

	/**
	 * Constrói o XML a partir de uma String contendo o XML e um List que será
	 * adicionado os elementos.
	 * 
	 * @param xml
	 *            String contendo o XML
	 * @param list
	 *            List que será armazenado os elementos
	 */
	private void construct(String xml, List<Object[]> list) {
		if(xml.equals(""))
			return;
		String element, xmlFound;
		List<Object[]> subList = null;
		Matcher mP, m = Pattern.compile("(<.*?>)?").matcher(xml);

		while(m.find()) {
			if(xml.equals(""))
				return;
			element = m.group();

			element = element.replace("<", "").replace(">", "");
			mP = Pattern.compile("<" + element + ">.*?</" + element + ">").matcher(xml);

			if(!mP.find()) {
				list.add(new Object[] { element, xml });
				return;
			} else {
				xmlFound = mP.group();
				xml = xml.replace(xmlFound, "");
				xmlFound = xmlFound.replace("<" + element + ">", "").replace("</" + element + ">", "");

				mP = Pattern.compile("(<.*?>)+").matcher(xmlFound);
				if(mP.find()) {
					subList = new ArrayList<Object[]>();
					list.add(new Object[] { element, subList });
					construct(xmlFound, subList);
				} else {
					list.add(new Object[] { element, xmlFound });
				}
			}
			m = Pattern.compile("(<.*?>)?").matcher(xml);
		}
	}

	/**
	 * Procura a elemento key na List bodyRoot
	 * 
	 * @param key
	 *            Nome do elemento que será pesquisada
	 * @return posição do elemento encontrada, caso contrario retornará -1
	 */
	@SuppressWarnings("unused")
	private int searchOnRoot(String key) {
		return search(bodyRoot, key);
	}

	/**
	 * Procura a elemento key na List list
	 * 
	 * @param list
	 *            List em que será pesquisada
	 * @param key
	 *            Nome elemento que será pesquisada
	 * @return posição da elemento encontrada, caso contrario retornará -1
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
	 * Exibe na saída padrão os elementos no List, identando pela String
	 * recebida
	 * 
	 * @param list
	 *            List que contém os elementos
	 * @param spc
	 *            String para contendo o formato da identação
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
	 * 
	 * @param fullNodePath
	 * @param key
	 * @return
	 */
	public int[] search(String fullNodePath, String key) {
		return null;
	}

	/**
	 * Retorna o nó encontrado no caminho recebido.
	 * 
	 * @param fullNodePath
	 *            String contendo o caminho dos elementos até o nó
	 * @return List contendo os elementos do nó ou null caso não seja encontrado
	 *         ou não seja um nó
	 */
	@SuppressWarnings("unchecked")
	private List<Object[]> getNode(String fullNodePath) {
		String[] path = fullNodePath.split("[.]");
		List<Object[]> list = this.bodyRoot;
		Object[] itemFind = null;
		int length = path.length;
		int index = -1;

		for(int i = 1; i < length; i++) {
			index = search(list, path[i]);

			if(index < 0)
				return null;
			else {
				itemFind = list.get(index);

				if(itemFind.length == 2 && itemFind[1] instanceof List) {
					list = (List<Object[]>) itemFind[1];
				} else {
					return null;
				}
			}
		}

		return list;
	}

	/**
	 * Retorna o valor contido na primeira ocorrência de determinado elemento.
	 * 
	 * @param fullNodePath
	 *            Caminho até o elemento
	 * @param element
	 *            Nome do elemento
	 * @return String contendo o valor ou null caso não seja encontrado
	 */
	private String getValue(String fullNodePath, String element) {
		List<Object[]> list = getNode(fullNodePath);
		int index = -1;
		Object[] itemFind;

		if(list != null) {
			index = search(list, element);
			if(index < 0)
				return null;

			itemFind = list.get(index);
			if(itemFind[1] instanceof List)
				return null;
			else
				return (String) itemFind[1];
		}

		return null;
	}

	/**
	 * Retorna o valor contido na primeira ocorrência de determinado elemento.
	 * 
	 * @param fullNodePath
	 *            Caminho até o elemento
	 * @param element
	 *            Nome do elemento
	 * @return String contendo o valor ou null caso não seja encontrado
	 */
	public String getStringValue(String fullNodePath, String element) {
		return getValue(fullNodePath, element);
	}

	/**
	 * Retorna o valor inteiro contido na primeira ocorrência de determinado
	 * elemento.
	 * 
	 * @param fullNodePath
	 *            Caminho até o elemento
	 * @param element
	 *            Nome do elemento
	 * @return int contendo o valor ou -1 caso não seja encontrado
	 */
	public int getIntValue(String fullNodePath, String element) {
		try {
			return Integer.parseInt(getValue(fullNodePath, element));
		} catch(NumberFormatException e) {
			return -1;
		}
	}

	/**
	 * Retorna o valor byte contido na primeira ocorrência de determinado
	 * elemento.
	 * 
	 * @param fullNodePath
	 *            Caminho até o elemento
	 * @param element
	 *            Nome do elemento
	 * @return byte contendo o valor ou -1 caso não seja encontrado
	 */
	public byte getByteValue(String fullNodePath, String element) {
		try {
			return Byte.parseByte(getValue(fullNodePath, element));
		} catch(NumberFormatException e) {
			return (byte) -1;
		}
	}

	/**
	 * Retorna todos os valores de todas os elementos encontrados.
	 * 
	 * @param fullNodePath
	 *            Caminho até o elemento
	 * @param element
	 *            Nome do elemento
	 * @return List<String> contendo todos os valores encontrados, ou null caso
	 *         não seja encontrado
	 */
	public List<String> getValues(String fullNodePath, String element) {
		List<Object[]> list = getNode(fullNodePath);
		if(list == null)
			return null;
		ArrayList<String> listValue = new ArrayList<String>();
		Object[] itemFind;
		int length = list.size();

		for(int i = 0; i < length; i++) {
			itemFind = list.get(i);
			if(itemFind[1] instanceof List)
				continue;
			else if(element.equalsIgnoreCase((String) itemFind[0]))
				listValue.add((String) itemFind[1]);
		}

		if(listValue.size() > 0)
			return listValue;
		else
			return null;
	}

	public Object getAnyValue(String fullName, String element) {
		return null;
	}

	public String getNode(String fullName, String nodeName) {
		return null;
	}

	public String[] getNodes(String fullName, String element) {
		return null;
	}

	public Object getAnyNode(String fullName, String element) {
		return null;
	}

	/**
	 * Verifica se determinado nó ou elemento existe.
	 * 
	 * @param fullName
	 *            Caminho até o nó ou elemento
	 * @return Se o elemento ou nó existe é retornado true, senão é retornado
	 *         false
	 */
	@SuppressWarnings("unchecked")
	public boolean exists(String fullName) {
		String[] path = fullName.split("[.]");
		int length = path.length;
		int index = -1;

		if(path == null || !this.root.equalsIgnoreCase(path[0]))
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
}
